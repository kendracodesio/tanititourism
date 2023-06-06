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
                    <a href="https://twitter.com/visit-taniti" target="_blank" title="Twitter Profile">
                        <FontAwesomeIcon icon={faTwitter}></FontAwesomeIcon>
                        {/*<i className="fa-brands fa-twitter"></i>*/}
                    </a>
                </li>
                <li>
                    <a href="https://facebook.com/visit-taniti" target="_blank" title="Facebook Profile">
                        <FontAwesomeIcon icon={faFacebook}></FontAwesomeIcon>
                        {/*<i className="fa-brands fa-facebook"></i>*/}
                    </a>
                </li>
                <li>
                    <a href="https://instagram.com/visit-taniti" target="_blank" title="Instagram Profile">
                        <FontAwesomeIcon icon={faInstagram}></FontAwesomeIcon>
                        {/*<i className="fa-brands fa-instagram"></i>*/}
                    </a>
                </li>
                <li>
                    <a href="https://pinterest.com/visit-taniti" target="_blank" title="Pinterest Profile">
                        <FontAwesomeIcon icon={faPinterest}></FontAwesomeIcon>
                        {/*<i className="fa-brands fa-pinterest"></i>*/}
                    </a>
                </li>
                <li>
                    <a href="mailto:info@visit-taniti.com" target="_blank" title="Email">
                        <FontAwesomeIcon icon={faEnvelope}></FontAwesomeIcon>
                        {/*<i className="fa-solid fa-envelope"></i>*/}
                    </a>
                </li>
            </ul>
        </div>
    );
}

export default SocialLinks;