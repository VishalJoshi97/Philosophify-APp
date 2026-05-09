import { View, Text, TouchableOpacity } from "react-native";
import { useState } from "react";

export default function Choose() {
    const [selected, setSelected] = useState("");

    const question = "Would you lie to save someone?";

    const options = [
        { label: "Utilitarian", desc: "Outcome matters" },
        { label: "Kantian", desc: "Truth matters" },
        { label: "Stoic", desc: "Control your response" },
    ];

    return (
        <View style={{ flex: 1, padding: 20 }}>
            <Text style={{ fontSize: 20 }}>{question}</Text>

            {options.map((opt) => (
                <TouchableOpacity
                    key={opt.label}
                    onPress={() => setSelected(opt.label)}
                    style={{
                        padding: 15,
                        backgroundColor: "#222",
                        marginTop: 10,
                        borderRadius: 10,
                    }}
                >
                    <Text style={{ color: "#fff" }}>{opt.label}</Text>
                </TouchableOpacity>
            ))}

            {selected !== "" && (
                <Text style={{ marginTop: 20 }}>
                    You chose: {selected}
                </Text>
            )}
        </View>
    );
}