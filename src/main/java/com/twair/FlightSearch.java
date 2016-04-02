package com.twair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FlightSearch {

    private List<Flight> flightList;

    public FlightSearch(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public FlightSearch byLocation(String source, String destination) {
        if(source == null || source.isEmpty() || destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("source cannot be null");
        }
        List<Flight> matchingFlights = new ArrayList<Flight>();
        for (Flight flight : flightList) {
            if (flight.getSource().equals(source) && flight.getDestination().equals(destination)) {
                matchingFlights.add(flight);
            }
        }
        return new FlightSearch(matchingFlights);
    }

    public FlightSearch byDeparture(Calendar departureDate) {
        if(departureDate == null) {
            return this;
        }
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flightList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            if (departureDate != null) {
                if (dateFormat.format(flight.getDepartureTime().getTime()).equals(dateFormat.format(departureDate.getTime()))) {
                    matchingFlights.add(flight);
                }
            }
        }
        return new FlightSearch(matchingFlights);
    }

    public FlightSearch byAvailableSeats(ClassType classType, int numberOfSeats) {
        if(numberOfSeats < 0) {
            throw new IllegalArgumentException("number of seats can not be negative");
        }
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flightList) {
            if(flight.canBook(classType, numberOfSeats)) {
                matchingFlights.add(flight);
            }
        }
        return new FlightSearch(matchingFlights);
    }

    public FlightSearch byClassType(ClassType classType) {
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flightList) {
            if(flight.hasClass(classType)) {
                matchingFlights.add(flight);
            }
        }
        return new FlightSearch(matchingFlights);
    }
}
