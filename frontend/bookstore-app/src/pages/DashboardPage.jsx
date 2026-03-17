import React, { useEffect, useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

const DashboardPage = () => {

  const [user, setUser] = useState(null);
  const history = useHistory();

  useEffect(() => {

    const token = localStorage.getItem("token");

    // Redirect if user is not logged in
    if (!token) {
      history.push("/login");
      return;
    }

    const fetchUser = async () => {
      try {

        const response = await axios.get(
          "http://localhost:8080/api/v1/users/me",
          {
            headers: {
              Authorization: `Bearer ${token}`
            }
          }
        );

        setUser(response.data);

      } catch (error) {
        console.error("Error fetching user:", error);
        history.push("/login");
      }
    };

    fetchUser();

  }, [history]);

  if (!user) {
    return <div>Loading dashboard...</div>;
  }

  return (
    <div style={{ padding: "2rem" }}>
      <h2>User Dashboard</h2>

      <p><strong>Username:</strong> {user.username}</p>
      <p><strong>First Name:</strong> {user.firstName}</p>
      <p><strong>Last Name:</strong> {user.lastName}</p>
      <p><strong>Street:</strong> {user.street}</p>
      <p><strong>City:</strong> {user.city}</p>
      <p><strong>Postal Code:</strong> {user.postalCode}</p>

    </div>
  );
};

export default DashboardPage;