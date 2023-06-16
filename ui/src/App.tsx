import {Routes, Route, useLocation} from 'react-router-dom';

import Navbar from './components/Navbar';
import CallToActionButton from "./components/CallToActionButton";
import Home from './pages/HomePage/Home';
import GoToTopButton from "./components/GoToTopButton";
import Footer from "./components/Footer";
import Do from "./pages/DoPage/Do";
import Stay from "./pages/StayPage/Stay";
import Dine from "./pages/DinePage/Dine";
import Plan from "./pages/PlanPage/Plan";
import {useEffect} from "react";
import { Outlet } from 'react-router-dom';
import AdminConsole from "./Admin/AdminConsole";


const useScrollToTop = () => {
    const { pathname } = useLocation();

    useEffect(() => {
        window.scrollTo(0,0);
    }, [pathname]);
};



// Public layout component
const PublicLayout = () => {
    return (
        <div className="App">
            <Navbar />
            <CallToActionButton />
            <Outlet />  {/* This will render the child routes */}
            <GoToTopButton />
            <Footer />
        </div>
    );
}



function App() {
    useScrollToTop();
    return (
            <Routes>
                <Route path="/" element={<PublicLayout />}>
                    <Route index element={<Home />} />
                    <Route path="/do" element={<Do />} />
                    <Route path="/stay" element={<Stay />} />
                    <Route path="/dine" element={<Dine />} />
                    <Route path="/plan" element={<Plan />} />
                </Route>
                <Route path="/admin/*" element={<AdminConsole />} />
            </Routes>
    );
}
export default App;


