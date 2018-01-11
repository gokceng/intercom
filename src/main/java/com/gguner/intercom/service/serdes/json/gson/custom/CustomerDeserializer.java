package com.gguner.intercom.service.serdes.json.gson.custom;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.model.Location;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Gokcen Guner
 * 09/01/2018 00:27
 **/
public class CustomerDeserializer implements JsonDeserializer<Customer> {
  static final String USER_ID = "user_id";
  static final String NAME = "name";
  static final String LATITUDE = "latitude";
  static final String LONGITUDE = "longitude";

  @Override
  public Customer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();

    int id = jsonObject.get(USER_ID).getAsInt();
    String name = jsonObject.get(NAME).getAsString();
    double latitude = jsonObject.get(LATITUDE).getAsDouble();
    double longitude = jsonObject.get(LONGITUDE).getAsDouble();

    return new Customer(id, name, new Location(latitude, longitude));
  }
}
