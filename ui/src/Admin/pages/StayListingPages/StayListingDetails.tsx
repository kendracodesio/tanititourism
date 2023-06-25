import React from 'react';
import FetchedListingDetail from "../../components/FetchedListingDetail";

function StayListingDetails() {
    return (
        <div className="admin-main-content">
        <FetchedListingDetail apiEndpoint={"/admin/api/places-to-stay/listing-detail"}
                              editLink={"/admin/stay-listings/listing-detail/edit"}/>
        </div>
    );
}
export default StayListingDetails;