import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomePage from './HomePage';


const Pages = () => {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={HomePage} />
                <Route path="/book/:id" component={BookDetailsPage} />
            </Switch>
        </Router>
    );
};

//export default Pages;
export { default as HomePage } from './HomePage';
export { default as BookDetailsPage } from './BookDetailsPage';
