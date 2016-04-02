package com.twair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class TravelClassTests {
    private int totalSeats;
    private TravelClass economyClass;
    private Double basePrice;

    @Before
    public void setUp() throws Exception {
        totalSeats = 100;
        basePrice = 5000.0;
        economyClass = new TravelClass(ClassType.ECONOMY, totalSeats);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfBookingCanNotBeMade() throws Exception {
        Integer numberOfSetas = 60;
        economyClass.book(numberOfSetas);
        economyClass.book(numberOfSetas);
    }

    @Test
    public void shouldReturnTrueIfBookingCanBeDone() throws Exception {
        Integer numberOfSetas = 60;
        Assert.assertTrue(economyClass.canBook(numberOfSetas));
    }

    @Test
    public void shouldReturnFalseIfBookingCanNotBeDone() throws Exception {
        Integer numberOfSetas = 110;
        Assert.assertFalse(economyClass.canBook(numberOfSetas));
    }
}
