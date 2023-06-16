import React from 'react';
import Table from 'react-bootstrap/Table';
import {Link} from "react-router-dom";


interface Listing {
    id: number
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;
    region: {id: number; name: string;};
    doTypes?: {id: number; typeName: string;}[];
    stayType?: {id: number; name: string;};
    dineType?: {id: number; name: string;};

}

type TypeFieldName = "doTypes" | "stayType" | "dineType"

interface ListingTableProps {
    listings: Listing[];
    typeFieldName: TypeFieldName;
}
function ListingTable({listings, typeFieldName}: ListingTableProps) {
    return (
        <Table striped>
            <thead>
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Cost</th>
                <th>Region</th>
                <th>Type</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            {listings.map((listing, index) => (
                <tr key={index}>
                    <td>
                        <img src={listing.imageUrl} alt={listing.imageAltText} style={{maxWidth: '100px'}}/>
                    </td>
                    <td><Link to={`listing-detail/${listing.id}`}>{listing.name}</Link></td>
                    <td>{listing.phone}</td>
                    <td>{listing.cost}</td>
                    <td>{listing.region.name}</td>
                    <td>
                        {Array.isArray(listing[typeFieldName]) ? (
                            (listing[typeFieldName] as {id: number; typeName: string;}[]).map((type, idx) => (
                                <span key={idx}>{type.typeName}{idx < (listing[typeFieldName] as {id: number; typeName: string;}[]).length - 1 ? ', ' : ''}</span>
                            ))
                        ) : (
                            <span>{(listing[typeFieldName] as {id: number; name: string;}).name}</span>
                        )}
                    </td>
                    <td>
                        <button onClick={() => handleDelete(listing.id)}>Delete</button>
                    </td>
                </tr>
            ))}
            </tbody>
        </Table>
    );
}

function handleDelete(id: number) {
    //TODO make a DELETE request to the API
}
export default ListingTable;


