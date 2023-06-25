import React from 'react';
import FetchedListingDetail from "../../components/FetchedListingDetail";

function DineListingDetails() {
    return (
        <div className="admin-main-content">
            <FetchedListingDetail apiEndpoint={"/admin/api/restaurants-and-nightlife/listing-detail"}
                                  editLink={"/admin/dine-listings/listing-detail/edit"}/>

        </div>
    );
}
export default DineListingDetails;
