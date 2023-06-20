import React from 'react';
import FetchedListingDetail from "../../components/FetchedListingDetail";

function StayListingDetails() {
    return (
        <div className="admin-main-content">
        <FetchedListingDetail apiEndpoint={"/admin/places-to-stay/listing-detail"}
                              editLink={"/admin/stay-listings/listing-detail/edit"}
                              backTo={"/admin/stay-listings"}/>
        </div>
    );
}
export default StayListingDetails;