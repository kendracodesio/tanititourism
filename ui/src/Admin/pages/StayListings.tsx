import React from 'react';
import GetListingTable from "../components/GetListingTable";

function StayListings() {
    return (
        <div className="admin-main-content">
        <GetListingTable apiEndpoint={"/admin/places-to-stay/list"}
                         deleteEndpoint={"/admin/places-to-stay/delete-listing"}
                         addLink={"/admin/stay-listings/add"}
                         tableName={"Places To Stay"}
                         typeFieldName={"stayType"}/>
        </div>

    );

}
export default StayListings;