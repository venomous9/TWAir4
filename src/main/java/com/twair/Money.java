package com.twair;

import java.util.Currency;

public class Money {
  private Currency currency;
  private int value;

  public Money(Currency currency, int value) {
    this.currency = currency;
    this.value = value;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
