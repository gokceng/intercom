package com.gguner.intercom.service.io.exc;

/**
 * Gokcen Guner
 * 10/01/2018 00:28
 **/
public class ResourceFormatException extends ResourceNotReadException {
  private static final long serialVersionUID = -6138033057632668265L;

  public ResourceFormatException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
