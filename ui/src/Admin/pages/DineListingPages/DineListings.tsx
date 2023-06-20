import React from 'react';
import FetchedListingTable from "../../components/FetchedListingTable";


function DineListings() {
    return (
        <div className="admin-main-content">
            <FetchedListingTable apiEndpoint={"/admin/restaurants-and-nightlife/list"}
                                 deleteEndpoint={"/admin/restaurants-and-nightlife/delete-listing"}
                                 addLink={"/admin/dine-listings/add"}
                                 tableName={"Restaurants And Nightlife"}
                                 typeFieldName={"dineType"}/>
        </div>

    );

}

export default DineListings;