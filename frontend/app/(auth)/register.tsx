import { View, Text, TextInput, TouchableOpacity } from "react-native";
import { useState } from "react";
import { useAuth } from "@/src/hooks/useAuth";
import { useRouter } from "expo-router";
import {registerApi} from "@/src/api/authApi";

export default function Register() {
    const router = useRouter();

    const [username, setUsername] = useState(""); // 🔥 NEW
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleRegister = async () => {
        setError("");

        //validation
        if (!username || !email || !password) {
            setError("All fields are required");
            return;
        }

        if (password.length < 6) {
            setError("Password must be at least 6 characters");
            return;
        }

        try {
            setLoading(true);

            await registerApi({
                username, // 🔥 NEW
                email,
                password,
            });

            alert("Account created 🎉");

            router.replace("/(auth)/login");

        } catch (err: any) {
            console.log(err);

            setError(
                err?.response?.data?.message || "Registration failed"
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <View style={{ flex: 1, backgroundColor: "black", justifyContent: "center", padding: 20 }}>

            <Text style={{ color: "white", fontSize: 28, marginBottom: 20 }}>
                Register
            </Text>

            <TextInput
                placeholder="Username"
                placeholderTextColor="gray"
                value={username}
                onChangeText={setUsername}
                style={{ backgroundColor: "#222", color: "white", padding: 12, marginBottom: 10, borderRadius: 10 }}
            />
            <TextInput
                placeholder="Email"
                placeholderTextColor="gray"
                value={email}
                onChangeText={setEmail}
                style={{ backgroundColor: "#222", color: "white", padding: 12, marginBottom: 10, borderRadius: 10 }}
            />

            <TextInput
                placeholder="Password"
                placeholderTextColor="gray"
                secureTextEntry
                value={password}
                onChangeText={setPassword}
                style={{ backgroundColor: "#222", color: "white", padding: 12, marginBottom: 20, borderRadius: 10 }}
            />

            <TouchableOpacity
                onPress={handleRegister}
                style={{ backgroundColor: "#2196F3", padding: 15, borderRadius: 10 }}
            >
                <Text style={{ color: "white", textAlign: "center" }}>Register</Text>
            </TouchableOpacity>

        </View>
    );
}