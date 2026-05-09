import { View, Text, TouchableOpacity } from "react-native";
import { Share } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { Ionicons } from "@expo/vector-icons";

export default function QuoteCard({ quote, author, isFav, onToggle }: any) {

    const handleShare = async () => {
        try {
            await Share.share({
                message: `"${quote.text}" - ${author || "Unknown"}`,
            });
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <LinearGradient
            colors={["#1c1c1c", "#2b2b2b"]}
            style={{
                borderRadius: 20,
                margin: 12,
                padding: 1, // border effect
            }}
        >
            <View
                style={{
                    backgroundColor: "#121212",
                    borderRadius: 20,
                    padding: 18,
                }}
            >

                {/* Quote Icon */}
                <Ionicons name="analytics-outline" size={22} color="#888" />

                {/* Quote Text */}
                <Text
                    style={{
                        color: "white",
                        fontSize: 17,
                        lineHeight: 24,
                        marginTop: 8,
                        fontWeight: "500",
                    }}
                >
                    {quote.text}
                </Text>

                {/* Author */}
                <Text
                    style={{
                        color: "#aaa",
                        marginTop: 12,
                        textAlign: "right",
                        fontSize: 13,
                    }}
                >
                    — {author || "Unknown"}
                </Text>

                {/* Divider */}
                <View
                    style={{
                        height: 1,
                        backgroundColor: "#2a2a2a",
                        marginVertical: 14,
                    }}
                />

                {/* Actions */}
                <View
                    style={{
                        flexDirection: "row",
                        justifyContent: "space-between",
                    }}
                >

                    {/* Favorite */}
                    <TouchableOpacity
                        onPress={onToggle}
                        style={{ flexDirection: "row", alignItems: "center", gap: 6 }}
                    >
                        <Ionicons
                            name={isFav ? "heart" : "heart-outline"}
                            size={20}
                            color={isFav ? "#ff4d4d" : "#888"}
                        />
                        <Text style={{ color: "#888", fontSize: 13 }}>
                            {isFav ? "Liked" : "Like"}
                        </Text>
                    </TouchableOpacity>

                    {/* Share */}
                    <TouchableOpacity
                        onPress={handleShare}
                        style={{ flexDirection: "row", alignItems: "center", gap: 6 }}
                    >
                        <Ionicons name="share-social-outline" size={20} color="#4da6ff" />
                        <Text style={{ color: "#4da6ff", fontSize: 13 }}>
                            Share
                        </Text>
                    </TouchableOpacity>

                </View>
            </View>
        </LinearGradient>
    );
}