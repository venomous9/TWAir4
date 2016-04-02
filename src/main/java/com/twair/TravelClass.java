package com.twair;

public class TravelClass {
    private ClassType classType;
    private Integer totalSeats;
    private Integer occupiedSeats;

    public TravelClass(ClassType classType, Integer totalSeats) {
        this.classType = classType;
        this.totalSeats = totalSeats;
        this.occupiedSeats = 0;
    }

    public void book(int numberOfSeats) throws Exception {
        if(canBook(numberOfSeats) == false) {
            throw new Exception("Booking can not be made");
        }
        occupiedSeats += numberOfSeats;
    }

    public boolean canBook(Integer numberOfSetas) {
        if(occupiedSeats + numberOfSetas > totalSeats) {
            return false;
        }
        return true;
    }

    public ClassType getClassType() {
        return classType;
    }
}
