import React from 'react';
import FetchedListingTable from "../../components/FetchedListingTable";



function DoListings() {
    return (
        <div className="admin-main-content">
        <FetchedListingTable apiEndpoint={"/admin/api/things-to-do/list"}
                             deleteEndpoint={"/admin/api/things-to-do/delete-listing"}
                             addLink={"/admin/do-listings/add"}
                             tableName={"Things To Do"}
                             typeFieldName={"doTypes"}/>
        </div>

    );
}
export default DoListings;