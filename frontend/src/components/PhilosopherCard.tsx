import { View, Text, Image, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";
import { LinearGradient } from "expo-linear-gradient";
import { Ionicons } from "@expo/vector-icons";

export default function PhilosopherCard({ item }: any) {
    const router = useRouter();

    return (
        <TouchableOpacity
            onPress={() => router.push(`/philosopher/${item.id}`)}
            activeOpacity={0.85}
            style={{
                marginHorizontal: 14,
                marginVertical: 10,
                borderRadius: 18,
                overflow: "hidden",
                backgroundColor: "#121212",

                // shadow (Android + iOS)
                elevation: 6,
                shadowColor: "#000",
                shadowOpacity: 0.4,
                shadowRadius: 10,
            }}
        >
            {/* Image */}
            <View>
                <Image
                    source={{ uri: item.imageUrl }}
                    style={{ height: 500, width: "100%" }}
                    resizeMode="cover"                />

                {/* Gradient overlay */}
                <LinearGradient
                    colors={["transparent", "rgba(0,0,0,0.85)"]}
                    style={{
                        position: "absolute",
                        bottom: 0,
                        width: "100%",
                        height: 100,
                        justifyContent: "flex-end",
                        padding: 12,
                    }}
                >
                    {/* Name on image */}
                    <Text
                        style={{
                            color: "white",
                            fontSize: 20,
                            fontWeight: "bold",
                        }}
                        numberOfLines={1}
                    >
                        {item.name}
                    </Text>
                </LinearGradient>
            </View>

            {/* Content */}
            <View style={{ padding: 12 }}>
                <Text
                    style={{
                        color: "#bbb",
                        fontSize: 13,
                        lineHeight: 18,
                    }}
                    numberOfLines={2}
                >
                    {item.shortBio}
                </Text>

                {/* Bottom row */}
                <View
                    style={{
                        flexDirection: "row",
                        justifyContent: "space-between",
                        alignItems: "center",
                        marginTop: 10,
                    }}
                >
                    <Text style={{ color: "#666", fontSize: 12 }}>
                        Tap to explore
                    </Text>

                    <Ionicons
                        name="arrow-forward-circle"
                        size={22}
                        color="#4da6ff"
                    />
                </View>
            </View>
        </TouchableOpacity>
    );
}