import {Routes, Route, useLocation} from 'react-router-dom';
import ProtectedRoutes from './ProtectedRoutes';
import AdminSidebar from './components/AdminSidebar';
import StayListings from './pages/StayListingPages/StayListings';
import ActionReportPage from './pages/ActionReportPage';
import AdminHome from './pages/AdminHome';
import DineListings from "./pages/DineListingPages/DineListings";
import DoListings from "./pages/DoListingPages/DoListings";
import DoListingForm from "./pages/DoListingPages/DoListingForm";
import DoListingDetails from "./pages/DoListingPages/DoListingDetails";
import StayListingDetails from "./pages/StayListingPages/StayListingDetails";
import StayListingForm from "./pages/StayListingPages/StayListingForm";
import DineListingForm from "./pages/DineListingPages/DineListingForm";
import DineListingDetails from "./pages/DineListingPages/DineListingDetails";
import React from "react";
import SignIn from "./pages/SignIn";


function AdminConsole() {

    const location = useLocation();
    return (
        <div style={{display: 'flex'}}>
            {/* Render AdminSidebar only if the path is not /admin */}
            {location.pathname !== "/admin" && <AdminSidebar/>}
            <div style={{flexGrow: 1}}>
                <Routes>
                    <Route path="/" element={<SignIn/>}/>
                    <Route element={<ProtectedRoutes/>}>
                        <Route path="home" element={<AdminHome/>}/>
                        <Route path="do-listings" element={<DoListings/>}/>
                        <Route path="stay-listings" element={<StayListings/>}/>
                        <Route path="dine-listings" element={<DineListings/>}/>
                        <Route path="action-report" element={<ActionReportPage/>}/>
                        <Route path="do-listings/listing-detail/:id" element={<DoListingDetails/>}/>
                        <Route path="do-listings/listing-detail/edit/:id" element={<DoListingForm/>}/>
                        <Route path="do-listings/add" element={<DoListingForm/>}/>
                        <Route path="stay-listings/listing-detail/:id" element={<StayListingDetails/>}/>
                        <Route path="stay-listings/listing-detail/edit/:id" element={<StayListingForm/>}/>
                        <Route path="stay-listings/add" element={<StayListingForm/>}/>
                        <Route path="dine-listings/listing-detail/:id" element={<DineListingDetails/>}/>
                        <Route path="dine-listings/listing-detail/edit/:id" element={<DineListingForm/>}/>
                        <Route path="dine-listings/add" element={<DineListingForm/>}/>
                    </Route>
                </Routes>
            </div>
        </div>
    );
}

export default AdminConsole;
