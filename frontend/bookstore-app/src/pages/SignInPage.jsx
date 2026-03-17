import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";


const SignInPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!username || !password) {
      setError("Both fields are required.");
      return;
    }
    setError("");
    setLoading(true);
    try {
      // Adjust the URL to match your Spring Boot backend login endpoint
      const response = await axios.post("http://localhost:8080/api/v1/users/login", {
        username,
        password
      });
      // Handle successful login (e.g., save token, redirect)
      alert("Login successful!");
      history.push("/dashboard")
    } catch (err) {
      setError(
        err.response?.data?.message ||
        "Login failed. Please check your credentials."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "0.5rem auto 0 auto", padding: "2rem", background: "#fff", borderRadius: "12px", boxShadow: "0 2px 16px rgba(99,102,241,0.08)" }}>
      <h2 style={{ textAlign: "center", marginBottom: "1.5rem" }}>Sign In</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "1rem" }}>
          <label>Username</label>
          <input
            type="text"
            value={username}
            onChange={e => setUsername(e.target.value)}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            style={{ width: "100%", padding: "0.5rem", borderRadius: "6px", border: "1px solid #ddd" }}
            required
          />
        </div>
        {error && <div style={{ color: "red", marginBottom: "1rem" }}>{error}</div>}
        <button
          type="submit"
          style={{
            width: "100%",
            padding: "0.7rem",
            background: "#6366f1",
            color: "#fff",
            border: "none",
            borderRadius: "8px",
            fontWeight: 600,
            fontSize: "1rem",
            cursor: "pointer",
            marginBottom: "1rem"
          }}
        >
          Sign In
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
            cursor: "pointer"
          }}
          onClick={() => history.push("/create-account")}
        >
          Create an Account
        </button>
      </form>
    </div>
  );
};

export default SignInPage;