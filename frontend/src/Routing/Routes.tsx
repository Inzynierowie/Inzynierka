import { Doctors, Home, Login, NotFound, Register, Visits } from "../pages";
import { Footer, Header } from "../components";
import React, { useState } from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";

import PrivateRoute from "./PrivateRoute";

const Routes: React.FC = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <Router>
      <Header />
      <Switch>
        {/* Gonna need layout here to manage login state (logout and such) */}
        <PrivateRoute isLoggedIn={isLoggedIn} exact={true} path="/" component={Home} />
        <PrivateRoute isLoggedIn={isLoggedIn} exact={true} path="/doctors" component={Doctors} />
        <PrivateRoute isLoggedIn={isLoggedIn} exact={true} path="/visits" component={Visits} />
        <Route
          exact={true}
          path="/login"
          component={() => <Login isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />}
        />
        <Route exact={true} path="/register" component={() => <Register />} />
        <Route path="*" component={NotFound} />
      </Switch>
      <Footer />
    </Router>
  );
};

export default Routes;
