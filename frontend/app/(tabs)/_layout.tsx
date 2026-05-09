import { Redirect, Tabs } from "expo-router";
import { ActivityIndicator, View } from "react-native";
import { useAuth } from "@/src/hooks/useAuth";
import { Ionicons } from "@expo/vector-icons";

export default function TabsLayout() {
    const { token, loading } = useAuth();

    if (loading) {
        return (
            <View style={{ flex: 1, justifyContent: "center", alignItems: "center", backgroundColor: "#000" }}>
                <ActivityIndicator size="large" color="#d4af37" />
            </View>
        );
    }

    if (!token) return <Redirect href="/(auth)/login" />;

    return (
        <Tabs
            screenOptions={{
                headerShown: false,

                // 🎨 Premium Tab Bar Style
                tabBarStyle: {
                    backgroundColor: "#121212",
                    borderTopWidth: 0,
                    height: 65,
                    paddingBottom: 8,
                    elevation: 10
                },

                tabBarActiveTintColor: "#d4af37", // gold
                tabBarInactiveTintColor: "#888",

                tabBarShowLabel: false
            }}
        >

            {/* 🏠 Feed */}
            <Tabs.Screen
                name="feed"
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <Ionicons name="home-outline" size={24} color={color} />
                    )
                }}
            />

            {/* 🧠 Concepts */}
            <Tabs.Screen
                name="concepts"
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <Ionicons name="bulb-outline" size={24} color={color} />
                    )
                }}
            />

            {/* ❤️ Favorites */}
            <Tabs.Screen
                name="favorites"
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <Ionicons name="heart-outline" size={24} color={color} />
                    )
                }}
            />

            <Tabs.Screen
                name="philogames"
                options={{
                    tabBarIcon: ({ color, size }) => (
                        <Ionicons name="brush" size={24} color={color} />
                    )
                }}
                />

        </Tabs>
    );
}