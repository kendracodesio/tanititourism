import {Routes, Route} from 'react-router-dom';
import AdminSidebar from './components/AdminSidebar';
import StayListings from './pages/StayListingPages/StayListings';
import Reports from './pages/Reports';
import AdminHome from './pages/AdminHome';
import DineListings from "./pages/DineListingPages/DineListings";
import DoListings from "./pages/DoListingPages/DoListings";
import DoListingForm from "./pages/DoListingPages/DoListingForm";
import DoListingDetails from "./pages/DoListingPages/DoListingDetails";
import StayListingDetails from "./pages/StayListingPages/StayListingDetails";
import StayListingForm from "./pages/StayListingPages/StayListingForm";




function AdminConsole() {
    return (
        <div style={{display: 'flex'}}>
            <AdminSidebar />
            <div style={{flexGrow: 1}}>
                <Routes>
                    <Route path="/" element={<AdminHome />} />
                    <Route path="do-listings" element={<DoListings/>} />
                    <Route path="stay-listings" element={<StayListings />} />
                    <Route path="dine-listings" element={<DineListings />} />
                    <Route path="reports" element={<Reports/>} />
                    <Route path="do-listings/listing-detail/:id" element={<DoListingDetails  />} />
                    <Route path="do-listings/listing-detail/edit/:id" element={<DoListingForm />}/>
                    <Route path="do-listings/add" element={<DoListingForm />}/>
                    <Route path="stay-listings/listing-detail/:id" element={<StayListingDetails />}/>
                    <Route path="stay-listings/listing-detail/edit/:id" element={<StayListingForm />}/>
                    <Route path="stay-listings/add" element={<StayListingForm/>}/>
                </Routes>
            </div>
        </div>
    );
}

export default AdminConsole;
