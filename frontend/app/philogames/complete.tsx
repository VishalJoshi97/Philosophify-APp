import { View, Text, TextInput } from "react-native";
import { useState } from "react";

export default function Complete() {
    const [input, setInput] = useState("");

    const sentence = "Man is condemned to be ____";
    const answer = "free";

    return (
        <View style={{ flex: 1, padding: 20 }}>
            <Text style={{ fontSize: 20 }}>{sentence}</Text>

            <TextInput
                value={input}
                onChangeText={setInput}
                placeholder="Your answer"
                style={{
                    borderWidth: 1,
                    marginTop: 20,
                    padding: 10,
                    borderRadius: 10,
                }}
            />

            {input !== "" && (
                <Text style={{ marginTop: 20 }}>
                    {input.toLowerCase() === answer
                        ? "✅ Correct"
                        : "❌ Try again"}
                </Text>
            )}
        </View>
    );
}