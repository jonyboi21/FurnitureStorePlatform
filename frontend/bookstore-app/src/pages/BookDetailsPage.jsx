import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchFurnitureById } from "../services/bookService";

function BookDetailsPage() {
    const { id } = useParams();
    const [item, setItem] = useState(null);

    useEffect(() => {
        fetchFurnitureById(id).then(setItem);
    }, [id]);

    if (!item) return <div>Loading...</div>;

    return (
        <div style={{ maxWidth: 600, margin: "2rem auto", padding: "2rem", border: "1px solid #eee", borderRadius: "8px" }}>
            <img src={item.imageUrl} alt={item.furnitureName} style={{ width: "100%", height: "auto", marginBottom: "1rem" }} />
            <h2>{item.furnitureName}</h2>
            <p><b>Price:</b> ${item.price}</p>
            <p><b>Description:</b> {item.description || "No description available."}</p>
        </div>
    );
}

export default BookDetailsPage;