import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";

const CreateAccountPage = () => {
  const [form, setForm] = useState({
    username: "",
    password: "",
    confirm: "",
    firstName: "",
    lastName: "",
    street: "",
    city: "",
    postalCode: ""
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const history = useHistory();

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { username, password, confirm, firstName, lastName, street, city, postalCode } = form;
    if (!username || !password || !confirm || !firstName || !lastName || !street || !city || !postalCode) {
      setError("All fields are required.");
      return;
    }
    if (password !== confirm) {
      alert("Passwords Do Not match");
      setError("Passwords do not match.");
      return;
    }
    setError("");
    setLoading(true);
    try {
      // Adjust the URL to match your Spring Boot backend endpoint
      await axios.post("http://localhost:8080/api/v1/users/register", {
        username,
        password,
        firstName,
        lastName,
        street,
        city,
        postalCode
      });
      alert("Account created! You can now sign in.");
      history.push("/signin");
    } catch (err) {
      setError(
        err.response?.data?.message ||
        "Failed to create account. Please try again."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "0.5rem auto 0 auto", padding: "2rem", background: "#fff", borderRadius: "12px", boxShadow: "0 2px 16px rgba(99,102,241,0.08)" }}>
      <h2 style={{ textAlign: "center", marginBottom: "1.5rem" }}>Create Account</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "1rem" }}>
          <label>First Name</label>
          <input
            type="text"
            name="firstName"
            value={form.firstName}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Last Name</label>
          <input
            type="text"
            name="lastName"
            value={form.lastName}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Street Address</label>
          <input
            type="text"
            name="street"
            value={form.street}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>City</label>
          <input
            type="text"
            name="city"
            value={form.city}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Postal Code</label>
          <input
            type="text"
            name="postalCode"
            value={form.postalCode}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Username</label>
          <input
            type="text"
            name="username"
            value={form.username}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Password</label>
          <input
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Confirm Password</label>
          <input
            type="password"
            name="confirm"
            value={form.confirm}
            onChange={handleChange}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        {error && <div style={{ color: "red", marginBottom: "1rem" }}>{error}</div>}
        <button
          type="submit"
          disabled={loading}
          style={{
            width: "100%",
            padding: "0.7rem",
            background: "#6366f1",
            color: "#fff",
            border: "none",
            borderRadius: "8px",
            fontWeight: 600,
            fontSize: "1rem",
            cursor: loading ? "not-allowed" : "pointer"
          }}
        >
          {loading ? "Creating..." : "Create Account"}
        </button>
        <button
          type="button"
          style={{
            width: "100%",
            padding: "0.7rem",
            background: "#818cf8",
            color: "#fff",
            border: "none",
            borderRadius: "8px",
            fontWeight: 600,
            fontSize: "1rem",
            cursor: "pointer",
            marginTop: "0.7rem"
          }}
          onClick={() => history.push("/login")}
        >
          Back to Sign In
        </button>
      </form>
    </div>
  );
};

export default CreateAccountPage;