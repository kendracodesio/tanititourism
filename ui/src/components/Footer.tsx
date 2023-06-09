import React from 'react';
import SocialLinks from "./SocialLinks";

function Footer () {
    return (
        <footer>
            <div className="line-full"></div>
            <div className="container">
                <div className="d-flex justify-content-between">
                    <div className="about-footer pt-5 pb-5">
                        <h4 className="text-uppercase">about our island</h4>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ullamcorper nulla posuere dui
                            tristique
                            posuere. Aenean nec
                            finibus arcu, non vestibulum est. Mauris tempus libero neque, eget egestas elit euismod
                            vitae.
                        </p>
                    </div>
                    <div className="pt-5">
                        <h4 className="text-uppercase">for more info</h4>
                        <p className="text-uppercase phone">call (888)-555-5555</p>
                        <h4 className="text-uppercase pt-5">connect with us</h4>
                        <SocialLinks />
                    </div>
                </div>
                    <p className="copyright pt-5 ps-2 pb-2">© 2023 Taniti Office of Tourism.</p>
                </div>
        </footer>
    )
}
export default Footer;