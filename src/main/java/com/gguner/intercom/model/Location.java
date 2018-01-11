package com.gguner.intercom.model;

import java.util.Objects;

/**
 * Gokcen Guner
 * 09/01/2018 00:01
 **/
public class Location {
  private static final double DEGREE_IN_RADIAN = Math.PI / 180;
  private static final double MEAN_EARTH_RADIUS_IN_KM = 6371;
  static final int MAX_LATITUDE = 90;
  static final int MAX_LONGITUDE = 180;

  private final double latitude;
  private final double longitude;

  public Location(double latitude, double longitude) {
    validateLatLon(latitude, longitude);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getArcLengthDistance(double lat, double lon) {
    validateLatLon(lat, lon);

    double deltaLatitude = Math.abs(degreeToRadian(latitude - lat));
    double deltaLongitude = Math.abs(degreeToRadian(longitude - lon));

    double a = Math.pow(Math.sin(deltaLatitude / 2), 2) +
      Math.cos(degreeToRadian(latitude)) * Math.cos(degreeToRadian(lat)) *
        Math.pow(Math.sin(deltaLongitude / 2), 2);
    double centralAngleInRadian = 2 * Math.asin(Math.sqrt(a));
    return MEAN_EARTH_RADIUS_IN_KM * centralAngleInRadian;
  }

  private void validateLatLon(double lat, double lon) {
    if (Math.abs(lat) > MAX_LATITUDE) {
      throw new IllegalArgumentException("Latitude value should be between " + -MAX_LATITUDE + " and " + MAX_LATITUDE + ". Given value is " + lat);
    }
    if (Math.abs(lon) > MAX_LONGITUDE) {
      throw new IllegalArgumentException("Longitude value should be between " + -MAX_LONGITUDE + " and " + MAX_LONGITUDE + ". Given value is " + lat);
    }
  }

  private double degreeToRadian(double degree) {
    return degree * DEGREE_IN_RADIAN;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Location location = (Location) o;
    return Double.compare(location.latitude, latitude) == 0 &&
      Double.compare(location.longitude, longitude) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }

  @Override
  public String toString() {
    return "Location{" +
      "latitude=" + latitude +
      ", longitude=" + longitude +
      '}';
  }
}
