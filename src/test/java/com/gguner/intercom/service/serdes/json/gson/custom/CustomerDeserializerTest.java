package com.gguner.intercom.service.serdes.json.gson.custom;

import com.gguner.intercom.model.Customer;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.gguner.intercom.service.serdes.json.gson.custom.CustomerDeserializer.LATITUDE;
import static com.gguner.intercom.service.serdes.json.gson.custom.CustomerDeserializer.LONGITUDE;
import static com.gguner.intercom.service.serdes.json.gson.custom.CustomerDeserializer.NAME;
import static com.gguner.intercom.service.serdes.json.gson.custom.CustomerDeserializer.USER_ID;
import static org.junit.Assert.*;

/**
 * Gokcen Guner
 * 11/01/2018 00:03
 **/
public class CustomerDeserializerTest {
  private JsonDeserializer<Customer> deserializer;

  @Rule
  public final ExpectedException exception = ExpectedException.none();
  private JsonObject jsonObject;

  @Before
  public void setUp() throws Exception {
    deserializer = new CustomerDeserializer();
    jsonObject = getJsonObject();
  }

  @Test
  public void deserializeValidCustomer() throws Exception {
    Customer customer = deserializer.deserialize(jsonObject, null, null);
    assertEquals(23.432121, customer.getLocation().getLatitude(), 0.0001);
    assertEquals(56.121243, customer.getLocation().getLongitude(), 0.0001);
    assertEquals(23, customer.getId());
    assertEquals("Some Name", customer.getName());
  }

  @Test
  public void deserializeMissingFieldCustomer() throws Exception {
    jsonObject.remove(LATITUDE);

    exception.expect(NullPointerException.class);
    deserializer.deserialize(jsonObject, null, null);
    fail("Shouldn't proceed to this line.");
  }

  @Test
  public void deserializeWrongFieldNameCustomer() throws Exception {
    jsonObject.remove(NAME);
    jsonObject.addProperty("NAME", "Some Name");

    exception.expect(NullPointerException.class);
    deserializer.deserialize(jsonObject, null, null);
    fail("Shouldn't proceed to this line.");
  }

  @Test
  public void deserializeInvalidFieldValueCustomer() throws Exception {
    jsonObject.addProperty(USER_ID, "345.123");

    exception.expect(NumberFormatException.class);
    deserializer.deserialize(jsonObject, null, null);
    fail("Shouldn't proceed to this line.");
  }

  private JsonObject getJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(USER_ID, "23");
    jsonObject.addProperty(NAME, "Some Name");
    jsonObject.addProperty(LATITUDE, "23.432121");
    jsonObject.addProperty(LONGITUDE, "56.121243");
    return jsonObject;
  }
}