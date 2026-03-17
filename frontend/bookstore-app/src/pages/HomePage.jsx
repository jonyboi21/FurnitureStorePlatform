import React from 'react';
import { BookCard } from '../components';

const HomePage = ({ onAddToCart }) => (
  <div
    style={{
      minHeight: "100vh",
      background: "linear-gradient(135deg, #f8fafc 0%, #e0e7ff 100%)",
      padding: "2rem 0"
    }}
  >
    <div
      style={{
        maxWidth: "1200px",
        margin: "0 auto",
        padding: "2rem",
        background: "#fff",
        borderRadius: "16px",
        boxShadow: "0 4px 24px rgba(0,0,0,0.08)",
      }}
    >
      <h2
        style={{
          textAlign: "center",
          fontSize: "2.5rem",
          fontWeight: 700,
          marginBottom: "2rem",
          color: "#3730a3",
          letterSpacing: "1px"
        }}
      >
        Discover Our Furniture Collection
      </h2>
      <p
        style={{
          textAlign: "center",
          color: "#64748b",
          fontSize: "1.15rem",
          marginBottom: "2.5rem"
        }}
      >
        Browse our curated selection of quality furniture. Click on any item for more details or add it directly to your cart!
      </p>
      <BookCard onAddToCart={onAddToCart} />
    </div>
  </div>
);

export default HomePage;