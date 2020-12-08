import { ToastContainer, toast } from "react-toastify";

import { Link } from "react-router-dom";
import React from "react";
const Home: React.FC = () => {
  const notify = () => toast("Wow so easy !");

  return (
    <>
      <ToastContainer />

      <button onClick={notify}>Notify !</button>

      <div className="Home">
        home
        <ul>
          <li>
            Go to <Link to="/">home</Link>
          </li>
          <li>
            Go to <Link to="/doctors">doctors</Link>
          </li>
          <li>
            Go to <Link to="/visits">visits</Link>
          </li>
          <li>
            Go to <Link to="/no_match">broken_link</Link>
          </li>
        </ul>
      </div>
    </>
  );
};

export default Home;
