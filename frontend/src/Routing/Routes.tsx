import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import { Home, Login, Doctors, Visits, NotFound } from "../pages";

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
