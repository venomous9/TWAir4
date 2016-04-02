package com.twair;

public class FlightWithQueryInfo {
  private Flight flight;
  private int passengers;
  private ClassType classType;

  public FlightWithQueryInfo(Flight flight, int passengers, ClassType classType) {
    this.flight = flight;
    this.passengers = passengers;
    this.classType = classType;
  }

  public String getTotalCost() {
    return String.valueOf(flight.getMoney().get(classType).getValue() * passengers) +
            flight.getMoney().get(classType).getCurrency().getSymbol();
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }
}
