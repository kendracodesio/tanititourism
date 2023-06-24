import React from 'react';
import { NavLink } from 'react-router-dom';



function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg pt-3 shadow">
            <NavLink to="/" className ="navbar-brand ps-5 pe-4"><img className="navbar-img" src="https://tanititourismimages.blob.core.windows.net/images/taniti-logo.png" alt="taniti-logo" /></NavLink>
            <button className="navbar-toggler me-2" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse ms-3" id="navbarNavDropdown">
                <ul className="navbar-nav justify-content-evenly">
                    <li className="nav-item ms-5 me-5 pe-3 text-center">
                        <NavLink to="/do" className="nav-link">THINGS TO<br /> DO</NavLink>
                    </li>
                    <li className="nav-item ms-5 me-5 pe-3 text-center">
                        <NavLink to="/stay" className="nav-link">PLACES <br />TO STAY</NavLink>
                    </li>
                    <li className="nav-item  ms-5 me-5 pe-3 text-center">
                        <NavLink to="/dine" className="nav-link">RESTAURANTS & <br /> NIGHTLIFE</NavLink>
                    </li>
                    <li className="nav-item  ms-5 me-5  pe-3 text-center">
                        <NavLink to="/plan" className="nav-link">PLAN YOUR <br /> TRIP</NavLink>
                    </li>
                </ul>

            </div>
        </nav>
    );
}

export default Navbar;