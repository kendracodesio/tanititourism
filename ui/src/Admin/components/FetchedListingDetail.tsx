import React, {useEffect, useState} from 'react';
import {Link, useParams, useLocation} from "react-router-dom";
import axios from "axios";
import ListingDetail from "./ListingDetail";
import {Alert} from "react-bootstrap";

interface ListingDetailsProps {
    apiEndpoint: string;
    editLink: string;
    backTo: string;
}


interface Listing {
    id: number
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;
    region: {id: number; name: string;};
    doTypes?: {id: number; typeName: string;}[];
    stayType?: {id: number; typeName: string;};
    dineType?: {id: number; typeName: string;};
    acceptsReservations?: string;
}


function FetchedListingDetail({apiEndpoint, editLink, backTo}: ListingDetailsProps) {
    let {id} = useParams();
    const apiURL = process.env.REACT_APP_API_URL;
    const [listing, setListing] = useState<Listing | null> (null);
    const location = useLocation();


    useEffect(() => {
        axios.get(`${apiURL}${apiEndpoint}/${id}`)
            .then(response => {
                if(response.data !== null) {
                    setListing(response.data);
                } else {
                    console.log(`No listing found with id: ${id}`);
                }
            })
            .catch(error => {
                console.error(`Error fetching data: ${error}`);
            });
    }, [id, apiURL, apiEndpoint]);



    return (
        <div>
        <h2>Listing Details</h2>
            {location.state && location.state.successMessage &&
                <Alert variant="success" className="mt-3">{location.state.successMessage}</Alert>
            }
            <div>{listing ?
                <>
                <ListingDetail listing={listing} editLink={editLink}/>
                </> : 'There was a problem loading the listing details. Try again later.'}</div>

            <Link to={`${backTo}`} className="mt-5 ms-5">Back To Listings</Link>
        </div>
    );
}


export default FetchedListingDetail;