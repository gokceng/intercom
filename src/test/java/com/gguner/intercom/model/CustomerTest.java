package com.gguner.intercom.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Gokcen Guner
 * 11/01/2018 00:55
 **/
public class CustomerTest {
  @Test
  public void getLocation() throws Exception {
    Location location = new Location(36, 42);

    Customer customer1 = new Customer(0, null, location);
    assertNotSame(location, customer1.getLocation());
    assertEquals(location, customer1.getLocation());
  }

  @Test
  public void equals() throws Exception {
    Location location = new Location(36, 42);
    Customer customer1 = new Customer(0, "name", location);
    Customer customer2 = new Customer(1, "name", location);

    assertNotEquals(customer1, customer2);

    Customer customer3 = new Customer(1, null, null);
    assertEquals(customer3, customer2);
  }

  @Test
  public void testHashCode() throws Exception {
    Location location = new Location(36, 42);
    Customer customer1 = new Customer(0, "name", location);
    Customer customer2 = new Customer(1, "name", location);

    assertNotEquals(customer1.hashCode(), customer2.hashCode());

    Customer customer3 = new Customer(1, null, null);
    assertEquals(customer3.hashCode(), customer2.hashCode());
  }

  @Test
  public void compareTo() throws Exception {
    Customer customer1 = new Customer(1, null, null);
    Customer customer0 = new Customer(0, null, null);

    Assert.assertTrue(customer0.compareTo(customer1) < 0);
    Assert.assertTrue(customer1.compareTo(customer0) > 0);
  }
}