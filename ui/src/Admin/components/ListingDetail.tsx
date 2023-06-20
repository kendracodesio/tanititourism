import React from 'react';
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import {Col, Image, Row} from "react-bootstrap";
import {Link} from "react-router-dom";


interface Listing {
    id: number;
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
    acceptsReservations?: string;

}

interface ListingProps {
    listing: Listing;
    editLink: string;
}

function ListingDetail({listing, editLink}: ListingProps) {
    return (
        <Card style={{width: '60rem'}} className="ms-5">
            <ListGroup variant="flush">
                <ListGroup.Item>
                    <Row>
                        Image:
                        <Col>
                            <Image src={listing.imageUrl} thumbnail className="listing-detail-img"/>
                        </Col>
                        <Col>
                            <Link to={`${editLink}/${listing.id}`} className="mt-2 ms-5">Edit</Link>
                        </Col>
                    </Row>
                </ListGroup.Item>
                <ListGroup.Item>Image Url: {listing.imageUrl}</ListGroup.Item>
                <ListGroup.Item>Image Alt Text: {listing.imageAltText}</ListGroup.Item>
                <ListGroup.Item>Listing Title: {listing.name}</ListGroup.Item>
                <ListGroup.Item>Description: {listing.description}</ListGroup.Item>
                <ListGroup.Item>Phone: {listing.phone}</ListGroup.Item>
                <ListGroup.Item>Cost: {listing.cost}</ListGroup.Item>
                <ListGroup.Item>Region: {listing.region.name}</ListGroup.Item>
                {listing.doTypes && listing.doTypes.length > 0 &&
                    <ListGroup.Item>Type(s): {listing.doTypes.map(type => type.typeName).join(', ')}</ListGroup.Item>}
                {listing.stayType &&
                    <ListGroup.Item>Type: {listing.stayType.typeName}</ListGroup.Item>}
                {listing.dineType &&
                    <ListGroup.Item>Type: {listing.dineType.typeName}</ListGroup.Item>}
                {listing.acceptsReservations &&
                    <ListGroup.Item>Accepts Reservations: {listing.acceptsReservations} </ListGroup.Item>}
            </ListGroup>
        </Card>
    );
}
export default ListingDetail;