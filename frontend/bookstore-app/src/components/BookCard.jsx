import { useState, useEffect } from "react";
import { fetchFurniture } from "../services/bookService";
import { Link } from "react-router-dom";

console.log("onAddToCart is", typeof onAddToCart);
function BookCard({ onAddToCart }) {
    const [items, setItems] = useState([]);
    const [selectedIndex, setSelectedIndex] = useState(-1);
      useEffect(() => {
        fetchFurniture().then(setItems).catch(() => setItems([]));
    }, []);

    return (
         <>
            {items.length === 0 && <p>No items available</p>}
            <div style={{
                display: "grid",
                gridTemplateColumns: "repeat(auto-fit, minmax(320px, 1fr))",
                gap: "2rem",
                padding: "2rem"
            }}>
                {items.map((item, index) => (
                    <div
                        key={index}
                        style={{
                            border: "1px solid #eee",
                            borderRadius: "8px",
                            boxShadow: "0 2px 8px rgba(0,0,0,0.08)",
                            padding: "1rem",
                            background: "#fff",
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center"
                        }}
                    >
                        <img
                            src={item.imageUrl}
                            alt={item.furnitureName || item.name}
                            style={{
                                width: "100%",
                                height: "180px",
                                objectFit: "cover",
                                borderRadius: "4px",
                                marginBottom: "1rem"
                            }}
                        />
                        <h3 style={{ margin: "0.5rem 0" }}>
                            <Link to={`/furniture/${item.id}`} style={{ textDecoration: "none", color: "#007bff" }}>
                                {item.furnitureName}
                            </Link></h3>
                        { <p style={{ color: "#888" }}>${item.price}</p> }
                        <button
                            style={{
                                marginTop: "auto",
                                padding: "0.5rem 1.5rem",
                                background: "#007bff",
                                color: "#fff",
                                border: "none",
                                borderRadius: "4px",
                                cursor: "pointer"
                            }}
                            onClick={() => onAddToCart(item)}

                        >
                            Add to Cart
                        </button>
                    </div>
                ))}
            </div>
        </>
    );
}

export default BookCard;
 