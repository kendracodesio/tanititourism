import React from 'react';
import GetListingDetails from "../components/GetListingDetails";

function StayListingDetails() {
    return (
        <div className="admin-main-content">
        <GetListingDetails apiEndpoint={"/admin/places-to-stay/listing-detail"}
                           editLink={"/admin/stay-listings/listing-detail/edit"}/>
        </div>
    );
}
export default StayListingDetails;