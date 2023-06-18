import React from 'react';
import GetListingDetails from "../components/GetListingDetails";

function DoListingDetails() {
    return (
        <div className="admin-main-content">
      <GetListingDetails apiEndpoint={"/admin/things-to-do/listing-detail"}
                         editLink={"/admin/do-listings/listing-detail/edit"}  />
    </div>
    );
}
export default DoListingDetails;