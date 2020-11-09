import React from "react";
import { Link } from "react-router-dom";

const Home: React.FC = () => {
  return (
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
  );
};

export default Home;
