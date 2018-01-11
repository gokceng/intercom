package com.gguner.intercom.model;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Gokcen Guner
 * 11/01/2018 00:12
 **/
public class LocationTest {
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void testConstructorValidation_TooBigLatitude() throws Exception {
    double latitude = Location.MAX_LATITUDE + 0.001;

    exception.expect(IllegalArgumentException.class);
    new Location(latitude, Location.MAX_LONGITUDE - 0.001);
  }

  @Test
  public void testConstructorValidation_TooSmallLatitude() throws Exception {
    double latitude = -Location.MAX_LATITUDE - 0.001;

    exception.expect(IllegalArgumentException.class);
    new Location(latitude, Location.MAX_LONGITUDE - 0.001);
  }

  @Test
  public void testConstructorValidation_TooBigLongitude() throws Exception {
    double longitude = Location.MAX_LONGITUDE + 0.001;

    exception.expect(IllegalArgumentException.class);
    new Location(Location.MAX_LATITUDE - 0.001, longitude);
  }

  @Test
  public void testConstructorValidation_TooSmallLongitude() throws Exception {
    double longitude = -Location.MAX_LONGITUDE - 0.001;

    exception.expect(IllegalArgumentException.class);
    new Location(Location.MAX_LATITUDE - 0.001, longitude);
  }

  @Test
  public void testConstructorValidation_ValidValues() throws Exception {
    new Location(Location.MAX_LATITUDE - 0.001, Location.MAX_LONGITUDE - 0.001);
  }

  @Test
  public void testArcLengthDistance_TooBigLatitude() throws Exception {
    double latitude = Location.MAX_LATITUDE + 0.001;

    Location location = new Location(1, 1);
    exception.expect(IllegalArgumentException.class);
    location.getArcLengthDistance(latitude, Location.MAX_LONGITUDE - 0.001);
  }

  @Test
  public void testArcLengthDistance_TooSmallLatitude() throws Exception {
    double latitude = -Location.MAX_LATITUDE - 0.001;

    Location location = new Location(1, 1);
    exception.expect(IllegalArgumentException.class);
    location.getArcLengthDistance(latitude, Location.MAX_LONGITUDE - 0.001);
  }

  @Test
  public void testArcLengthDistance_TooBigLongitude() throws Exception {
    double longitude = Location.MAX_LONGITUDE + 0.001;

    Location location = new Location(1, 1);
    exception.expect(IllegalArgumentException.class);
    location.getArcLengthDistance(Location.MAX_LATITUDE - 0.001, longitude);
  }

  @Test
  public void testArcLengthDistance_TooSmallLongitude() throws Exception {
    double longitude = -Location.MAX_LONGITUDE - 0.001;

    Location location = new Location(1, 1);
    exception.expect(IllegalArgumentException.class);
    location.getArcLengthDistance(Location.MAX_LATITUDE - 0.001, longitude);
  }

  /**
   * Values are given on the website: https://www.movable-type.co.uk/scripts/latlong.html
   */
  @Test
  public void getArcLengthDistance() throws Exception {
    Location location1 = new Location(32.13412, -56.12953);
    double arcLengthDistance1 = location1.getArcLengthDistance(89.12381, 176.81231);
    Assert.assertEquals(6493, arcLengthDistance1, 0.5);

    Location location2 = new Location(-82.13412, 46.12953);
    double arcLengthDistance2 = location2.getArcLengthDistance(1.89125, -1.864292);
    Assert.assertEquals(9632, arcLengthDistance2, 0.5);

    Location location3 = new Location(45.123123, 101.98419);
    double arcLengthDistance3 = location3.getArcLengthDistance(0.99872, 100.53123);
    Assert.assertEquals(4908, arcLengthDistance3, 0.5);
  }

  @Test
  public void equals() throws Exception {
    Location location1 = new Location(36.00000001, 42);
    Location location2 = new Location(36.00000002, 42);

    assertNotEquals(location1, location2);

    Location location3 = new Location(36.00000002, 42);
    assertEquals(location2, location3);
  }

  @Test
  public void testHashCode() throws Exception {
    Location location1 = new Location(36.00000001, 42);
    Location location2 = new Location(36.00000002, 42);

    assertNotEquals(location1.hashCode(), location2.hashCode());

    Location location3 = new Location(36.00000002, 42);
    assertEquals(location2.hashCode(), location3.hashCode());
  }
}