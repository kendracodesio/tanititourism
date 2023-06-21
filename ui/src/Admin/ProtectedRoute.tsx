import {Route, useNavigate} from 'react-router-dom';
import {ReactNode, useEffect} from "react";

interface ProtectedRouteProps {
    path: string;
    element: ReactNode;

}

function ProtectedRoute ({path, element}: ProtectedRouteProps) {
    const navigate = useNavigate();
    const token = localStorage.getItem('token');

    useEffect(() => {
        if (!token) {
            navigate('/');
        }
    }, [navigate, token]);

    return (
        token ? <Route path={path} element={element}/> : null
    );

}
export default ProtectedRoute;