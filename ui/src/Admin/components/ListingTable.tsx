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
    region: { id: number; name: string; };
    doTypes?: { id: number; typeName: string; }[];
    stayType?: { id: number; typeName: string; };
    dineType?: { id: number; typeName: string; };

}

type TypeFieldName = "doTypes" | "stayType" | "dineType"

interface ListingTableProps {
    listings: Listing[];
    typeFieldName: TypeFieldName;
    deleteListing: (id: number) => void;
}

function ListingTable({listings, typeFieldName, deleteListing}: ListingTableProps) {

    return (
        <div>
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
                                (listing[typeFieldName] as { id: number; typeName: string; }[]).map((type, idx) => (
                                    <span key={idx}>{type.typeName}{idx < (listing[typeFieldName] as {
                                        id: number;
                                        typeName: string;
                                    }[]).length - 1 ? ', ' : ''}</span>
                                ))
                            ) : (
                                <span>{(listing[typeFieldName] as { id: number; typeName: string; }).typeName}</span>
                            )}
                        </td>
                        <td>
                            <button onClick={() => deleteListing(listing.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>

            </Table>
        </div>
    );
}


export default ListingTable;


