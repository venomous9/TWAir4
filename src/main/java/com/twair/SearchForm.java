package com.twair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SearchForm {
    private String from;
    private String to;
    private String number;
    private Integer numberSeats;
    private Calendar departureDate;
    private ClassType classType;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(Integer numberSeats) {
        if(numberSeats == null) {
            this.numberSeats = 1;
        }else{
            this.numberSeats = numberSeats;
        }
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDateString) throws ParseException {
        if(departureDateString.isEmpty()) {
            this.departureDate = null;
        }else{
            Calendar departureDate = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            departureDate.setTime(sdf.parse(departureDateString));
            this.departureDate = departureDate;
        }
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(String classTypeString) {
        switch (classTypeString) {
            case "Economy":
                this.classType = ClassType.ECONOMY;
                break;
            case "Business":
                this.classType = ClassType.BUSINESS;
                break;
            case "First":
                this.classType = ClassType.FIRST;
                break;
            default:
                this.classType = ClassType.ECONOMY;
        }
    }
}
