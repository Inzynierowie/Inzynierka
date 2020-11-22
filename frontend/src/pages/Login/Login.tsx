import React, { FormEvent, useEffect, useState } from "react";
import { Redirect } from "react-router-dom";
import { useCookies } from "react-cookie";
import ReactLoading, { LoadingType } from "react-loading";
import { useAppointmentGetter, useLoginPost } from "../../API/Doctors";

interface LoginProps {
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
  isLoggedIn: boolean;
}

const Login: React.FC<LoginProps> = ({ setIsLoggedIn, isLoggedIn }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [cookies, setCookie, removeCookie] = useCookies();

  const [data, loading, error, refetch, setUser] = useLoginPost(
    cookies?.loggedUser?.email,
    cookies?.loggedUser?.password,
  );

  useEffect(() => {
    debugger;
    if (!loading && !error) {
      setCookie("loggedUser", JSON.stringify({ ...data, password }));
      alert(`Welcome back, ${data.name} ${data.surname}!`);
      setIsLoggedIn(true);
    }
  }, [data, loading, error, cookies]);

  const onFormSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setUser({ email, password });
  };

  return !isLoggedIn ? (
    <>
      {loading ? (
        <ReactLoading delay={3000} type="cylon" color="#CCC" height="0" width="100%" />
      ) : (
        <form onSubmit={onFormSubmit}>
          <h1>Login to see more content</h1>
          {/* currently the session dies when we refresh */}
          <br />
          <input
            type="text"
            placeholder="username"
            name="username"
            onChange={(e) => {
              setEmail(e.target.value);
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
      )}
    </>
  ) : (
    <Redirect to="/" />
  );
};

export default Login;
