import React from 'react';
import GetListingTable from "../components/GetListingTable";
import {Link} from "react-router-dom";

function DoListings() {
    return (
        <div className="mt-5">
        <div className="d-flex justify-content-start">
            <h1 className="ms-3"> Listings: Things To Do</h1>
            <Link to={`/admin/do-listings/add`} className="ms-4 mb-3">Add New Listing</Link>
        </div>

        <GetListingTable apiEndpoint={"/admin/things-to-do/list"}
                         deleteEndpoint={"/admin/things-to-do/delete-listing"}
                         typeFieldName={"doTypes"}/>
        </div>
    );
}
export default DoListings;