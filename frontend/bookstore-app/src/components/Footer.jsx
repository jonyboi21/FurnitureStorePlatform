import React from 'react';

const Footer = () => (
  <footer
    style={{
      background: "linear-gradient(90deg, #6366f1 0%, #818cf8 100%)",
      color: "#fff",
      padding: "2rem 1rem 1.5rem 1rem",
      textAlign: "center",
      borderTopLeftRadius: "24px",
      borderTopRightRadius: "24px",
      boxShadow: "0 -2px 16px rgba(99,102,241,0.08)",
      marginTop: "3rem"
    }}
  >
    <p style={{ fontSize: "1.1rem", marginBottom: "0.5rem", letterSpacing: "1px" }}>
      &copy; {new Date().getFullYear()} Furniture Store. All rights reserved.
    </p>
    <p style={{ fontSize: "1rem", margin: 0 }}>
      Contact us:{" "}
      <a
        href="mailto:info@furniturestore.com"
        style={{
          color: "#e0e7ff",
          textDecoration: "underline",
          fontWeight: 500
        }}
      >
        info@furniturestore.com
      </a>
    </p>
  </footer>
);

export default Footer;