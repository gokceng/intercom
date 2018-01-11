package com.gguner.intercom.model;

import java.util.Objects;

/**
 * Gokcen Guner
 * 09/01/2018 00:01
 **/
public class Customer implements Comparable<Customer> {
  private final int id;
  private final String name;
  private final Location location;

  public Customer(int id, String name, Location location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Location getLocation() {
    if (location == null) {
      return null;
    }
    return new Location(location.getLatitude(), location.getLongitude());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Customer{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", location=" + location +
      '}';
  }

  @Override
  public int compareTo(Customer o) {
    return Integer.compare(this.id, o.id);
  }
}
