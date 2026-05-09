import { View, Text, FlatList, ActivityIndicator } from "react-native";
import {useCallback, useState} from "react";
import { useAuth } from "@/src/hooks/useAuth";
import {addFavoriteApi, getFavoritesApi, removeFavoriteApi} from "@/src/api/favoriteApi";
import {useFocusEffect} from "expo-router";
import QuoteCard from "@/src/components/QuoteCard";

export default function Favorites() {
    const { user } = useAuth();
    const [data, setData] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const [favorites, setFavorites] = useState<any[]>([]);

    //get all fav
    const fetchFavorites = async () => {
        try {
            // @ts-ignore
            const res = await getFavoritesApi(user.id);

            // console.log("QUOTE DATA:",res)
            setData(res); // ✅ direct usage
            setFavorites(res);
        } catch (err) {
            console.log("Fav error:", err);
        } finally {
            setLoading(false);
        }
    };

    //toggle fav
    const toggleFavorite = async (quoteId: number) => {
        try {
            const existing = favorites.find(
                (f) => f.type === "QUOTE" && f.data.id === quoteId
            );

            if (existing) {
                // ✅ 1. REMOVE instantly from UI
                setFavorites((prev) =>
                    prev.filter((f) => f.id !== existing.id)
                );

                // ✅ 2. Call backend
                await removeFavoriteApi(existing.id);

            } else {
                // optional: optimistic add
                const temp = {
                    id: Date.now(), // temporary id
                    type: "QUOTE",
                    data: { id: quoteId, text: "..." },
                    author: ""
                };

                setFavorites((prev) => [temp, ...prev]);

                await addFavoriteApi({
                    userId: user?.id,
                    type: "QUOTE",
                    referenceId: quoteId,
                });

                // better: refetch or replace temp
            }

        } catch (err) {
            console.log("Toggle error:", err);

            // ❗ Optional rollback (if needed)
            fetchFavorites();
        }
    };


    //immediate update
    useFocusEffect(
        useCallback(() => {
            fetchFavorites();
        }, [])
    );

    if (loading) {
        return (
            <View style={{ flex: 1, justifyContent: "center", backgroundColor: "black" }}>
                <ActivityIndicator color="white" />
            </View>
        );
    }

    console.log("LENGTH:", data.length);
    return (
        <FlatList
            style={{ backgroundColor: "black" }}
            data={data}
            // inverted={(data?.length || 0) > 5}
            keyExtractor={(_, i) => i.toString()}
            renderItem={({ item }) => (
                <View>
                    {item.type === "QUOTE" ? (
                        <QuoteCard
                            author={item.author}
                            quote={item.data}
                            isFav={favorites.some(f => f.data.id === item.data.id)}
                            onToggle={() => toggleFavorite(item.data.id)}
                        />
                    ) : (
                        <View
                            style={{
                                margin: 12,
                                padding: 16,
                                backgroundColor: "#1e1e1e",
                                borderRadius: 16,
                            }}
                        >
                            <Text style={{ color: "white", fontSize: 18 }}>
                                {item.data.name}
                            </Text>

                            <Text style={{ color: "#aaa" }}>
                                {item.data.shortBio}
                            </Text>
                        </View>
                    )}
                </View>
            )}
        />
    );
}