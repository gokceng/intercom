package com.gguner.intercom.service.serdes.json.gson;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.service.serdes.exc.DeserializationException;
import com.gguner.intercom.service.serdes.json.JsonSerDes;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Gokcen Guner
 * 11/01/2018 00:43
 **/
public class GsonSerDesTest {
  private JsonSerDes serDes;
  private ClassLoader classLoader = getClass().getClassLoader();

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    serDes = new GsonSerDes();
  }

  @Test
  public void deserializeMap() throws Exception {
    String json = getString("map.json");
    Map map = serDes.deserialize(json, Map.class);
    assertEquals("Gokcen Guner", map.get("interviewee"));
  }

  @Test
  public void deserializeCollection() throws Exception {
    String json = getString("collection.json");
    Collection collection = serDes.deserialize(json, Collection.class);
    assertEquals(3, collection.size());
    assertTrue(collection.contains("Some"));
    assertTrue(collection.contains("String"));
    assertTrue(collection.contains("Values"));
  }

  @Test
  public void deserializeValidCustomer() throws Exception {
    String json = getString("validCustomer.json");
    Customer customer = serDes.deserialize(json, Customer.class);
    assertNotNull(customer);

    assertEquals(23.12312, customer.getLocation().getLatitude(), 0.0001);
    assertEquals(-76.87632, customer.getLocation().getLongitude(), 0.0001);
    assertEquals(7, customer.getId());
    assertEquals("John Doe", customer.getName());
  }

  @Test
  public void deserializeInvalidCustomer() throws Exception {
    String json = getString("invalidCustomer.json");
    exception.expect(DeserializationException.class);
    serDes.deserialize(json, Customer.class);
    fail("Shouldn't proceed to this line.");
  }

  private String getString(String path) throws IOException, URISyntaxException {
    return new String(Files.readAllBytes(Paths.get(classLoader.getResource(path).toURI())));
  }
}