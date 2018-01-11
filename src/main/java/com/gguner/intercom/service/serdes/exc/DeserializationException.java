package com.gguner.intercom.service.serdes.exc;

/**
 * Gokcen Guner
 * 09/01/2018 00:41
 **/
public class DeserializationException extends Exception {
  private static final long serialVersionUID = 1691293268067895977L;

  public DeserializationException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
