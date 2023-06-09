import React from 'react';

interface PageHeroProps {
    imageSrc: string;
    altText: string;
}
function PageHero({ imageSrc, altText }: PageHeroProps) {
    return (
        <div className="container-fluid">
            <img src={imageSrc} alt={altText} className="d-block w-100 img-hero" />
        </div>
    );
}

export default PageHero;