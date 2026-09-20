import { Link } from "react-router-dom";

function Navbar() {

  return (
    <nav className="navbar">

      <div className="logo">
        🍔 Food Frenzy
      </div>

      <ul className="nav-links">

        <li>
          <Link to="/">Home</Link>
        </li>

        <li>
          <Link to="/">Menu</Link>
        </li>

        <li>
          <Link to="/cart">Cart 🛒</Link>
        </li>

        <li>
          Orders
        </li>

        <li>
          Login
        </li>

      </ul>

    </nav>
  );
}

export default Navbar;