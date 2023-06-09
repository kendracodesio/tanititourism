import React from 'react';
import HomePageHero from "./components/HomePageHero";
import About from "./components/About";
import Discover from "./components/Discover";
import Gallery from "./components/Gallery";

function Home() {
    return (
        <div>
            <HomePageHero />
            <div className="container">
                <About />
                <Discover />
                <Gallery />
            </div>
        </div>
    );
}

export default Home;