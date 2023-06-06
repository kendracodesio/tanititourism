import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAngleUp } from '@fortawesome/free-solid-svg-icons'

function GoToTopButton () {
    const [isVisible, setIsVisible] = useState(false);

    //Show button when page is scrolled up to a given amount
    const toggleVisibility = () => {
        if (window.scrollY > 500) {
            setIsVisible(true);
        } else {
            setIsVisible(false);
        }
    };

    //Set the top coordinate to 0
    //Make the page scroll to the top
    const scrollToTop = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    };

    useEffect(() => {
        window.addEventListener("scroll", toggleVisibility);
        return () => window.removeEventListener("scroll", toggleVisibility);
    }, []);

    return (
        <div>
            {isVisible &&
            <button onClick={scrollToTop} className="top-btn">
                <FontAwesomeIcon icon={faAngleUp}></FontAwesomeIcon>
            </button>}
        </div>
    );
};

export default GoToTopButton;