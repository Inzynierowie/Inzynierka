import * as React from "react";

import { Redirect, Route, RouteProps } from "react-router-dom";

interface PrivateRouteProps extends RouteProps {
  // tslint:disable-next-line:no-any
  component: any;
  isLoggedIn: boolean;
}

const PrivateRoute = (props: PrivateRouteProps) => {
  const { component: Component, isLoggedIn, ...rest } = props;

  return (
    <Route
      {...rest}
      render={(routeProps) =>
        isLoggedIn ? (
          <Component {...routeProps} />
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: { from: routeProps.location },
            }}
          />
        )
      }
    />
  );
};

export default PrivateRoute;
