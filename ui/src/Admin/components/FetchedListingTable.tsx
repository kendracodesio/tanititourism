import React, {useEffect, useState} from 'react';
import ListingTable from "./ListingTable";
import {Link} from "react-router-dom";
import Pagination from "../../components/Pagination"
import axiosInstance from "../axiosInstance";

interface GetListingTableProps {
    apiEndpoint: string;
    deleteEndpoint: string;
    addLink: string;
    tableName: string;
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
    stayType?: { id: number; typeName: string; };
    dineType?: { id: number; typeName: string; };

}

function FetchedListingTable({apiEndpoint, deleteEndpoint, typeFieldName, addLink, tableName}: GetListingTableProps) {
    const apiURL = process.env.REACT_APP_API_URL;
    const [listings, setListings] = useState<ListingData[]>([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState<number>();

    useEffect(() => {
        axiosInstance.get(`${apiURL}${apiEndpoint}?page=${page}`)
            .then(response => {
                setListings(response.data.content);
                setPageSize(response.data.size);
                setTotalPages(response.data.totalPages);
            })
            .catch(error => {
                console.error(`Error fetching data: ${error}`);
            });
    }, [apiURL, apiEndpoint, page, pageSize]);

    const handlePrevious = () => {
        setPage(prevPage => prevPage > 1 ? prevPage -1 :prevPage);
    };

    const handleNext = () => {
        setPage(prevPage => prevPage < totalPages ? prevPage + 1 : prevPage);
    };


    const handleDelete = (id: number) => {
        axiosInstance.delete(`${apiURL}${deleteEndpoint}/${id}`)
            .then(response => {
                setListings(listings.filter(listing => listing.id !== id));
            })
            .catch(err => {
                console.log("Delete failed", err);
            });
    }

    return (

            <div>
                <div className="mt-5">
                    <div className="d-flex justify-content-start">
                        <h1 className="ms-3"> Listings: {tableName}</h1>
                        <Link to={addLink} className="ms-5 mt-2">Add New Listing</Link>
                    </div>
                </div>
                <ListingTable listings={listings}
                              deleteListing={handleDelete}
                              typeFieldName={typeFieldName}/>

                <Pagination page={page}
                            totalPages={totalPages}
                            handlePrevious={handlePrevious}
                            handleNext={handleNext}/>
            </div>



    );


}

export default FetchedListingTable;