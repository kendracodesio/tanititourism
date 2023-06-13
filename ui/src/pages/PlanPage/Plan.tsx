import React from 'react';
import PageHero from "../../components/PageHero";
import PageIntro from "../../components/PageIntro";
import Section from "./components/Section";
import InternalLink from "./components/InternalLink";
import FAQSection from "./components/FAQSection";

function Plan () {
    return (
        <div>
            <PageHero imageSrc="https://tanititourismimages.blob.core.windows.net/images/plan-hero.jpg"
                      altText="Person Opening a Map on the Floor"/>
            <div className="container">
                <PageIntro heading="plan your trip" introText="So you're planning a trip to Taniti. That's wonderful! We know you're going to
                    have such an amazing time."/>
                <p className="text-left pb-3 ps-5">To help you get started here's a checklist of things to consider as
                    you look
                    forward to your visit:
                </p>
                <Section imageLeft={false}
                         title="travel to taniti"
                         imageSrc="https://tanititourismimages.blob.core.windows.net/images/air-taniti.jpg"
                         imageAltText="view from inside a airplane overlooking island"
                         >
                    <p className="text-uppercase"><strong>by air</strong></p>
                    <p>The most popular way visitors arrive to island is by air. Taniti is served by a small airport
                        that can accommodate small jet and propeller planes. Within the next few years, the airport will be expanding to accommodate larger
                        jets.
                    </p>
                    <p className="text-uppercase"><strong>by boat</strong></p>
                    <p className="pb-4">Another option travelers have when planning their way to the island is by cruise
                        ship. Cruise ships typically dock at Yellow Leaf Bay for one night a week.</p>
                </Section>
                <Section imageLeft={true}
                         title="get around in taniti"
                         imageSrc="https://tanititourismimages.blob.core.windows.net/images/getting-around-taniti.jpg"
                         imageAltText="view from car overlooking two on a bicycle by the sea"
                         >
                    <p className="text-uppercase"><strong>by bus or by car</strong></p>
                    <p>Public buses serve Taniti City and run from 5am to 11pm every day. Private buses serve
                        the rest of the island. Taxis are available in Taniti City, and rental cars can be rented from a local rental
                        agency near the airport.</p>
                    <p className="text-uppercase"><strong>by bike or on foot</strong></p>
                    <p className="pb-5">Bikes and helmets are available to rent from several vendors. If you're looking
                        to explore the island by foot, Taniti City is fairly flat and very walkable. The area surrounding
                        Merriton Landing is highly recommended for those exploring by foot .</p>
                </Section>
                <Section
                    imageLeft={false}
                    title="what to do in taniti"
                    imageSrc="https://tanititourismimages.blob.core.windows.net/images/to-do-beach-taniti.jpg"
                    imageAltText="crowd of people enjoying time at the beach">
                    <p>Most people visit Taniti to enjoy the beaches, explore the rainforest, and to visit
                        the volcano. However, there are other things to do, including visiting a local history
                        museum, going on chartered fishing tours, snorkeling, zip-lining in the rainforest, dancing at
                        a new dance club, seeing a movie, taking taking helicopter rides, playing at an arcade, visiting art
                        galleries, and bowling. Also, a
                        nine-hole golf course should be operational by next year. Many of these activities are located
                        in Merriton Landing, which is a rapidly developing area on the north side of Yellow Leaf Bay.
                    </p>
                    <InternalLink to="/do" linkText="Find Things To Do In Taniti"/>
                </Section>
                <Section imageLeft={true}
                         title="where to stay in taniti"
                         imageSrc="https://tanititourismimages.blob.core.windows.net/images/stay-in-taniti.jpg"
                         imageAltText="view from inside a hotel room">
                    <p>Taniti has a wide variety of lodging to suit the needs of just about any traveler. Travelers
                        on a budget and who don't mind sharing a room with others might consider the island's hostel.
                        There's also many small, family-owned hotels and a growing number of bed and breakfasts that range
                        from economical to upscale. Taniti even has a large, four-star resort for those looking for
                        the ultimate luxury experience. And for vacationers who have privacy at top of mind, the island has
                        several private condos and beach-front residences.
                    </p>
                    <InternalLink to="/stay" linkText="Find Where To Stay In Taniti"/>
                </Section>
                <Section imageLeft={false}
                         title="where to eat in taniti"
                         imageSrc="https://tanititourismimages.blob.core.windows.net/images/eat-taniti.jpg"
                         imageAltText="two entrees next to a glass of white wine">
                    <p>One of the best ways to deepen your knowledge of Taniti culture is to get
                        to know the local food scene. The island offers plenty of local flavor restaurants
                        that we highly recommend for enriching your experience. However, if your palate is not feeling that adventurous,
                        the island also has restaurants that serve up American-style meals. Pan-Asian cuisine is also
                        quite popular with many who come to the island. If you're looking to grab a beer or other alcoholic drink with your
                        meal, one of several pubs might be more your style.
                    </p>
                    <InternalLink to="/dine" linkText="Find Where To Eat In Taniti"/>
                </Section>
                <Section imageLeft={true}
                         title="best time to visit taniti"
                         imageSrc="https://tanititourismimages.blob.core.windows.net/images/rainforest.jpg"
                         imageAltText="lush green rainforest with waterfall">
                    <p>There's never a bad time to visit Taniti, but travelers may want to
                        consider timing their visit for ideal weather conditions based on the activities they wish to experience.
                        Regardless of when you plan to visit Taniti, you will enjoy warm weather and water temperatures. The climate
                        typically ranges from 70ºF | 21ºC to a high of 95ºF | 35ºC.</p>
                    <p>The rainy season usually begins in November and lasts until April. This can be great time for exploring the
                        rainforest as the vegetation is especially lush and beautiful. Activities like snorkeling and hiking are ideally done during the dry season
                        -- from April to October.</p>
                </Section>
                <div className="divider-full"></div>
                <FAQSection/>

            </div>
        </div>
    );
}

export default Plan;