import React, {useEffect, useState} from 'react';
import axios from "axios";
import ListingTable from "./ListingTable";

interface GetListingTableProps {
    apiEndpoint: string;
    deleteEndpoint: string;
    typeFieldName: "doTypes" | "stayType" | "dineType";
}

interface ListingData {
    id: number
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;
    region: { id: number; name: string; };
    doTypes?: { id: number; typeName: string; }[];
    stayType?: {id: number; typeName: string;};
    dineType?: {id: number; typeName: string;};

}

function GetListingTable({apiEndpoint, deleteEndpoint, typeFieldName}:GetListingTableProps) {
    const apiURL = process.env.REACT_APP_API_URL;
    const [listings, setListings] = useState<ListingData[]>([]);
    useEffect(() => {
        axios.get(`${apiURL}${apiEndpoint}`)
            .then(response => {
                setListings(response.data);
            })
            .catch(error => {
                console.error(`Error fetching data: ${error}`);
            });
    }, [apiURL, apiEndpoint]);

    const handleDelete = (id: number) => {
        axios.delete(`${apiURL}${deleteEndpoint}/${id}`)
            .then(response => {
                setListings(listings.filter(listing =>  listing.id !== id));
            })
            .catch(err => {
                console.log("Delete failed", err);
            });
    }

    return (
        <div>
            <ListingTable listings={listings}
                          deleteListing={handleDelete}
                          typeFieldName={typeFieldName}/>
        </div>

    );


}

export default GetListingTable;