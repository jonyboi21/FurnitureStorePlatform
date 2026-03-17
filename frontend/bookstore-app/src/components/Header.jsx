import React from 'react';
import { useHistory } from 'react-router-dom';

const Header = () => {
  const history = useHistory();

  return (
    <header
      style={{
        background: "linear-gradient(90deg, #6366f1 0%, #818cf8 100%)",
        color: "#fff",
        padding: "1.2rem 1rem 0.8rem 1rem", // reduced padding
        borderBottomLeftRadius: "24px",
        borderBottomRightRadius: "24px",
        boxShadow: "0 2px 16px rgba(99,102,241,0.08)",
        marginBottom: "1rem", // reduced margin
        marginTop: 0,
        letterSpacing: "1px",
        position: "relative"
      }}
    >
      {/* Home Button */}
      <button
        style={{
          position: "absolute",
          top: "1.5rem",
          left: "2rem",
          background: "#fff",
          color: "#6366f1",
          border: "none",
          borderRadius: "20px",
          padding: "0.5rem 1.5rem",
          fontWeight: 600,
          fontSize: "1rem",
          cursor: "pointer",
          boxShadow: "0 2px 8px rgba(99,102,241,0.10)",
          transition: "background 0.2s, color 0.2s"
        }}
        onClick={() => history.push("/")}
      >
        Home
      </button>
      {/* Sign In Button */}
      <button
        style={{
          position: "absolute",
          top: "1.5rem",
          right: "2rem",
          background: "#fff",
          color: "#6366f1",
          border: "none",
          borderRadius: "20px",
          padding: "0.5rem 1.5rem",
          fontWeight: 600,
          fontSize: "1rem",
          cursor: "pointer",
          boxShadow: "0 2px 8px rgba(99,102,241,0.10)",
          transition: "background 0.2s, color 0.2s"
        }}
        onClick={() => history.push("/login")}
      >
        Sign In
      </button>
      <div style={{ textAlign: "center" }}>
        <h1 style={{ margin: 0, fontWeight: 700, fontSize: "2.2rem", letterSpacing: "2px" }}>
          Furniture Store
        </h1>
        <p style={{ margin: "0.5rem 0 0 0", fontSize: "1.1rem", color: "#e0e7ff" }}>
          Your home for quality furniture
        </p>
      </div>
    </header>
  );
};

export default Header;