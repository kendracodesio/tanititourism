import React, {ReactNode} from 'react';

interface SectionProps {
    imageLeft: boolean;
    title: string;
    imageSrc: string;
    imageAltText: string;
    children: ReactNode;
}
function Section({ imageLeft, title, imageSrc, imageAltText, children}: SectionProps) {
    const textColumn = (
        <div className="col-6 pe-5 ps-5 pb-5">
            <h3 className="text-uppercase pt-5">{title}</h3>
            {children}
        </div>
    );

    const imageColumn = (
        <div className="col-6 ps-5 pb-5 mt-5 d-flex align-items-center">
            <img src={imageSrc} alt={imageAltText} className="img-fluid img-plan shadow rounded"/>
        </div>
    );

    return (
        <React.Fragment>
        <div className="divider-full"></div>
        <div className="row">
            {imageLeft ? [imageColumn, textColumn] : [textColumn, imageColumn]}
        </div>
        </React.Fragment>
    );
}

export default Section;