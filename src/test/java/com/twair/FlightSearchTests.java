package com.twair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/* the tests of this class, directly integrated with Flight class for better reliability*/
public class FlightSearchTests {
    private String source;
    private String destination;
    private Calendar departure;
    private Calendar arrival;
    private FlightSearch allFlights;

    @Before
    public void setUp() throws Exception {
        source = "TestSource";
        destination = "TestDestination";
        departure = new GregorianCalendar(2016,3,10, 9, 10, 0);
        arrival = new GregorianCalendar(2016,3,10, 10, 10, 0);

        List<TravelClass> travelClasses = new ArrayList<>();
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 30));
        Flight flight1 = new Flight("F001", source, destination, new Plane("type1", 30), new GregorianCalendar(2016,3,10, 9, 10, 0), new GregorianCalendar(2016,3,10, 11, 10, 0), travelClasses);

        travelClasses = new ArrayList<>();
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 5));
        travelClasses.add(new TravelClass(ClassType.BUSINESS, 5));
        Flight flight2 = new Flight("F002", "TestSource1", destination, new Plane("type2", 10), new GregorianCalendar(2016,4,10, 9, 10, 0), new GregorianCalendar(2016,4,10, 11, 10, 0), travelClasses);

        travelClasses = new ArrayList<>();
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 5));
        Flight flight3 = new Flight("F003", source, destination, new Plane("type2", 5), new GregorianCalendar(2016,4,11, 9, 10, 0), new GregorianCalendar(2016,4,11, 11, 10, 0), travelClasses);

        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        allFlights = new FlightSearch(flightList);
    }

    @Test
    public void shouldReturnListOfFlightsForMatchingSourceDestination() throws Exception {
        List<Flight> flights = allFlights.byLocation(source, destination).getFlightList();
        Assert.assertEquals(source, flights.get(0).getSource());
        Assert.assertEquals(destination, flights.get(0).getDestination());
        Assert.assertEquals(source, flights.get(1).getSource());
        Assert.assertEquals(destination, flights.get(1).getDestination());
        Assert.assertEquals(2, flights.size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldMandateSource() throws Exception {
        allFlights.byLocation(null, destination);
    }

    @Test(expected=IllegalArgumentException.class)
    public void sourceCannotBeEmpty() throws Exception {
        allFlights.byLocation("", destination);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldMandateDestination() throws Exception {
        allFlights.byLocation(source, null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void destinationCannotBeEmpty() throws Exception {
        allFlights.byLocation(source, "");
    }

    @Test
    public void shouldReturnMatchingFlightsBasedOnDepartureDate() throws Exception {
        Calendar departureDate = new GregorianCalendar(2016,3,10);
        List<Flight> flights = allFlights.byDeparture(departureDate).getFlightList();
        Assert.assertEquals(source, flights.get(0).getSource());
        Assert.assertEquals(destination, flights.get(0).getDestination());
        Assert.assertEquals(1, flights.size());
    }

    @Test
    public void shouldFilterByAvailableSeats() throws Exception {
        int numberOfSeats = 11;
        List<Flight> matchingFlights = allFlights.byAvailableSeats(ClassType.FIRST, numberOfSeats).getFlightList();
        Assert.assertEquals(0, matchingFlights.size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void numberOfSeatsCannotBeNegative() throws Exception {
        allFlights.byAvailableSeats(ClassType.ECONOMY, -10);
    }

    @Test
    public void shouldFilterBasedOnClassType() throws Exception {
        List<Flight> matchingFlights = allFlights.byClassType(ClassType.BUSINESS).getFlightList();
        Assert.assertEquals("TestSource1", matchingFlights.get(0).getSource());
        Assert.assertEquals(destination, matchingFlights.get(0).getDestination());
        Assert.assertEquals(1, matchingFlights.size());
    }
}
