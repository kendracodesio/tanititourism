import React, {useEffect, useState} from 'react';
import axios from "axios";
import ListingTable from "../components/ListingTable";

interface Listing {
    id: number
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;
    region: { id: number; name: string; };
    doTypes: { id: number; typeName: string; }[];

}

function DoListings() {
    const apiURL = process.env.REACT_APP_API_URL;
    const [listings, setListings] = useState<Listing[]>([]);
    useEffect(() => {
        axios.get(`${apiURL}/admin/things-to-do/list`)
            .then(response => {
                setListings(response.data);
            })
            .catch(error => {
                console.error(`Error fetching data: ${error}`);
            });
    }, [apiURL]);

    return (
        <div>
            <h2>Listings: Things To Do</h2>
            <ListingTable listings={listings}
                          typeFieldName={"doTypes"}/>
        </div>

    );


}

export default DoListings;