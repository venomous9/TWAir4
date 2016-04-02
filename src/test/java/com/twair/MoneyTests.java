package com.twair;

import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;

public class MoneyTests {

  @Test
  public void shouldHaveRightCurrencyAndValue() {
    Money money = new Money(Currency.getInstance("USD"), 200);
    Assert.assertEquals(Currency.getInstance("USD"), money.getCurrency());
    Assert.assertEquals(200, money.getValue());
  }

}
