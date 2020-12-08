import { Nav, NavDropdown, Navbar } from "react-bootstrap";
import React, { useState } from "react";

import { useHistory } from "react-router-dom";

const Header: React.FC = () => {
  let history = useHistory();
  const [pathname, setPathname] = useState(history.location.pathname);
  history.listen((location) => {
    setPathname(location.pathname);
  });

  return (
    <Navbar style={{ boxShadow: "0px -15px 20px black" }} className="position-fixed w-100" bg="transparent" expand="lg">
      <Navbar.Brand href="/">
        <img src="/logo_200x200.png" width="100" height="40" className="d-inline-block align-top" alt="MedKit logo" />
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse className="justify-content-center" id="basic-navbar-nav">
        <Nav className="">
          <Nav.Link active={pathname === "/"} className="text-capitalize" eventKey="/" href="/">
            home
          </Nav.Link>
          <Nav.Link active={pathname === "/doctors"} className="text-capitalize" eventKey="/doctors" href="/doctors">
            doctors
          </Nav.Link>
          <Nav.Link active={pathname === "/visits"} className="text-capitalize" eventKey="/visits" href="/visits">
            visits
          </Nav.Link>
          <NavDropdown title="Services" id="basic-nav-dropdown">
            <NavDropdown.Item href="/services#1">Invoicing</NavDropdown.Item>
            <NavDropdown.Item href="/services#2">Calendar app</NavDropdown.Item>
            <NavDropdown.Item href="/services#3">Specialists</NavDropdown.Item>
            <NavDropdown.Item href="/services#4">24/7 support</NavDropdown.Item>
          </NavDropdown>
        </Nav>
      </Navbar.Collapse>
      <Nav variant="pills">
        <Nav.Link active={pathname === "/login"} className="text-capitalize mr-3" eventKey="/login" href="/login">
          login
        </Nav.Link>
        <Nav.Link active={pathname === "/register"} className="text-capitalize" eventKey="/register" href="/register">
          register
        </Nav.Link>
      </Nav>
    </Navbar>
  );
};

export default Header;
