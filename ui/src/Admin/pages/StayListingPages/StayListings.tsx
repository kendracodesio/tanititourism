import React from 'react';
import FetchedListingTable from "../../components/FetchedListingTable";

function StayListings() {
    return (
        <div className="admin-main-content">
        <FetchedListingTable apiEndpoint={"/admin/places-to-stay/list"}
                             deleteEndpoint={"/admin/places-to-stay/delete-listing"}
                             addLink={"/admin/stay-listings/add"}
                             tableName={"Places To Stay"}
                             typeFieldName={"stayType"}/>
        </div>

    );

}
export default StayListings;