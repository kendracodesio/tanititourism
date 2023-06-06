import React from 'react';
import Hero from "./Hero";
import About from "./About";
import Discover from "./Discover";
import Gallery from "./Gallery";

function Home() {
    return (
        <div>
            <Hero />
            <div className="container">
                <About />
                <Discover />
                <Gallery />
            </div>
        </div>
    );
}

export default Home;