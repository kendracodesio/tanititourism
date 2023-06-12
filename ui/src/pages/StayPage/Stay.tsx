import React, {useEffect, useState} from 'react';
import PageHero from "../../components/PageHero";
import PageIntro from "../../components/PageIntro";
import Dropdown from "../../components/Dropdown";
import axios from "axios";
import ListingItem from "../../components/ListingItem";

interface Listing {
    id:number
    imageUrl: string
    imageAltText: string
    name: string
    description: string
    phone: string
    actionText: string
    cost: string
}

function Stay () {
    const apiURL = process.env.REACT_APP_API_URL;
    const [stayType, setStayType] = useState("all");
    const [region, setRegion] = useState("all");
    const [listings, setListings] = useState<Listing[]>([]);

    useEffect(() => {
        axios.get(`${apiURL}/api/places-to-stay/list?stayType=${stayType}&region=${region}`)
            .then(response => {
                setListings(response.data.content);
                })
            .catch(error => {
                console.log("Error getting places to stay:", error);
            });

            }, [apiURL, stayType, region]);

    return (
        <div>
            <PageHero imageSrc="https://tanititourismimages.blob.core.windows.net/images/stay-hero.jpg"
                      altText="resort hotel on island" />
            <div className="container">
                <PageIntro heading="places to stay" introText="Taniti has a wide variety of lodging to suit the needs of just about any traveler. Travelers on a budget and who don't
    mind sharing a room with others might consider the island's hostel. There's also many small, family-owned hotels and a
    growing number of bed and breakfasts that range from economical to upscale. Taniti even has a large, four-star resort
    for those looking for the ultimate luxury experience. And for vacationers who have privacy at top of mind, the island has
    several private condos and beach-front residences." />
                <form className="row ps-5 mt-5">
                    <Dropdown apiEndpoint="/api/places-to-stay/type"
                              label="By Type"
                              id="stayType"
                              onChange={newStayType => setStayType(newStayType)}/>
                    <Dropdown apiEndpoint="/api/places-to-stay/region"
                              label="Region"
                              id="region"
                              onChange={newRegion => setRegion(newRegion)}/>
                </form>
                {listings.map(listing => (
                    <ListingItem key={listing.id}
                                 imageUrl={listing.imageUrl}
                                 imageAltText={listing.imageAltText}
                                 name={listing.name}
                                 description={listing.description}
                                 phone={listing.phone}
                                 actionText="to book or to request more info:"
                                 cost={listing.cost} />
                ))}
            </div>

        </div>
    );
}

export default Stay;