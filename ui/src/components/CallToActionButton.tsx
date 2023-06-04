import React from 'react';

const CallToActionButton = () => {
    return (
        <button className="cta-btn" onClick={() => window.location.href='stay.html'}>
            BOOK A STAY
        </button>
    );
};

export default CallToActionButton;