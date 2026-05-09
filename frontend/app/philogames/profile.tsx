import { View, Text } from "react-native";

export default function Profile() {
    return (
        <View style={{ flex: 1, padding: 20 }}>
            <Text style={{ fontSize: 20 }}>Your Thinking Style</Text>

            <Text style={{ marginTop: 20 }}>
                Nietzsche: 60%
            </Text>

            <Text>Stoic: 30%</Text>
            <Text>Kantian: 10%</Text>
        </View>
    );
}