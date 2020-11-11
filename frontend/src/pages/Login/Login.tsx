import React, { FormEvent, useState } from "react";
import { Redirect } from "react-router-dom";
import { useAppointmentGetter } from "../../API/Doctors";

interface LoginProps {
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
  isLoggedIn: boolean;
}

const Login: React.FC<LoginProps> = ({ setIsLoggedIn, isLoggedIn }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  /**
   * TODO: Clear out this call and console log after developement
   * This is how I am planning to hook up the data in applicaion
   * thanks to hooks we can react to each API request change and
   * if any of the data loads we can also react to specific hook loading said data.
   */
  const [data, loading, error, refetch] = useAppointmentGetter(1);
  console.log({ data, loading, error });
  // EOL for request preview

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
