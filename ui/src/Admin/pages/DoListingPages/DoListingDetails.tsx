import React from 'react';
import FetchedListingDetail from "../../components/FetchedListingDetail";

function DoListingDetails() {
    return (
        <div className="admin-main-content">
      <FetchedListingDetail apiEndpoint={"/admin/api/things-to-do/listing-detail"}
                            editLink={"/admin/do-listings/listing-detail/edit"}/>
    </div>
    );
}
export default DoListingDetails;