import {Routes, Route} from 'react-router-dom';
import AdminSidebar from './components/AdminSidebar';
import StayListings from './pages/StayListings';
import Reports from './pages/Reports';
import AdminHome from './pages/AdminHome';
import DineListings from "./pages/DineListings";
import ListingDetails from "./pages/ListingDetails";
import DoListings from "./pages/DoListings";
import DoListingForm from "./components/DoListingForm";




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
                    <Route path="do-listings/listing-detail/edit/:id" element={<DoListingForm />}/>
                    <Route path="do-listings/add" element={<DoListingForm />}/>
                </Routes>
            </div>
        </div>
    );
}

export default AdminConsole;
