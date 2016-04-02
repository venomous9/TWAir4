package com.twair;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flight {
    private String source;
    private String destination;
    private Plane plane;
    private String number;
    private final Integer availableSeats;
    private Calendar departureTime;
    private Calendar arrivalTime;
    private Map<ClassType, TravelClass> travelClassMap = new HashMap<>();


    public Flight(String number, String source, String destination, Plane plane, Calendar departure, Calendar arrival, List<TravelClass> travelClasses) throws Exception {
        this.source = source;
        this.destination = destination;
        this.plane = plane;
        this.number = number;
        this.availableSeats = plane.getNumberOfSeats();
        setScheduleTime(departure, arrival);
        for(TravelClass travelClass : travelClasses) {
            travelClassMap.put(travelClass.getClassType(), travelClass);
        }
    }

    public boolean canBook(ClassType classType, Integer numberOfSeats) {
        if(travelClassMap.containsKey(classType)) {
            return travelClassMap.get(classType).canBook(numberOfSeats);
        }
        return false;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getNumber() {
        return number;
    }

    public Calendar getDepartureTime() {
        return departureTime;
    }

    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    public boolean hasClass(ClassType classType) {
        return travelClassMap.containsKey(classType);
    }

    private void setScheduleTime(Calendar departureTime, Calendar arrivalTime) throws Exception {
        if(departureTime.after(arrivalTime)) {
            throw new Exception("departure time cannot be greater than arrival time");
        }
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
