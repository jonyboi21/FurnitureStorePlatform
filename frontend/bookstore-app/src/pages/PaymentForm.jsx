import React, { useState } from "react";
import axios from "axios";

function PaymentForm() {
    const [phone, setPhone] = useState("");
    const [amount, setAmount] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("Processing payment...");
        try {
            const res = await axios.post("http://localhost:8080/api/mpesa/pay", {
                phone,
                amount: parseFloat(amount)
            });
            setMessage("Payment request sent! Check your phone to complete the payment.");
        } catch (err) {
            setMessage("Payment failed. Please try again.");
        }
    };

    return (
        <div style={{ padding: "2rem", textAlign: "center" }}>
            <h2>Pay with M-Pesa</h2>
            <form onSubmit={handleSubmit} style={{ maxWidth: 400, margin: "2rem auto" }}>
                <input
                    type="text"
                    placeholder="Phone (2547XXXXXXXX)"
                    value={phone}
                    onChange={e => setPhone(e.target.value)}
                    style={{ width: "100%", padding: "0.5rem", marginBottom: "1rem" }}
                    required
                />
                <input
                    type="number"
                    placeholder="Amount"
                    value={amount}
                    onChange={e => setAmount(e.target.value)}
                    style={{ width: "100%", padding: "0.5rem", marginBottom: "1rem" }}
                    required
                />
                <button
                    type="submit"
                    style={{
                        padding: "0.75rem 2rem",
                        background: "#28a745",
                        color: "#fff",
                        border: "none",
                        borderRadius: "4px",
                        cursor: "pointer",
                        fontSize: "1.1rem"
                    }}
                >
                    Pay Now
                </button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}

export default PaymentForm;