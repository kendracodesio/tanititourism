import React from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faTwitter} from '@fortawesome/free-brands-svg-icons'
import {faFacebook} from '@fortawesome/free-brands-svg-icons'
import {faInstagram} from '@fortawesome/free-brands-svg-icons'
import {faPinterest} from '@fortawesome/free-brands-svg-icons'
import {faEnvelope} from '@fortawesome/free-solid-svg-icons'

const SocialLinks = () => {
    return (
        <div className=" text-center">
            <ul className="social-links">
                <li>
                    <a href="https://twitter.com/visit-taniti" target="_blank"  rel="noreferrer"  title="Twitter Profile">
                        <FontAwesomeIcon icon={faTwitter}></FontAwesomeIcon>
                    </a>
                </li>
                <li>
                    <a href="https://facebook.com/visit-taniti" target="_blank"  rel="noreferrer" title="Facebook Profile">
                        <FontAwesomeIcon icon={faFacebook}></FontAwesomeIcon>
                    </a>
                </li>
                <li>
                    <a href="https://instagram.com/visit-taniti" target="_blank"  rel="noreferrer" title="Instagram Profile">
                        <FontAwesomeIcon icon={faInstagram}></FontAwesomeIcon>
                    </a>
                </li>
                <li>
                    <a href="https://pinterest.com/visit-taniti" target="_blank"  rel="noreferrer" title="Pinterest Profile">
                        <FontAwesomeIcon icon={faPinterest}></FontAwesomeIcon>
                    </a>
                </li>
                <li>
                    <a href="mailto:info@visit-taniti.com" target="_blank"   rel="noreferrer"  title="Email">
                        <FontAwesomeIcon icon={faEnvelope}></FontAwesomeIcon>
                    </a>
                </li>
            </ul>
        </div>
    );
}

export default SocialLinks;