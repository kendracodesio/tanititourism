import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHotel } from '@fortawesome/free-solid-svg-icons'
import { faUtensils } from '@fortawesome/free-solid-svg-icons'
import { faPersonHiking } from '@fortawesome/free-solid-svg-icons'
import { useNavigate } from 'react-router-dom';



function Discover () {
    const navigate = useNavigate();
    return(
        <div className="discover">
            <div className="d-flex justify-content-center pb-5 align-items-center pt-5 ">
                <div className="divider"></div>
                <h2 className="text-uppercase">discover taniti</h2>
                <div className="divider"></div>
            </div>

            <div className="d-flex justify-content-around ">
                <div className="text-center">
                    <FontAwesomeIcon className="hotel-icon" icon={faHotel}></FontAwesomeIcon>
                    <h3 className="pt-3">STAY</h3>
                    <div className="line"></div>
                </div>
                <div className="text-center">
                    <FontAwesomeIcon className="eat-icon" icon={faUtensils}></FontAwesomeIcon>
                    <h3 className="pt-3">EAT</h3>
                    <div className="line"></div>
                </div>
                <div className="text-center">
                    <FontAwesomeIcon className="do-icon" icon={faPersonHiking}></FontAwesomeIcon>
                    <h3 className="pt-3">DO</h3>
                    <div className="line"></div>
                </div>
            </div>

            <div className="container">
                <div className="row text-center d-flex justify-content-around">
                    <div className="col-3">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus vitae tristique dolor, vitae
                            finibus
                            magna
                        </p>
                        <button className="btn btn-custom btn-lg text-uppercase pt-3" onClick={() => navigate('/stay')}>read
                            more
                        </button>
                    </div>
                    <div className="col-3 ">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus vitae tristique dolor, vitae
                            finibus
                            magna
                        </p>
                        <button className="btn btn-custom btn-lg text-uppercase pt-3"
                                onClick={() =>  navigate('/dine')}>read
                            more
                        </button>
                    </div>
                    <div className="col-3 ">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus vitae tristique dolor, vitae
                            finibus
                            magna
                        </p>
                        <button className="btn btn-custom btn-lg text-uppercase pt-3" onClick={() =>  navigate('/do')}>read
                            more
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default Discover;