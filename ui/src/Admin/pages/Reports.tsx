import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import axios from "axios";
import Pagination from "../../components/Pagination";

interface ThingToDo {
    id: number;
    name: string;

}

interface PlaceToStay {
    id: number;
    name: string;

}

interface RestaurantsAndNightlife {
    id: number;
    name: string;

}

interface User {
    id: number;
    firstName: string;
}

interface ActionReport {
    id: number;
    action: string;
    timestamp: string;
    thingToDo?: ThingToDo;
    placesToStay?: PlaceToStay;
    restaurantsAndNightlife?: RestaurantsAndNightlife;
    user: User;
}

function Reports() {
    const apiURL = process.env.REACT_APP_API_URL;
    const [actionReport, setActionReport] = useState<ActionReport[]>([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState<number>();


    useEffect(() => {
        axios.get(`${apiURL}/admin/api/action-reports?page=${page}`)
            .then(response => {
                setActionReport(response.data.content);
                setPageSize(response.data.size);
                setTotalPages(response.data.totalPages);
            })
            .catch(error => {
                console.error(`Error fetching data: ${error}`);
            });
    }, [apiURL, page, pageSize]);

    const handlePrevious = () => {
        setPage(prevPage => prevPage > 1 ? prevPage - 1 : prevPage);
    };

    const handleNext = () => {
        setPage(prevPage => prevPage < totalPages ? prevPage + 1 : prevPage);
    };

    return (
        <div className="admin-main-content">
            <Table striped>
                <thead>
                <tr>
                    <th>Action</th>
                    <th>Listing Type</th>
                    <th>Listing Title</th>
                    <th>User</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                {actionReport.map((report, index) => {
                    let listingType, listingTitle, listingId, link;
                    if (report.thingToDo) {
                        listingType = "Things To Do";
                        listingTitle = report.thingToDo.name;
                        listingId = report.thingToDo.id;
                        link = "do-listings";
                    } else if (report.placesToStay) {
                        listingType = "Places To Stay";
                        listingTitle = report.placesToStay.name;
                        listingId = report.placesToStay.id;
                        link = "stay-listings";
                    } else if (report.restaurantsAndNightlife) {
                        listingType = "Restaurants And Nightlife"
                        listingTitle = report.restaurantsAndNightlife.name;
                        listingId = report.restaurantsAndNightlife.id;
                        link = "dine-listings";
                    }

                    return (
                        <tr key={index}>
                            <td>{report.action}</td>
                            <td>{listingType}</td>
                            <td><Link to={`/admin/${link}/listing-detail/${listingId}`}>{listingTitle}</Link></td>
                            <td>{report.user.firstName}</td>
                            <td>{new Date(report.timestamp).toLocaleDateString()}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>


            <Pagination page={page}
                        totalPages={totalPages}
                        handlePrevious={handlePrevious}
                        handleNext={handleNext}/>
        </div>
    );
}

export default Reports;