import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch, useLocation } from 'react-router-dom';
import { HomePage, BookDetailsPage } from './pages';
import { Header, Footer, BookCard } from './components';
import Cart from './components/Cart';
import CheckoutPage from './pages/CheckoutPage';
import PaymentForm from './pages/PaymentForm';
import SignInPage from './pages/SignInPage';
import CreateAccountPage from './pages/CreateAccountPage';
import DashboardPage from './pages/DashboardPage';

function MainContent({ cartItems, handleAddToCart }) {
  const location = useLocation();
  const hideCart = location.pathname === "/checkout";

  return (
    <div>
      <Header />
      {!hideCart && <Cart cartItems={cartItems} />}
      <Switch>
        <Route path="/" exact render={() => <HomePage onAddToCart={handleAddToCart} />} />
        <Route path="/books" render={() => <BookCard onAddToCart={handleAddToCart} />} />
        <Route path="/furniture/:id" render={() => <BookDetailsPage />} />
        <Route path="/checkout" render={() => <CheckoutPage cartItems={cartItems} />} />
        <Route path="/payment" component={PaymentForm} />
        <Route path="/login" component={SignInPage} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/create-account" component={CreateAccountPage} />
      </Switch>
      <Footer />
    </div>
  );
}

function App() {
  const [cartItems, setCartItems] = useState([]);

  const handleAddToCart = (item) => {
    setCartItems((prev) => [...prev, item]);
  };

  return (
    <Router>
      <MainContent cartItems={cartItems} handleAddToCart={handleAddToCart} />
    </Router>
  );
}

export default App;