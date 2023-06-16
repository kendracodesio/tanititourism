import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import ListingComponent from "../components/ListingComponent";

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
    stayType?: {id: number; name: string;};
    dineType?: {id: number; name: string;};
    acceptsReservations?: string;
}


function ListingDetails() {
    let {id} = useParams();
    const apiURL = process.env.REACT_APP_API_URL;
    const [listing, setListing] = useState<Listing | null> (null);

    useEffect(() => {
        axios.get(`${apiURL}/admin/things-to-do/listing-detail/${id}`)
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
    }, [id, apiURL]);



    return (
        <div>
        <h2>Listing Details</h2>
            <div>{listing ? <ListingComponent listing={listing}/> : 'There was a problem loading the listing details. Try again later.'}</div>
        </div>
    );
}
export default ListingDetails;