import client from "@/src/api/client";

export const getQuoteById = async (id: number) => {
    const res = await client.get(`/quotes/${id}`);
    return res.data;
};