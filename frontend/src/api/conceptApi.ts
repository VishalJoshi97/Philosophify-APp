import client from "./client";

// services/conceptService.js
export const getConcepts = async () => {
    const res =await client.get("/concepts");
    return res.data
};