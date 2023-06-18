import React from 'react';
import GetListingTable from "../components/GetListingTable";



function DoListings() {
    return (
        <div className="admin-main-content">
        <GetListingTable apiEndpoint={"/admin/things-to-do/list"}
                         deleteEndpoint={"/admin/things-to-do/delete-listing"}
                         addLink={"/admin/do-listings/add"}
                         tableName={"Things To Do"}
                         typeFieldName={"doTypes"}/>
        </div>

    );
}
export default DoListings;