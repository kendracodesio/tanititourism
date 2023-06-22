import {Navigate, Outlet} from 'react-router-dom';



function ProtectedRoutes () {
    let auth = (localStorage.getItem('token'));
    return auth ? <Outlet/> : <Navigate to={"/admin"}/>;
}
export default ProtectedRoutes;