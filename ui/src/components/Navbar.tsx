import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons'
import { NavLink } from 'react-router-dom';



function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg pt-3 shadow">
            <NavLink to="/" className ="navbar-brand ps-5 pe-4"><img className="navbar-img" src="/images/taniti-logo.png" alt="taniti-logo" /></NavLink>
            <button className="navbar-toggler me-2" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav mx-auto">
                    <li className="nav-item me-5 text-center">
                        <NavLink to="/do" className="nav-link">THINGS TO<br /> DO</NavLink>
                    </li>
                    <li className="nav-item me-5 text-center">
                        <NavLink to="/stay" className="nav-link">PLACES <br />TO STAY</NavLink>
                    </li>
                    <li className="nav-item me-5 text-center">
                        <NavLink to="/dine" className="nav-link">RESTAURANTS & <br /> NIGHTLIFE</NavLink>
                    </li>
                    <li className="nav-item me-5 text-center">
                        <NavLink to="/plan" className="nav-link">PLAN YOUR <br /> TRIP</NavLink>
                    </li>
                </ul>
                <form className="search-area d-flex justify-content-end me-3" role="search">
                <div>
                    <input className="form-control search-input" placeholder="Search..." aria-label="Search" />
                </div>
                <div className="search-btn ms-1">
                    <button className="btn search" type="submit"><FontAwesomeIcon className="search-icon" icon={faMagnifyingGlass}></FontAwesomeIcon></button>
                </div>
                </form>
            </div>
        </nav>
    );
}

export default Navbar;