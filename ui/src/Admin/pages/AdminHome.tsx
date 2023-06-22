import React, {useEffect, useState} from 'react';
import axiosInstance from "../axiosInstance";
import ReportTable, { IActionReport } from "../components/ReportTable";

function AdminHome() {
    const [firstName, setFirstName] = useState('');
    const [recentActivity, setRecentActivity] = useState<IActionReport[]>([])
    const apiURL = process.env.REACT_APP_API_URL;

    useEffect(() => {
        const username = localStorage.getItem('username');
        axiosInstance.get(`${apiURL}/admin/api/user?username=${username}`)
            .then(response => {
                if (response.data) {
                    setFirstName(response.data);
                }
            })
            .catch(error => {
                console.error(`Error fetching user data: ${error}`);
            });
    }, [apiURL]);

    useEffect(() => {
        const username = localStorage.getItem('username');
        axiosInstance.get(`${apiURL}/admin/api/user-recent-activity?username=${username}`)
            .then(response => {
                console.log(response.data);
                setRecentActivity(response.data);
            })
            .catch(error => {
                console.error(`Error fetching data: ${error}`);
            });
    }, [apiURL]);

    return (
        <div className="container admin-main-content">
            <h2 className="admin-title ms-5">Welcome to the Admin Home Page</h2>
            <h3 className="admin-home">Hello {firstName}! </h3>
            <h3 className="admin-home">Here's your most recent activity:</h3>

            <div style={{width: '80%', margin: '0 auto'}}>
            <ReportTable reportedActions={recentActivity} showUser={false}/>
            </div>

        </div>

    );
}

export default AdminHome;
