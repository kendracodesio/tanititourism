import React, {useEffect, useState} from 'react';
import PageHero from "../../components/PageHero";
import PageIntro from "../../components/PageIntro";
import Dropdown from "../../components/Dropdown";
import ListingItem from "../../components/ListingItem";
import axios from "axios";
import Pagination from "../../components/Pagination";

interface Listing {
    id: number;
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;
    acceptsReservations: string;
}

function Dine () {
    const apiURL = process.env.REACT_APP_API_URL;
    const [dineType, setDineType] = useState("all");
    const [region, setRegion] = useState("all");
    const [listings, setListings] = useState<Listing[]>([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState<number>();


    useEffect(() => {
        axios.get(`${apiURL}/api/restaurants-and-nightlife/list?dineType=${dineType}&region=${region}&page=${page}`)
            .then(response => {
                setListings(response.data.content);
                setPageSize(response.data.size);
                setTotalPages(response.data.totalPages);
            })
            .catch(error => {
                console.log("Error getting restaurants and nightlife:", error);
            });
    }, [apiURL, dineType, region, page, pageSize]);

    const handlePrevious = () => {
        setPage(prevPage => prevPage > 1 ? prevPage -1 :prevPage);
    };

    const handleNext = () => {
        setPage(prevPage => prevPage < totalPages ? prevPage + 1 : prevPage);
    };

    return (
        <div>
            <PageHero imageSrc="https://tanititourismimages.blob.core.windows.net/images/dine-hero.jpg"
                      altText="Couple enjoying private dining at lookout spot on island"/>
            <div className="container">
                <PageIntro heading="restaurants & nightlife" introText="One of the best ways to deepen your knowledge of Taniti culture is to get
      to know the local food scene. The island offers plenty of local flavor restaurants
      that we highly recommend for enriching your experience. However, if your palate is not feeling that adventurous,
       the island also has restaurants that serve up American-style meals. Pan-Asian cuisine is also
       quite popular with many who come to the island. If you're looking to grab a beer or other alcoholic drink with your
       meal, one of several pubs might be more your style. Taniti's emerging nightlife scene also offers an exciting change of
      pace for those looking to go out and show off their dance moves." />
                <form className="row ps-5 mt-5">
                    <Dropdown apiEndpoint="/api/restaurants-and-nightlife/type"
                              label="By Type"
                              id="dineType"
                              onChange={newDineType => setDineType(newDineType)}/>
                    <Dropdown apiEndpoint="/api/restaurants-and-nightlife/region"
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
                                 cost={listing.cost}
                                 acceptsReservations={listing.acceptsReservations}/>
                ))}
                {totalPages > 1 &&
                <Pagination page={page}
                            totalPages={totalPages}
                            handlePrevious={handlePrevious}
                            handleNext={handleNext}/>
                }
            </div>
        </div>
    );
}
export default Dine;