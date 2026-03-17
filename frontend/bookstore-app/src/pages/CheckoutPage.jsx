import React from "react";
import { useHistory } from "react-router-dom";


function CheckoutPage({ cartItems }) {
    // Group items by furnitureName and count occurrences
        const history = useHistory();

    const grouped = cartItems.reduce((acc, item) => {
        const key = item.furnitureName;
        if (!acc[key]) {
            acc[key] = { ...item, count: 1 };
        } else {
            acc[key].count += 1;
        }
        return acc;
    }, {});
    const groupedItems = Object.values(grouped);

    // Calculate total
    const total = groupedItems.reduce((sum, item) => sum + (item.price || 0) * item.count, 0);

    return (
        <div style={{ padding: "2rem", textAlign: "center" }}>
            <h2>Checkout</h2>
            <ul style={{ listStyle: "none", padding: 0 }}>
    {groupedItems.map((item, idx) => (
        <li
            key={idx}
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                marginBottom: "2rem",
                padding: "1.5rem 2rem",
                background: "#f9f9f9",
                borderRadius: "8px",
                boxShadow: "0 2px 8px rgba(0,0,0,0.06)",
                maxWidth: "500px",
                marginLeft: "auto",
                marginRight: "auto"
            }}
        >
            <img
                src={item.imageUrl}
                alt={item.furnitureName}
                style={{
                    width: "120px",
                    height: "80px",
                    objectFit: "cover",
                    borderRadius: "8px",
                    marginRight: "2rem"
                }}
            />
            <span style={{ fontSize: "1.2rem" }}>
                <b>{item.furnitureName}</b> {item.count > 1 && <b>({item.count}x)</b>}<br />
                <span style={{ color: "#555" }}>${item.price}</span>
            </span>
        </li>
    ))}
</ul>
            <button
                style={{
                    margin: "2rem 0 0 0",
                    padding: "0.75rem 2rem",
                    background: "#007bff",
                    color: "#fff",
                    border: "none",
                    borderRadius: "4px",
                    cursor: "pointer",
                    fontSize: "1.1rem"
                }}
                onClick={() => {
                  history.push("/payment");   // Handle payment processing here
                }}
            >
                Pay ${total.toFixed(2)}
            </button>
        </div>
    );
}

export default CheckoutPage;