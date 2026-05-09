import { View, FlatList, ActivityIndicator, RefreshControl } from "react-native";
import {useCallback, useEffect, useState} from "react";
import { getAllPhilosophersApi } from "@/src/api/philosopherApi";
import PhilosopherCard from "@/src/components/PhilosopherCard";
import {useFocusEffect} from "expo-router";

export default function Feed() {
    const [data, setData] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const [refreshing, setRefreshing] = useState(false);

    const fetchPhilosophers = async () => {
        try {
            const res = await getAllPhilosophersApi();
            setData(res);
        } catch (err) {
            console.log("Feed Error:", err);
        } finally {
            setLoading(false);
            setRefreshing(false);
        }
    };

    useFocusEffect(
        useCallback(() => {
            fetchPhilosophers();
        }, [])
    );

    if (loading) {
        return (
            <View style={{ flex: 1, justifyContent: "center", backgroundColor: "black" }}>
                <ActivityIndicator size="large" color="white" />
            </View>
        );
    }

    return (
        <FlatList
            style={{ backgroundColor: "black" }}
            data={data}
            keyExtractor={(item) => item.id.toString()}
            renderItem={({ item }) => <PhilosopherCard item={item} />}
            refreshControl={
                <RefreshControl
                    refreshing={refreshing}
                    onRefresh={() => {
                        setRefreshing(true);
                        fetchPhilosophers();
                    }}
                />
            }
        />
    );
}