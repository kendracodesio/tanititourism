import React from 'react';
import GetListingTable from "../components/GetListingTable";

function StayListings() {
    return (
        <GetListingTable apiEndpoint={"/admin/places-to-stay/list"}
                         deleteEndpoint={"/admin/places-to-stay/delete-listing"}
                         typeFieldName={"stayType"}/>

    );

}
export default StayListings;