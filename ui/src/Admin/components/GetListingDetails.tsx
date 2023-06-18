import React, {useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import axios from "axios";
import ListingComponent from "./ListingComponent";

interface ListingDetailsProps {
    apiEndpoint: string;
    editLink: string;
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


function GetListingDetails({apiEndpoint, editLink}: ListingDetailsProps) {
    let {id} = useParams();
    const apiURL = process.env.REACT_APP_API_URL;
    const [listing, setListing] = useState<Listing | null> (null);


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
            <div>{listing ?
                <>
                <ListingComponent listing={listing}/>
                    <Link to={`${editLink}/${listing.id}`}>Edit</Link>
                </> : 'There was a problem loading the listing details. Try again later.'}</div>
        </div>
    );
}


export default GetListingDetails;