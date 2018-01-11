package com.gguner.intercom.service.serdes.json.gson;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.service.serdes.exc.DeserializationException;
import com.gguner.intercom.service.serdes.json.JsonSerDes;
import com.gguner.intercom.service.serdes.json.gson.custom.CustomerDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Gokcen Guner
 * 09/01/2018 00:28
 **/
public class GsonSerDes implements JsonSerDes {
  private final Gson instance;

  public GsonSerDes() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Customer.class, new CustomerDeserializer());
    instance = gsonBuilder.create();
  }

  @Override
  public <T> T deserialize(String input, Class<T> classOfT) throws DeserializationException {
    try {
      return instance.fromJson(input, classOfT);
    } catch (RuntimeException e) {
      throw new DeserializationException("Deserialization failed for:" + input, e);
    }
  }
}
