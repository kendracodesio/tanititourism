import {Routes, Route} from 'react-router-dom';
import AdminSidebar from './components/AdminSidebar';
import StayListings from './pages/StayListings';
import DoListings from './pages/DoListings';
import Reports from './pages/Reports';
import AdminHome from './pages/AdminHome';
import DineListings from "./pages/DineListings";
import ListingDetails from "./pages/ListingDetails";




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
                    <Route path="do-listings/listing-detail/:id" element={<ListingDetails />} />
                </Routes>
            </div>
        </div>
    );
}

export default AdminConsole;
