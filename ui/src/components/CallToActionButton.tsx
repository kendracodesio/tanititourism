import React from 'react';
import { useNavigate } from 'react-router-dom';

function CallToActionButton () {
    const navigate = useNavigate();
    return (
        <button className="cta-btn" onClick={() => navigate('/stay')}>
            BOOK <br />
            A <br />
            STAY
        </button>
    );
}

export default CallToActionButton;