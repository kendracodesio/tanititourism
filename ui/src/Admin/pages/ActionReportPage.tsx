import React, {useEffect, useState} from 'react';
import Pagination from "../../components/Pagination";
import axiosInstance from "../axiosInstance";
import ReportTable, {IActionReport} from "../components/ReportTable";
import {faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";



function ActionReportPage() {
    const apiURL = process.env.REACT_APP_API_URL;
    const [actionReport, setActionReport] = useState<IActionReport[]>([]);
    const [searchQuery, setSearchQuery] = useState("");
    const [isSearching, setIsSearching] = useState(false);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState<number>();


    useEffect(() => {
        if (!isSearching) {
            axiosInstance.get(`${apiURL}/admin/api/action-reports?page=${page}`)
                .then(response => {
                    setActionReport(response.data.content);
                    setPageSize(response.data.size);
                    setTotalPages(response.data.totalPages);
                })
                .catch(error => {
                    console.error(`Error fetching data: ${error}`);
                });
        }
    }, [apiURL, page, pageSize, isSearching]);

    const handlePrevious = () => {
        setPage(prevPage => prevPage > 1 ? prevPage - 1 : prevPage);
    };

    const handleNext = () => {
        setPage(prevPage => prevPage < totalPages ? prevPage + 1 : prevPage);
    };

    const handleFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const fieldName = event.target.name;
        const fieldValue = event.target.value;

        if (fieldName === "searchQuery") {
            setSearchQuery(fieldValue);
        }
    };


    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        if (!searchQuery) return;
        setIsSearching(true);

        axiosInstance.get(`${apiURL}/admin/api/search?searchQuery=${searchQuery}`)
            .then(response => {
                if (response.data) {
                    setActionReport(response.data);
                }
            })
            .catch(error => {
                setIsSearching(false);
                console.error(`Error fetching data: ${error}`);
            });
    };

    const goBack = () => {
        setIsSearching(false);
        setSearchQuery("");
        setPage(1);
    }


    return (
        <div className="admin-main-content">
            <div className="mt-5">
                <div className="d-flex justify-content-between">
                    <h1 className="ms-3">Action Report</h1>
                    <form className="search-area d-flex justify-content-end me-3" role="search" onSubmit={handleSubmit}>
                        <div>
                            <input className="form-control search-input" placeholder="Search by title"
                                   aria-label="Search" name="searchQuery" onChange={handleFieldChange}/>
                        </div>
                        <div className="search-btn ms-1">
                            <button className="btn search" type="submit"><FontAwesomeIcon className="search-icon"
                                                                                          icon={faMagnifyingGlass}></FontAwesomeIcon>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div className="p-2">
            <ReportTable reportedActions={actionReport} showUser={true}/>
            </div>

            {isSearching &&
                <button onClick={goBack} className="mt-5 ms-5">Back To Full Report</button>
            }

            <div className="pb-4 me-5">
            {totalPages > 1 && !isSearching &&
                <Pagination page={page}
                            totalPages={totalPages}
                            handlePrevious={handlePrevious}
                            handleNext={handleNext}/>
            }

            </div>

        </div>
    );
}

export default ActionReportPage;