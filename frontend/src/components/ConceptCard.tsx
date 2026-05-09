// components/ConceptCard.js
import { View, Text } from "react-native";
import { Ionicons } from "@expo/vector-icons";

export default function ConceptCard({ item }:any) {
    return (
        <View style={{
            backgroundColor: "#121212",
            margin: 14,
            padding: 18,
            borderRadius: 20,
            shadowColor: "#000",
            shadowOpacity: 0.4,
            shadowRadius: 10,
            elevation: 8
        }}>

            {/* Header */}
            <View style={{ flexDirection: "row", justifyContent: "space-between", alignItems: "center" }}>

                {/* Title */}
                <Text style={{
                    color: "#fff",
                    fontSize: 22,
                    fontWeight: "700"
                }}>
                    {item.title}
                </Text>

                {/* Icon */}
                <Ionicons name="book-outline" size={22} color="#aaa" />
            </View>

            {/* Category Badge */}
            <View style={{
                alignSelf: "flex-start",
                backgroundColor: "#2e7d32",
                paddingHorizontal: 10,
                paddingVertical: 4,
                borderRadius: 10,
                marginTop: 6
            }}>
                <Text style={{ color: "#fff", fontSize: 12 }}>
                    {item.category}
                </Text>
            </View>

            {/* Explanation */}
            <View style={{ flexDirection: "row", marginTop: 14 }}>
                <Ionicons name="bulb-outline" size={18} color="#FFD700" style={{ marginRight: 6 }} />
                <Text style={{
                    color: "#ccc",
                    flex: 1,
                    lineHeight: 20
                }}>
                    {item.explanation}
                </Text>
            </View>

            {/* Divider */}
            <View style={{
                height: 1,
                backgroundColor: "#333",
                marginVertical: 12
            }} />

            {/* Example */}
            <View style={{ flexDirection: "row" }}>
                <Ionicons name="sparkles-outline" size={18} color="#4FC3F7" style={{ marginRight: 6 }} />
                <Text style={{
                    color: "#e0f7fa",
                    fontStyle: "italic",
                    flex: 1
                }}>
                    {item.example}
                </Text>
            </View>

        </View>
    );
}