import React, {useEffect, useState} from 'react';
import Pagination from "../../components/Pagination";
import axiosInstance from "../axiosInstance";
import ReportTable, {IActionReport, PlaceToStay, RestaurantsAndNightlife, ThingToDo, User} from "../components/ReportTable";
import {Link} from "react-router-dom";




function ActionReportPage() {
    const apiURL = process.env.REACT_APP_API_URL;
    const [actionReport, setActionReport] = useState<IActionReport[]>([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState<number>();


    useEffect(() => {
        axiosInstance.get(`${apiURL}/admin/api/action-reports?page=${page}`)
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
            <div className="mt-5">
                <div className="d-flex justify-content-start">
                    <h1 className="ms-3">Action Report</h1>
                    {/*<Link to={addLink} className="ms-5 mt-2">Add New Listing</Link>*/}
                </div>
            </div>
           <ReportTable reportedActions={actionReport} showUser={true}/>


            <Pagination page={page}
                        totalPages={totalPages}
                        handlePrevious={handlePrevious}
                        handleNext={handleNext}/>
        </div>
    );
}
export default ActionReportPage;