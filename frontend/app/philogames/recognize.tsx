import { View, Text, TouchableOpacity } from "react-native";
import { useState } from "react";

export default function Recognize() {
    const [selected, setSelected] = useState("");

    const question = "God is dead";
    const options = ["Nietzsche", "Marx", "Kant"];
    const answer = "Nietzsche";

    return (
        <View style={{ flex: 1, padding: 20 }}>
            <Text style={{ fontSize: 20, marginBottom: 20 }}>{question}</Text>

            {options.map((opt) => (
                <TouchableOpacity
                    key={opt}
                    onPress={() => setSelected(opt)}
                    style={{
                        padding: 15,
                        backgroundColor: selected === opt ? "#444" : "#222",
                        marginBottom: 10,
                        borderRadius: 10,
                    }}
                >
                    <Text style={{ color: "#fff" }}>{opt}</Text>
                </TouchableOpacity>
            ))}

            {selected !== "" && (
                <Text style={{ marginTop: 20 }}>
                    {selected === answer ? "✅ Correct" : "❌ Wrong"}
                </Text>
            )}
        </View>
    );
}