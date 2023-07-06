import React from 'react';
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";

export interface ThingToDo {
    id: number;
    name: string;

}

export interface PlaceToStay {
    id: number;
    name: string;

}

export interface RestaurantsAndNightlife {
    id: number;
    name: string;

}

export interface User {
    id: number;
    firstName: string;
}
export interface IActionReport {
    id: number;
    action: string;
    timestamp: string;
    thingToDo?: ThingToDo;
    placeToStay?: PlaceToStay;
    restaurantsAndNightlife?: RestaurantsAndNightlife;
    user?: User;
}
interface ReportTableProps {
    reportedActions: IActionReport[];
    showUser: boolean;
}

function ReportTable({reportedActions, showUser}: ReportTableProps) {
    return (
        <Table striped>
            <thead>
            <tr>
                <th>Action</th>
                <th>Listing Type</th>
                <th>Listing Title</th>
                {showUser && <th>User</th>}
                <th>Date & Time</th>
            </tr>
            </thead>
            <tbody>
            {reportedActions?.map((reportedAction, index) => {
                let listingType, listingTitle, listingId, link;
                if (reportedAction.thingToDo) {
                    listingType = "Things To Do";
                    listingTitle = reportedAction.thingToDo.name;
                    listingId = reportedAction.thingToDo.id;
                    link = "do-listings";
                } else if (reportedAction.placeToStay) {
                    listingType = "Places To Stay";
                    listingTitle = reportedAction.placeToStay.name;
                    listingId = reportedAction.placeToStay.id;
                    link = "stay-listings";
                } else if (reportedAction.restaurantsAndNightlife) {
                    listingType = "Restaurants And Nightlife"
                    listingTitle = reportedAction.restaurantsAndNightlife.name;
                    listingId = reportedAction.restaurantsAndNightlife.id;
                    link = "dine-listings";
                }
                let date = new Date(reportedAction.timestamp + 'Z');
                return (
                    <tr key={index}>
                        <td>{reportedAction.action}</td>
                        <td>{listingType}</td>
                        <td><Link to={`/admin/${link}/listing-detail/${listingId}`}>{listingTitle}</Link></td>
                        {showUser && <td>{reportedAction.user?.firstName}</td>}
                        <td>{date.toLocaleString()}</td>
                    </tr>
                );
            })}
            </tbody>
        </Table>
    )
}
export default ReportTable;