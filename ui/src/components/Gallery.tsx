import React from 'react';

function Gallery () {
    return (

        <div className="d-flex justify-content-evenly pt-5 pb-5">
            <div className="p-2">
                <img className="img-fluid rounded shadow img-island" src="/images/image1.jpg"
                     alt="Brown Hut Island Gazebo" />
            </div>
            <div className="p-2">
                <img className="img-fluid rounded shadow img-island" src="/images/image2.jpg"
                     alt="Silhouette of Trees during Golden Hour" />
            </div>
            <div className="p-2">
                <img className="img-fluid rounded shadow img-island" src="/images/image3.jpg"
                     alt="Tropical island washed by blue sea water on sunny day" />
            </div>
        </div>
    );
}

export default Gallery;