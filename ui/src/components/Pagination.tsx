import React from 'react';

interface PaginationProps {
    page: number,
    totalPages: number,
    handlePrevious: () => void,
    handleNext: () => void

}

function Pagination({page, totalPages, handlePrevious, handleNext }: PaginationProps) {
    return (
        <div className="d-flex justify-content-center align-items-center">
            <button onClick={handlePrevious} disabled={page === 1} className="me-2">
                Previous
            </button>
            <span>Page {page} of {totalPages}</span>
            <button onClick={handleNext} disabled={page === totalPages} className="ms-2">
                Next
            </button>
        </div>
    );
}
export default Pagination;