import React from 'react';
import FetchedListingDetail from "../../components/FetchedListingDetail";

function DineListingDetails() {
    return (
        <div className="admin-main-content">
            <FetchedListingDetail apiEndpoint={"/admin/restaurants-and-nightlife/listing-detail"}
                                  editLink={"/admin/dine-listings/listing-detail/edit"}
                                  backTo={"/admin/dine-listings"}/>
        </div>
    );
}
export default DineListingDetails;
