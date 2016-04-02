package com.twair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightTests {
    private String source;
    private String dest;
    private Plane plane;
    private Calendar departure;
    private Calendar arrival;
    private List<TravelClass> travelClasses;
    private List<TravelClass> mockTravelClasses;
    private TravelClass mockEconomyClass;

    @Before
    public void setUp() throws Exception {
        source = "TestSource";
        dest = "TestDestination";
        plane = new Plane("type", 30);
        departure = new GregorianCalendar(2016,3,10, 9, 10, 0);
        arrival = new GregorianCalendar(2016,3,10, 10, 10, 0);
        travelClasses = new ArrayList();
        travelClasses.add(new TravelClass(ClassType.ECONOMY, 30));

        mockEconomyClass = mock(TravelClass.class);
        mockTravelClasses = new ArrayList();
        mockTravelClasses.add(mockEconomyClass);

    }

    @Test
    public void shouldHaveSourceDestination() throws Exception {
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, travelClasses);
        Assert.assertEquals(source, flight.getSource());
        Assert.assertEquals(dest, flight.getDestination());
    }

    @Test
    public void shouldReturnTrueIfNumberOfSeatsCanBeBooked() throws Exception {
        Integer numberOfSeats = 10;
        when(mockEconomyClass.canBook(numberOfSeats)).thenReturn(true);
        when(mockEconomyClass.getClassType()).thenReturn(ClassType.ECONOMY);
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, mockTravelClasses);
        Assert.assertTrue(flight.canBook(ClassType.ECONOMY, numberOfSeats));
    }

    @Test
    public void shouldReturnTrueIfNumberOfSetasExactlySameAsAvailableSeats() throws Exception {
        Integer numberOfSeats = 30;
        when(mockEconomyClass.canBook(numberOfSeats)).thenReturn(true);
        when(mockEconomyClass.getClassType()).thenReturn(ClassType.ECONOMY);
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, mockTravelClasses);
        Assert.assertTrue(flight.canBook(ClassType.ECONOMY, numberOfSeats));
    }

    @Test
    public void shouldReturnFalseIfNumberOfSetasCanNotBeBooked() throws Exception {
        Integer numberOfSeats = 40;
        when(mockEconomyClass.canBook(numberOfSeats)).thenReturn(false);
        when(mockEconomyClass.getClassType()).thenReturn(ClassType.ECONOMY);
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, mockTravelClasses);
        Assert.assertFalse(flight.canBook(ClassType.ECONOMY, numberOfSeats));
    }

    @Test
    public void shouldReturnFalseIfNoTravelClassForTheFlight() throws Exception {
        Integer numberOfSeats = 5;
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, travelClasses);
        Assert.assertFalse(flight.canBook(ClassType.BUSINESS, numberOfSeats));
    }

    @Test
    public void shouldHaveArrivalAndDeparture() throws Exception {
        Calendar departure = new GregorianCalendar(2016,4,10, 9, 10, 0);
        Calendar arrival = new GregorianCalendar(2016,4,10, 11, 10, 0);
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, travelClasses);
        Assert.assertEquals(departure, flight.getDepartureTime());
        Assert.assertEquals(arrival, flight.getArrivalTime());
    }

    @Test(expected=Exception.class)
    public void DepartureDateCannotBeGreaterOrEqualToArrivalTime() throws Exception {
        Calendar departure = new GregorianCalendar(2016,5,10, 9, 10, 0);
        Calendar arrival = new GregorianCalendar(2016,4,10, 11, 10, 0);
        new Flight("F001", source, dest, plane, departure, arrival, travelClasses);
    }

    @Test
    public void shouldReturnTrueIfThereAreSeatsOfThatClass() throws Exception {
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, travelClasses);
        Assert.assertTrue(flight.hasClass(ClassType.ECONOMY));
    }

    @Test
    public void shouldReturnFalseIfThereAreNoSeatsOfThatClass() throws Exception {
        Flight flight = new Flight("F001", source, dest, plane, departure, arrival, travelClasses);
        Assert.assertFalse(flight.hasClass(ClassType.BUSINESS));
    }

}
