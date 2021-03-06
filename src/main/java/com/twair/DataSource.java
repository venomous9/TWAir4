package com.twair;

import java.util.*;

public class DataSource {
    final private static DataSource dataSource = new DataSource();
    public static DataSource instance() {
        return dataSource;
    }

    public List<String> fetchLocations() {
        List<String> locations = new ArrayList<String>();
        locations.add("Hyderabad");
        locations.add("Bangalore");
        return locations;
    }

    public List<Plane> fetchPlanes() {
        List<Plane> planes = new ArrayList<>();
        planes.add(new Plane("Boeing777-200LR(77L)", 238));
        planes.add(new Plane("Airbus A319 V2", 144));
        planes.add(new Plane("Airbus A321", 172));
        return planes;
    }

    public FlightSearch fetchFlights() throws Exception {
        List<Flight> flightList = new ArrayList<>();
        List<Plane> planes = fetchPlanes();
        List<String> locations = fetchLocations();
        List<TravelClass> travelClasses = new ArrayList<>();
        Currency currency = Currency.getInstance("USD");
        Map<ClassType, Money> prices = new HashMap<>();
        prices.put(ClassType.ECONOMY, new Money(currency, 100));
        prices.put(ClassType.BUSINESS, new Money(currency, 200));
        prices.put(ClassType.FIRST, new Money(currency, 300));
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 200));
        travelClasses.add(new TravelClass(ClassType.BUSINESS, 100));
        travelClasses.add(new TravelClass(ClassType.FIRST, 50));
        Flight flight1 = new Flight("F001", locations.get(0), locations.get(1), planes.get(0),
                new GregorianCalendar(2016,3,10, 9, 10, 0), new GregorianCalendar(2016,3,10, 9,
                12, 0), travelClasses, prices);

        travelClasses = new ArrayList<>();
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 144));
        Flight flight2 = new Flight("F002", locations.get(0), locations.get(1), planes.get(1),
                new GregorianCalendar(2016,3,11, 9, 10, 0), new GregorianCalendar(2016,3,11, 9,
                12, 0), travelClasses, prices);
        travelClasses = new ArrayList<>();
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 152));
        travelClasses.add(new TravelClass(ClassType.BUSINESS, 20));
        Flight flight3 = new Flight("F003", locations.get(0), locations.get(1), planes.get(2),
                new GregorianCalendar(2016,3,12, 9, 10, 0), new GregorianCalendar(2016,3,12, 9,
                12, 0), travelClasses, prices);

        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        FlightSearch allFlights = new FlightSearch(flightList);
        return allFlights;
    }
}
