import React from 'react';

interface ListingItemProps {
    key: number;
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;
    acceptsReservations?: string; //optional property applies only to Restaurants & Nightlife

}

function ListingItem({imageUrl, imageAltText, name, description, phone, cost, acceptsReservations}: ListingItemProps) {
    let actionText;
    if (acceptsReservations === "YES") {
        actionText = "for reservations or for more info:";
    } else if (acceptsReservations === "NO") {
        actionText = "walk-ins only - for more info:";
    } else {
        actionText = "to book or to request more info:"
    }
    return (
        <div className="do-item shadow mx-auto mb-5 ">
            <div className="row p-5">
                <div className="col-4 d-flex align-items-center ps-2">
                    <img src={imageUrl} alt={imageAltText} className="img-fluid rounded border shadow"/>
                </div>
                <div className="col-8 d-flex justify-content-between ps-4">
                    <div>
                        <h2 className="text-capitalize do-heading">{name}</h2>
                        <p>{description}</p>
                        <p className="text-capitalize call pt-3">{actionText}</p>
                        <p className="text-capitalize call">Call {phone}</p>
                    </div>
                    <div>
                        <p className="cost">{cost}</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ListingItem;