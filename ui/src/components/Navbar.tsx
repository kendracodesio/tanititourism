import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons'



interface NavbarProps {

}

const Navbar: React.FC<NavbarProps> = () => {
    return (
        <nav className="navbar navbar-expand-lg pt-3 shadow">
            <a className="navbar-brand ps-5 pe-4" href="index.html">
                <img className="navbar-img" src="/images/taniti-logo.png" alt="taniti-logo" />
            </a>
            <button className="navbar-toggler me-2" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav mx-auto">
                    <li className="nav-item me-5 text-center">
                        <a className="nav-link" href="do.html">THINGS TO<br /> DO</a>
                    </li>
                    <li className="nav-item me-5 text-center">
                        <a className="nav-link" href="stay.html">PLACES <br />TO STAY</a>
                    </li>
                    <li className="nav-item me-5 text-center">
                        <a className="nav-link" href="restaurants-nightlife.html">RESTAURANTS & <br /> NIGHTLIFE</a>
                    </li>
                    <li className="nav-item me-5 text-center">
                        <a className="nav-link" href="plan.html">PLAN YOUR <br /> TRIP</a>
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