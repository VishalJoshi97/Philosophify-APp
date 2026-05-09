import client from "./client";

export const getAllPhilosophersApi = async () => {
    const res = await client.get("/philosophers");
    return res.data;
};


export const getPhilosopherByIdApi = async (id: string) => {
    const res = await client.get(`/philosophers/${id}`);
    return res.data;
};



