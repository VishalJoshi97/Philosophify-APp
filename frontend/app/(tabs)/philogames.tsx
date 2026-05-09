import { View, Text, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";

export default function PhiloGamesTab() {
    const router = useRouter();

    const Card = ({ title, route }: any) => (
        <TouchableOpacity
            onPress={() => router.push(route)}
            style={{
                backgroundColor: "#111",
                padding: 20,
                borderRadius: 12,
                marginBottom: 15,
            }}
        >
            <Text style={{ color: "#fff", fontSize: 18 }}>{title}</Text>
        </TouchableOpacity>
    );

    return (
        <View style={{ flex: 1, padding: 20, backgroundColor: "#000" }}>
            <Text style={{ color: "#fff", fontSize: 24, marginBottom: 20 }}>
                PhiloGames
            </Text>

            <Card title="🧩 Recognize" route="/philogames/recognize" />
            <Card title="🧠 Complete Thought" route="/philogames/complete" />
            <Card title="⚔️ Choose Philosophy" route="/philogames/choose" />
            <Card title="📊 Your Mind" route="/philogames/profile" />
        </View>
    );
}