import { useHistory } from "react-router-dom";

function Cart({ cartItems }) {
    const history = useHistory();
    const handleCheckout = () => {
        if (cartItems.length === 0) {
            alert("Cart is empty");
        } else {
            history.push("/checkout");
        }
    };

    // Group items by furnitureName and count occurrences
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

    // Responsive styles
    const cartStyle = {
        position: "sticky",
        top: "2rem",
        right: 0,
        marginLeft: "auto",
        width: "260px",
        minWidth: "220px",
        maxWidth: "90vw",
        padding: "1.2rem",
        border: "1px solid #e0e7ef",
        borderRadius: "16px",
        background: "#fff",
        boxShadow: "0 4px 24px rgba(0,0,0,0.08)",
        textAlign: "center",
        zIndex: 1000,
        marginBottom: "2rem",
    };

    // For mobile, stack the cart below content
    const isMobile = window.innerWidth < 700;
    if (isMobile) {
        cartStyle.position = "static";
        cartStyle.width = "100%";
        cartStyle.margin = "2rem 0";
    }

    return (
        <div style={cartStyle}>
            <h2 style={{ marginBottom: "1.2rem", color: "#3730a3", fontSize: "1.3rem" }}>Shopping Cart</h2>
            {cartItems.length === 0 ? (
                <p style={{ color: "#888" }}>Your cart is empty.</p>
            ) : (
                <ul style={{ listStyle: "none", padding: 0, marginBottom: "1.2rem" }}>
                    {groupedItems.map((item, idx) => (
                        <li
                            key={idx}
                            style={{
                                marginBottom: "0.8rem",
                                padding: "0.5rem 0.7rem",
                                background: "#f8fafc",
                                borderRadius: "8px",
                                display: "flex",
                                alignItems: "center",
                                justifyContent: "space-between",
                                fontSize: "0.98rem"
                            }}
                        >
                            <span>
                                <b>{item.furnitureName}</b> {item.count > 1 && <span style={{ color: "#007bff" }}>({item.count}x)</span>}
                            </span>
                            <span style={{ color: "#555" }}>
                                {item.price && <>${item.price}</>}
                            </span>
                        </li>
                    ))}
                </ul>
            )}
            <button
                style={{
                    width: "100%",
                    padding: "0.6rem 0",
                    background: "#28a745",
                    color: "#fff",
                    border: "none",
                    borderRadius: "8px",
                    cursor: "pointer",
                    fontSize: "1rem",
                    fontWeight: 600,
                    letterSpacing: "1px",
                    boxShadow: "0 2px 8px rgba(40,167,69,0.08)"
                }}
                onClick={handleCheckout}
            >
                Checkout
            </button>
        </div>
    );
}

export default Cart;