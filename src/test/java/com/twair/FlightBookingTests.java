package com.twair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;

public class FlightBookingTests {
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
    travelClasses.add(new TravelClass(ClassType.ECONOMY, 100));

    mockEconomyClass = mock(TravelClass.class);
    mockTravelClasses = new ArrayList();
    mockTravelClasses.add(mockEconomyClass);

  }

  @Test
  public void shouldCalculateTotalCost() throws Exception{
    int passengers = 10;
    Currency currency = Currency.getInstance("USD");
    Map<ClassType, Money> prices = new HashMap<>();
    prices.put(ClassType.ECONOMY, new Money(currency, 100));
    prices.put(ClassType.BUSINESS, new Money(currency, 100));
    prices.put(ClassType.FIRST, new Money(currency, 100));
    Flight flight = new Flight("F001", source, dest, plane, departure, arrival,
            travelClasses, prices);
    FlightBooking flightBooking = new FlightBooking(flight, passengers,
            ClassType.BUSINESS);
    Assert.assertEquals("1000$", flightBooking.getTotalCost());
  }

  @Test
  public void shouldCalculateTotalCostBasedOnAvailableSeatsForEconomyClassType() throws Exception{
    int passengers = 10;
    Currency currency = Currency.getInstance("USD");
    Map<ClassType, Money> prices = new HashMap<>();
    prices.put(ClassType.ECONOMY, new Money(currency, 100));
    prices.put(ClassType.BUSINESS, new Money(currency, 100));
    prices.put(ClassType.FIRST, new Money(currency, 100));
    Flight flight = new Flight("F001", source, dest, plane, departure, arrival,
            travelClasses, prices);

    travelClasses.get(0).book(30);
    FlightBooking flightBooking = new FlightBooking(flight, passengers,
            ClassType.ECONOMY);
    Assert.assertEquals("1000$", flightBooking.getTotalCost());

    travelClasses.get(0).book(15);
    Assert.assertEquals("1300$", flightBooking.getTotalCost());

    travelClasses.get(0).book(45);
    Assert.assertEquals("1600$", flightBooking.getTotalCost());


  }

}
