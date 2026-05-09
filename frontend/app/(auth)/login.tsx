import {View, Text, TextInput, TouchableOpacity, Alert} from "react-native";
import {useEffect, useState} from "react";
import { useAuth } from "@/src/hooks/useAuth";
import { useRouter } from "expo-router";
import {loginApi} from "@/src/api/authApi";
import {User} from "@/src/types/user";

export default function Login() {
    const { login,token } = useAuth();
    const router = useRouter();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        if (token) {
            console.log("✅ Redirecting to feed (JUST LOGGED IN)");
            router.replace("/(tabs)/feed");
        }
    }, [token]);


    // Normal Login
    const handleLogin = async () => {
        console.log("LOGIN CLICKED");
        try {
            const res = await loginApi({ email, password });

            console.log("JWT RESPONSE: ",res.data)
            const token = res?.data?.jwtToken;
            const user = {
                id: res.data.userId,
                username: res.data.username,
            };

            console.log("LOGIN TOKEN ",token)
            console.log("LOGIN USER ",user)

            if (!token || !user) {
                Alert.alert("Login Failed", "Invalid credentials");
                return;
            }

            await login(token,user as User);

            alert("Login Successful!");

        } catch (e: any) {
            console.log("❌ LOGIN ERROR:", e?.response?.data || e.message);
            Alert.alert("Login Failed", "Check your credentials");
        }
    };


    return (
        <View style={{ flex: 1, backgroundColor: "black", justifyContent: "center", padding: 20 }}>

            <Text style={{ color: "white", fontSize: 28, marginBottom: 20 }}>
                Philosophify
            </Text>

            <TextInput
                placeholder="Email"
                placeholderTextColor="gray"
                value={email}
                onChangeText={setEmail}
                style={{
                    backgroundColor: "#222",
                    color: "white",
                    padding: 12,
                    marginBottom: 10,
                    borderRadius: 10,
                }}
            />

            <TextInput
                placeholder="Password"
                placeholderTextColor="gray"
                secureTextEntry
                value={password}
                onChangeText={setPassword}
                style={{
                    backgroundColor: "#222",
                    color: "white",
                    padding: 12,
                    marginBottom: 20,
                    borderRadius: 10,
                }}
            />

            <TouchableOpacity
                onPress={handleLogin}
                style={{
                    backgroundColor: "#4CAF50",
                    padding: 15,
                    borderRadius: 10,
                }}
            >
                <Text style={{ color: "white", textAlign: "center" }}>Login</Text>
            </TouchableOpacity>

            <TouchableOpacity onPress={() => router.push("/register")}>
                <Text style={{ color: "gray", marginTop: 15, textAlign: "center" }}>
                    Don&#39;t have an account? Register
                </Text>
            </TouchableOpacity>

        </View>
    );
}