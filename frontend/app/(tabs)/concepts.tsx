// screens/ConceptsScreen.js
import { useEffect, useState } from "react";
import { View, Text, FlatList, ActivityIndicator } from "react-native";
import { getConcepts } from "@/src/api/conceptApi";
import ConceptCard from "@/src/components/ConceptCard";

export default function ConceptsScreen() {
    const [concepts, setConcepts] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getConcepts()
            .then(setConcepts)
            .finally(() => setLoading(false));
    }, []);

    if (loading) return <ActivityIndicator size="large" />;

    return (
        <FlatList
            style={{ backgroundColor: "black" }}
            data={concepts}
            keyExtractor={(item, index) => index.toString()}
            renderItem={({ item }) => <ConceptCard item={item} />}
        />
    );
}