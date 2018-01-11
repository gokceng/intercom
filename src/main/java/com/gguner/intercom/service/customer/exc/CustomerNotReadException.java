package com.gguner.intercom.service.customer.exc;

/**
 * Gokcen Guner
 * 10/01/2018 00:17
 **/
public class CustomerNotReadException extends Exception {
  private static final long serialVersionUID = 1778722666356364342L;

  public CustomerNotReadException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
