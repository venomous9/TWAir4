package com.twair;

public class FlightBooking {
  private Flight flight;
  private int passengers;
  private ClassType classType;

  public FlightBooking(Flight flight, int passengers, ClassType classType) {
    this.flight = flight;
    this.passengers = passengers;
    this.classType = classType;
  }

  public String getTotalCost() {

    int occupiedSeats = flight.getOccupiedSeats(classType);
    int totalSeats = flight.getTotalSeats(classType);
    int priceForClassType = flight.getPrices().get(classType).getValue();
    double percentageOfBooking = ((double)occupiedSeats + passengers) / totalSeats * 100.0;

    if (percentageOfBooking > 40.0 && percentageOfBooking <= 90.0 ) {
      priceForClassType += priceForClassType * 30.0 / 100.0;
    } else if (percentageOfBooking > 90.0) {
      priceForClassType += priceForClassType * 60.0 / 100.0;
    }

    return String.valueOf(priceForClassType * passengers) +
            flight.getPrices().get(classType).getCurrency().getSymbol();
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }
}
