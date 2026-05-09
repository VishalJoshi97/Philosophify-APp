import client from "./client";

//used directly in detailed philosopher/[id]
export const addFavoriteApi = async (payload: any) => {
    return client.post("/favorites", payload);
};

export const getFavoritesApi = async (userId: number) => {
    const res = await client.get(`/favorites/${userId}`);
    return res.data;
};

export const removeFavoriteApi = async (favId: number) => {
    return client.delete(`/favorites/${favId}`);
};