import {View, Text, Image, FlatList, ActivityIndicator} from "react-native";
import { useLocalSearchParams } from "expo-router";
import { useEffect, useState } from "react";
import { getPhilosopherByIdApi } from "@/src/api/philosopherApi";
import { useAuth } from "@/src/hooks/useAuth"
import client from "@/src/api/client";
import QuoteCard from "@/src/components/QuoteCard";
import {LinearGradient} from "expo-linear-gradient";

export default function PhilosopherDetail() {
    const { id } = useLocalSearchParams();
    const [data, setData] = useState<any>(null);
    const [loading, setLoading] = useState(true);
    const { user } = useAuth();

    const [quotes, setQuotes] = useState<any[]>([]);

    const toggleFavorite = async (id: number) => {

        //instant UI update
        setQuotes(prev =>
            prev.map(q =>
                q.id === id ? { ...q, isFav: !q.isFav } : q
            )
        );

        try {
            await client.post("/favorites", {
                userId: user?.id,
                type: "QUOTE",
                referenceId: id,
            });
        } catch (err) {
            console.log("Favorite error:", err);
        }
    };


    const fetchData = async () => {
        try {
            const res = await getPhilosopherByIdApi(id as string);
            setData(res);

            //THIS IS CRITICAL
            setQuotes(
                res.quotes.map((q: any) => ({
                    ...q,
                    isFav: q.isFav ?? false
                }))
            );

        } catch (err) {
            console.log("Detail error:", err);
        } finally {
            setLoading(false);
        }
    };


    useEffect(() => {
        fetchData();
    }, []);

    if (loading) {
        return (
            <View style={{ flex: 1, justifyContent: "center", backgroundColor: "black" }}>
                <ActivityIndicator size="large" color="white" />
            </View>
        );
    }

    return (
        <FlatList
            style={{ backgroundColor: "#0d0d0d" }}
            showsVerticalScrollIndicator={false}

            ListHeaderComponent={
                <>
                    {/* Hero Image with overlay */}
                    <View>
                        <Image
                            source={{ uri: data.imageUrl }}
                            style={{ height: 500, width: "100%" }}
                        />

                        {/* Dark gradient overlay */}
                        <LinearGradient
                            colors={["transparent", "#0d0d0d"]}
                            style={{
                                position: "absolute",
                                bottom: 0,
                                width: "100%",
                                height: 120,
                            }}
                        />
                    </View>

                    {/* Name */}
                    <Text
                        style={{
                            color: "white",
                            fontSize: 26,
                            fontWeight: "bold",
                            marginHorizontal: 14,
                            marginTop: 10,
                        }}
                    >
                        {data.name}
                    </Text>

                    {/* Bio */}
                    <Text
                        style={{
                            color: "#aaa",
                            marginHorizontal: 14,
                            marginTop: 6,
                            lineHeight: 20,
                        }}
                    >
                        {data.fullBio}
                    </Text>

                    {/* Section Title */}
                    <Text
                        style={{
                            color: "white",
                            fontSize: 18,
                            marginHorizontal: 14,
                            marginTop: 18,
                            marginBottom: 6,
                            fontWeight: "600",
                        }}
                    >
                        Quotes
                    </Text>
                </>
            }

            data={quotes}
            keyExtractor={(item: any) => item.id.toString()}

            renderItem={({ item }) => (
                <QuoteCard
                    quote={item}
                    author={data.name}
                    isFav={item.isFav}
                    // onToggle={() => addToFavorites(item.id)}
                    onToggle={() => toggleFavorite(item.id)}
                />
            )}
        />
    );
}