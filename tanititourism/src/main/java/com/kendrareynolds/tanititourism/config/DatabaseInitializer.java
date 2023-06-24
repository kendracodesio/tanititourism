package com.kendrareynolds.tanititourism.config;


import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.repository.*;
import com.kendrareynolds.tanititourism.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final ActionReportRepository actionReportRepository;
    private final ThingToDoRepository thingToDoRepository;
    private final PlaceToStayRepository placeToStayRepository;
    private final RestaurantsAndNightlifeRepository restaurantsAndNightlifeRepository;
    private final DoTypeService doTypeService;
    private final RegionService regionService;
    private final StayTypeService stayTypeService;
    private final DineTypeService dineTypeService;

    @Override
    public void run(String... arg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timestamp1 = LocalDateTime.parse("2023-03-02 14:00:00", formatter);
        LocalDateTime timestamp2 = LocalDateTime.parse("2023-03-15 14:00:00", formatter);
        LocalDateTime timestamp3 = LocalDateTime.parse("2023-03-21 14:00:00", formatter);
        LocalDateTime timestamp4 = LocalDateTime.parse("2023-03-28 14:00:00", formatter);

        LocalDateTime timestamp5 = LocalDateTime.parse("2023-03-30 14:00:00", formatter);
        LocalDateTime timestamp6 = LocalDateTime.parse("2023-04-05 14:00:00", formatter);
        LocalDateTime timestamp7 = LocalDateTime.parse("2023-04-17 14:00:00", formatter);
        LocalDateTime timestamp8 = LocalDateTime.parse("2023-04-21 14:00:00", formatter);

        LocalDateTime timestamp9 = LocalDateTime.parse("2023-04-24 14:00:00", formatter);
        LocalDateTime timestamp10 = LocalDateTime.parse("2023-04-27 14:00:00", formatter);
        LocalDateTime timestamp11 = LocalDateTime.parse("2023-05-03 14:00:00", formatter);
        LocalDateTime timestamp12 = LocalDateTime.parse("2023-05-06 14:00:00", formatter);

        LocalDateTime timestamp13 = LocalDateTime.parse("2023-05-11 14:00:00", formatter);
        LocalDateTime timestamp14 = LocalDateTime.parse("2023-05-16 14:00:00", formatter);
        LocalDateTime timestamp15 = LocalDateTime.parse("2023-05-19 14:00:00", formatter);
        LocalDateTime timestamp16 = LocalDateTime.parse("2023-05-20 14:00:00", formatter);

        LocalDateTime timestamp17 = LocalDateTime.parse("2023-05-22 14:00:00", formatter);
        LocalDateTime timestamp18 = LocalDateTime.parse("2023-05-28 14:00:00", formatter);
        LocalDateTime timestamp19 = LocalDateTime.parse("2023-06-01 14:00:00", formatter);
        LocalDateTime timestamp20 = LocalDateTime.parse("2023-06-05 14:00:00", formatter);

        LocalDateTime timestamp21 = LocalDateTime.parse("2023-06-14 14:00:00", formatter);
        LocalDateTime timestamp22 = LocalDateTime.parse("2023-06-14 14:00:00", formatter);
        LocalDateTime timestamp23 = LocalDateTime.parse("2023-04-19 14:00:00", formatter);
        LocalDateTime timestamp24 = LocalDateTime.parse("2023-06-21 14:00:00", formatter);

        List<User> users = userService.findAllUsers();

        DoType adventure = doTypeService.findByTypeName("Adventure");
        DoType exploreByAir = doTypeService.findByTypeName("Explore By Air");
        DoType exploreBySea = doTypeService.findByTypeName("Explore By Sea");
        DoType exploreByFoot = doTypeService.findByTypeName("Explore By Foot");

        Set<DoType> thingToDo1Types = new HashSet<>();
        thingToDo1Types.add(adventure);
        thingToDo1Types.add(exploreByAir);

        Set<DoType> thingToDo2and3Types = new HashSet<>();
        thingToDo2and3Types.add(adventure);
        thingToDo2and3Types.add(exploreBySea);

        Set<DoType> thingToDo4Types = new HashSet<>();
        thingToDo4Types.add(adventure);
        thingToDo4Types.add(exploreByFoot);

        Region merrtionLanding = regionService.findByName("Merriton Landing");
        Region dtTanitiCity = regionService.findByName("Downtown Taniti City");
        Region yellowLeafBay = regionService.findByName("Yellow Leaf Bay");
        Region islandInterior = regionService.findByName("Island Interior Region");
        Region wTanitiCity = regionService.findByName("Taniti City - West");
        Region eTanitiCity = regionService.findByName("Taniti City - East");


        StayType hotels = stayTypeService.findByTypeName("Hotels");
        StayType resorts = stayTypeService.findByTypeName("Resorts");
        StayType bAndB = stayTypeService.findByTypeName("Bed And Breakfasts");

        DineType localFlavor = dineTypeService.findByTypeName("Local Flavor Restaurants");
        DineType panAsian = dineTypeService.findByTypeName("Pan-Asian Restaurants");
        DineType american = dineTypeService.findByTypeName("American Restaurants");
        DineType pub = dineTypeService.findByTypeName("Pubs & Breweries");


        ThingToDo thingToDo1 = new ThingToDo("Taniti Island Helicopter Tour",
                "Embark on an exhilarating helicopter tour of Taniti Island, offering unmatched panoramic views of our pristine beaches, lush forests, and stunning coastline. An unforgettable experience for all visitors.",
                "+689 98 99 87 70", Cost.MID_RANGE, "https://tanititourismimages.blob.core.windows.net/images/helicopter-tour.jpg", "red helicopter in sky", merrtionLanding, Listing.ListingType.DO, thingToDo1Types);
        thingToDoRepository.save(thingToDo1);
        ActionReport actionReport1 = new ActionReport(ActionReport.Action.CREATE, timestamp1, thingToDo1, users.get(0));
        actionReportRepository.save(actionReport1);
        ActionReport actionReport1Update = new ActionReport(ActionReport.Action.UPDATE, timestamp3, thingToDo1, users.get(3));
        actionReportRepository.save(actionReport1Update);
        ActionReport actionReport1Update2 = new ActionReport(ActionReport.Action.UPDATE, timestamp22, thingToDo1, users.get(0));
        actionReportRepository.save(actionReport1Update2);

        PlaceToStay placeToStay1 = new PlaceToStay("Taniti City Hotel",
                "Located in the heart of downtown Taniti City, our hotel provides the perfect balance of comfort and convenience. With modern rooms, on-site amenities, and easy access to city attractions, Taniti City Hotel is your home away from home. Experience authentic island hospitality at an affordable price.",
                "+689 98 99 87 80", Cost.MID_RANGE, "https://tanititourismimages.blob.core.windows.net/images/taniti-city-hotel.jpg", "taniti city hotel room interior view", dtTanitiCity, Listing.ListingType.STAY, hotels);
        placeToStayRepository.save(placeToStay1);
        ActionReport actionReport2 = new ActionReport(ActionReport.Action.CREATE, timestamp2, placeToStay1, users.get(1));
        actionReportRepository.save(actionReport2);
        ActionReport actionReport2Update = new ActionReport(ActionReport.Action.UPDATE, timestamp4, placeToStay1, users.get(2));
        actionReportRepository.save(actionReport2Update);

        RestaurantsAndNightlife restaurant1 = new RestaurantsAndNightlife("South Sea Kitchen",
                "South Sea Kitchen, situated in the charming Merriton Landing, captures the essence of local island flavor. Delight in freshly caught seafood dishes, traditional Tahiti fare, and homemade desserts that remind you of a simpler time. Here, exceptional culinary experiences come at a price that''s as pleasing as the food itself.",
                "+689 98 99 87 90", Cost.ECONOMICAL, "https://tanititourismimages.blob.core.windows.net/images/south-sea-kitchen.jpg", "south sea kitchen interior", merrtionLanding, Listing.ListingType.DINE, RestaurantsAndNightlife.AcceptsReservations.NO, localFlavor);
        restaurantsAndNightlifeRepository.save(restaurant1);
        ActionReport actionReport3 = new ActionReport(ActionReport.Action.CREATE, timestamp5, restaurant1, users.get(2));
        actionReportRepository.save(actionReport3);
        ActionReport actionReport3Update = new ActionReport(ActionReport.Action.UPDATE, timestamp7, restaurant1, users.get(1));
        actionReportRepository.save(actionReport3Update);

        ThingToDo thingToDo2 = new ThingToDo("Coastline Snorkel Excursions",
                "Navigate the clear waters of Taniti, uncovering a mesmerizing world beneath the waves. The vibrant coral reefs, abundant marine life, and crystal-clear visibility offer an unforgettable snorkeling experience. Essential equipment and experienced guides included.",
                "+689 98 99 87 71", Cost.ECONOMICAL, "https://tanititourismimages.blob.core.windows.net/images/snorkeling.jpg", "couple snorkeling in the sea", yellowLeafBay, Listing.ListingType.DO, thingToDo2and3Types);
        thingToDoRepository.save(thingToDo2);
        ActionReport actionReport4 = new ActionReport(ActionReport.Action.CREATE, timestamp6, thingToDo2, users.get(0));
        actionReportRepository.save(actionReport4);
        ActionReport actionReport4Update = new ActionReport(ActionReport.Action.UPDATE, timestamp8, thingToDo2, users.get(2));
        actionReportRepository.save(actionReport4Update);

        PlaceToStay placeToStay2 = new PlaceToStay("Waterfront Resort",
                "Waterfront Resort at Yellow Leaf Bay invites you to live luxuriously. Positioned right on the water, each of our private bungalows offers a stunning bay view. Enjoy a range of top-tier amenities including spa services, gourmet dining, and direct beach access. Immerse yourself in paradise at our luxury resort.",
                "+689 98 99 87 81", Cost.LUXURY, "https://tanititourismimages.blob.core.windows.net/images/waterfront-resort.jpg", "aerial view of waterfront resort", yellowLeafBay, Listing.ListingType.STAY, resorts);
        placeToStayRepository.save(placeToStay2);
        ActionReport actionReport5 = new ActionReport(ActionReport.Action.CREATE, timestamp9, placeToStay2, users.get(3));
        actionReportRepository.save(actionReport5);
        ActionReport actionReport5Update = new ActionReport(ActionReport.Action.UPDATE, timestamp12, placeToStay2, users.get(3));
        actionReportRepository.save(actionReport5Update);

        RestaurantsAndNightlife restaurant2 = new RestaurantsAndNightlife("Shintaro Restaurant",
                "Indulge in the extravagant culinary journey at Shintaro Restaurant. Poised on the beautiful coast of Yellow Leaf Bay, Shintaro offers an assortment of intricate Pan-Asian delights. Our expert chefs utilize premium ingredients to create dishes that are as visually striking as they are flavorful. The restaurant also warmly welcomes reservations, ensuring a seamlessly memorable dining experience.",
                "+689 98 99 87 91", Cost.LUXURY, "https://tanititourismimages.blob.core.windows.net/images/shintaro-restaurant.jpg", "interior view of shintaro restaurant before opening for dinner", yellowLeafBay, Listing.ListingType.DINE, RestaurantsAndNightlife.AcceptsReservations.YES, panAsian);
        restaurantsAndNightlifeRepository.save(restaurant2);
        ActionReport actionReport6 = new ActionReport(ActionReport.Action.CREATE, timestamp10, restaurant2, users.get(2));
        actionReportRepository.save(actionReport6);
        ActionReport actionReport6Update = new ActionReport(ActionReport.Action.UPDATE, timestamp13, restaurant2, users.get(1));
        actionReportRepository.save(actionReport6Update);

        ThingToDo thingToDo3 = new ThingToDo("Private Charter Fishing Tour",
                "Experience the rich fishing grounds of Taniti''s deep blue waters. From novice to experienced anglers, our experienced crew will guide you in catching a diverse array of marine species. Gear, bait, and a boatload of excitement included.",
                "+689 98 99 87 72", Cost.LUXURY, "https://tanititourismimages.blob.core.windows.net/images/fishing.jpg", "fishing rod overlooking the sea", yellowLeafBay, Listing.ListingType.DO, thingToDo2and3Types);
        thingToDoRepository.save(thingToDo3);
        ActionReport actionReport7 = new ActionReport(ActionReport.Action.CREATE, timestamp11, thingToDo3, users.get(0));
        actionReportRepository.save(actionReport7);
        ActionReport actionReport7Update = new ActionReport(ActionReport.Action.UPDATE, timestamp14, thingToDo3, users.get(1));
        actionReportRepository.save(actionReport7Update);

        PlaceToStay placeToStay3 = new PlaceToStay("Bay View Inn",
                "Unwind at Bay View Inn, your cozy island getaway nestled in charming Merriton Landing. Each of our comfortable rooms boasts a stunning view of the bay, promising a serene start to each day. With on-site dining and a convenient location near local attractions, a stay at Bay View Inn is certain to be filled with delightful moments and cherished memories.",
                "+689 98 99 87 82", Cost.MID_RANGE, "https://tanititourismimages.blob.core.windows.net/images/bay-view-inn.jpg", "bay view inn hotel room interior view", merrtionLanding, Listing.ListingType.STAY, hotels);
        placeToStayRepository.save(placeToStay3);
        ActionReport actionReport8 = new ActionReport(ActionReport.Action.CREATE, timestamp15, placeToStay3, users.get(2));
        actionReportRepository.save(actionReport8);
        ActionReport actionReport8Update = new ActionReport(ActionReport.Action.UPDATE, timestamp17, placeToStay3, users.get(0));
        actionReportRepository.save(actionReport8Update);

        RestaurantsAndNightlife restaurant3 = new RestaurantsAndNightlife("Lola's Bistro",
                "Lola's Bistro is your the go-to spot in downtown Taniti City for a taste of classic American comfort food. Our juicy burgers and golden, crispy fries, crafted from locally-sourced ingredients, have locals and tourists alike coming back for more. Lola's Bistro offers a relaxed, friendly ambiance where walk-ins are always welcome.",
                "+689 98 99 87 92", Cost.ECONOMICAL, "https://tanititourismimages.blob.core.windows.net/images/lolas.jpg", "lola's bistro american food hamburger and fries", dtTanitiCity, Listing.ListingType.DINE, RestaurantsAndNightlife.AcceptsReservations.NO, american);
        restaurantsAndNightlifeRepository.save(restaurant3);
        ActionReport actionReport9 = new ActionReport(ActionReport.Action.CREATE, timestamp16, restaurant3, users.get(3));
        actionReportRepository.save(actionReport9);
        ActionReport actionReport9Update = new ActionReport(ActionReport.Action.UPDATE, timestamp19, restaurant3, users.get(1));
        actionReportRepository.save(actionReport9Update);

        ThingToDo thingToDo4 = new ThingToDo("Rainforest Hiking Tour",
                "Embark on an adventurous journey through Taniti''s lush rainforest. This guided tour offers a unique opportunity to explore diverse flora and fauna, hear the symphony of exotic birds, and experience the freshness of wilderness. Hiking boots recommended!",
                "+689 98 99 87 73", Cost.ECONOMICAL, "https://tanititourismimages.blob.core.windows.net/images/rainforest-hike.jpg", "man hiking in the rainforest", islandInterior, Listing.ListingType.DO, thingToDo4Types);
        thingToDoRepository.save(thingToDo4);
        ActionReport actionReport10 = new ActionReport(ActionReport.Action.CREATE, timestamp18, thingToDo4, users.get(2));
        actionReportRepository.save(actionReport10);
        ActionReport actionReport10Update = new ActionReport(ActionReport.Action.UPDATE, timestamp20, thingToDo4, users.get(0));
        actionReportRepository.save(actionReport10Update);

        PlaceToStay placeToStay4 = new PlaceToStay("Voyageur Bed & Breakfast",
                "Experience the warmth and comfort of island hospitality at Voyageur Bed & Breakfast. Located in vibrant Taniti City, our B&B offers a quaint and inviting atmosphere that embraces you like a gentle island breeze. Enjoy a good night''s sleep in our cozy rooms and wake up to a delightful homemade breakfast, all without stretching your budget.",
                "+689 98 99 87 83", Cost.ECONOMICAL, "https://tanititourismimages.blob.core.windows.net/images/voyageurbandb.jpg", "voyageur bed and breakfast exterior view", wTanitiCity, Listing.ListingType.STAY, bAndB);
        placeToStayRepository.save(placeToStay4);
        ActionReport actionReport11 = new ActionReport(ActionReport.Action.CREATE, timestamp21, placeToStay4, users.get(1));
        actionReportRepository.save(actionReport11);

        RestaurantsAndNightlife restaurant4 = new RestaurantsAndNightlife("High Tide Bar & Grill",
                "Enjoy the perfect brew at High Tide Bar & Grill, a must-visit pub located in the lively Eastside of Taniti City. Known for their excellent selection of locally crafted beers and a menu full of mouthwatering pub classics, there''s no better place to unwind after a day of island exploration. With a comfortable and welcoming atmosphere, High Tide Bar & Grill is the perfect spot for a casual meal, a meet-up with friends, or a night out on the town.",
                "+689 98 99 87 93", Cost.MID_RANGE, "https://tanititourismimages.blob.core.windows.net/images/high-tide-bar.jpg", "group of people with beers at high tide bar & grill", eTanitiCity, Listing.ListingType.DINE, RestaurantsAndNightlife.AcceptsReservations.NO, pub);
        restaurantsAndNightlifeRepository.save(restaurant4);
        ActionReport actionReport12 = new ActionReport(ActionReport.Action.CREATE, timestamp23, restaurant4, users.get(0));
        actionReportRepository.save(actionReport12);
        ActionReport actionReport12Update = new ActionReport(ActionReport.Action.UPDATE, timestamp24, restaurant4, users.get(3));
        actionReportRepository.save(actionReport12Update);


    }


}



