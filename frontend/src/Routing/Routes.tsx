import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import { Home, Login, Doctors, Visits } from "../pages";

const NotFound: React.FC = () => {
  return (
    <>
      <h1>404</h1>
      <h2>Not Found!</h2>
    </>
  );
};

const Routes: React.FC = () => {
  // This state should come from cookies
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return isLoggedIn ? (
    <Router>
      <Switch>
        {/* Gonna need layout here to manage login state (logout and such) */}
        <Route exact={true} path="/" component={Home} />
        <Route exact={true} path="/doctors" component={Doctors} />
        <Route exact={true} path="/visits" component={Visits} />
        <Route exact={true} path="*" component={NotFound} />
      </Switch>
    </Router>
  ) : (
    <Login isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
  );
};

export default Routes;
