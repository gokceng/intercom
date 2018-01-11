package com.gguner.intercom.service.serdes;

import com.gguner.intercom.service.serdes.exc.DeserializationException;

/**
 * Gokcen Guner
 * 09/01/2018 00:52
 **/
public interface SerDes {
  <T> T deserialize(String input, Class<T> classOfT) throws DeserializationException;
}
