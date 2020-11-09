import React, { FormEvent, useState } from "react";
import { Redirect } from "react-router-dom";

interface LoginProps {
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
  isLoggedIn: boolean;
}

const Login: React.FC<LoginProps> = ({ setIsLoggedIn, isLoggedIn }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const validateLogin = (e: FormEvent) => {
    e.preventDefault();

    const validate = (user: any) => {
      return user.password.length > 8 && user.username.toLowerCase() === "admin";
    };

    const logingUser = {
      username,
      password,
    };

    console.log("Currently trying to log in as: ", logingUser);
    if (validate(logingUser)) console.log("Logged in succesfully!");
    else {
      console.error("Something went wrong!");
    }

    setIsLoggedIn(validate(logingUser));
  };

  return !isLoggedIn ? (
    <form onSubmit={validateLogin}>
      <h1>Login to see more content</h1>
      {/* currently the session dies when we refresh */}
      <br />
      <input
        type="text"
        placeholder="username"
        name="username"
        onChange={(e) => {
          setUsername(e.target.value);
        }}
      />
      <input
        type="password"
        placeholder="password"
        name="password"
        onChange={(e) => {
          setPassword(e.target.value);
        }}
      />
      <button type="submit">Send the data!</button>
    </form>
  ) : (
    <Redirect to="/" />
  );
};

export default Login;
