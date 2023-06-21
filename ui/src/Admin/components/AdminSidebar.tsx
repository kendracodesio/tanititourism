import React, {ReactNode, useState} from 'react';
import { NavLink, useMatch } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown, faCaretRight } from '@fortawesome/free-solid-svg-icons';

interface SidebarLinkProps {
    to: string;
    children: ReactNode;
    end?: boolean;
    marginLeft?: string;
}

function SidebarLink({ to, children, end, marginLeft }: SidebarLinkProps) {
    let match = useMatch({path: to, end: end === undefined ? false : end});

    let className = "nav-link text-white " + (marginLeft || "") + (match ? " active-link" : "");

    return (
        <li className="nav-item">
            <NavLink to={to} className={className}>
                {children}
            </NavLink>
        </li>
    );
}

function ExpandableSidebarLink({ to, children, links }: {to: string, children: ReactNode, links: SidebarLinkProps[]}) {
    let [isOpen, setIsOpen] = useState(false);

    return (
        <>
            <li className="nav-item" onClick={() => setIsOpen(!isOpen)}>
                <div className="nav-link text-white" style={{cursor: 'pointer'}}>
                    {children} {isOpen ? <FontAwesomeIcon icon={faCaretDown} /> : <FontAwesomeIcon icon={faCaretRight} />}
                </div>
            </li>
            {isOpen && links.map(linkProps => <SidebarLink key={linkProps.to} {...linkProps} marginLeft="ms-2" />)}
        </>
    );
}

function AdminSidebar() {


    return (
        <div className="d-flex flex-column flex-shrink-0 p-5 text-white sidebar">
            <img className="navbar-img mx-auto" src="https://tanititourismimages.blob.core.windows.net/images/taniti-logo.png" alt="taniti-logo" />
            <hr/>
            <div className="d-flex justify-content-center align-items-center mb-3 mb-md-0 mx-auto text-white text-decoration-none">
                <span className="fs-6">Admin Console</span>
            </div>
            <hr/>
            <ul className="nav flex-column mb-auto pt-2">
                <div className="mb-4 pt-3">
                    <SidebarLink to="/admin/home" end >Home</SidebarLink>
                </div>
                <div className="mb-4">
                    <ExpandableSidebarLink to="#" links={[
                        {to: "/admin/do-listings", children: 'Things To Do'},
                        {to: "/admin/stay-listings", children: 'Places To Stay'},
                        {to: "/admin/dine-listings", children: 'Restaurants And Nightlife'},
                    ]}>
                        Listings
                    </ExpandableSidebarLink>
                </div>
                <div className="mb-4">
                    <SidebarLink to="/admin/reports">Action Reports</SidebarLink>
                </div>
            </ul>
            <hr/>
            <div className="mt-2">
                <SidebarLink to="/logout" >Logout</SidebarLink>
            </div>
        </div>
    );
}

export default AdminSidebar;

