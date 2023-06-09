import React from 'react';

interface PageIntroProps {
    heading: string
    introText: string
}

function PageIntro({heading, introText}: PageIntroProps) {
    return (
        <div>
            <div className="d-flex justify-content-center pb-1 align-items-center pt-5">
                <div className="divider"></div>
                <h1 className="text-uppercase">{heading}</h1>
                <div className="divider"></div>
            </div>
            <p className="text-center">{introText}</p>
        </div>
    )
}
export default PageIntro;



