package com.gguner.intercom.service.io;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.model.Location;
import com.gguner.intercom.service.io.exc.ResourceFormatException;
import com.gguner.intercom.service.io.exc.ResourceNotReadException;
import com.gguner.intercom.service.serdes.json.JsonSerDes;
import com.gguner.intercom.service.serdes.json.gson.GsonSerDes;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Gokcen Guner
 * 11/01/2018 00:25
 **/
public class JsonResourceFileReaderTest {
  private ResourceReader<Customer> jsonFileReader;
  private JsonSerDes jsonSerDes;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    jsonSerDes = new GsonSerDes();
    jsonFileReader = new JsonResourceFileReader<>(jsonSerDes);
  }

  @Test
  public void readFromNonExistingFile() throws Exception {
    exception.expect(ResourceNotReadException.class);
    jsonFileReader.readFromFile("someNonExistentPath", null);
  }

  @Test
  public void readFromExistingValidFile() throws Exception {
    ResourceReader<Customer> jsonFileReader = new JsonResourceFileReader<>(jsonSerDes);
    Set<Customer> customers = jsonFileReader.readFromFile("validCustomers.txt", Customer.class);
    assertEquals(2, customers.size());
    assertTrue(customers.contains(new Customer(7, "John Doe", new Location(23.12312, -76.87632))));
    assertTrue(customers.contains(new Customer(9, "Jane Doe", new Location(49.104, 46.9371))));
  }

  @Test
  public void readFromExistingInvalidFile() throws Exception {
    exception.expect(ResourceFormatException.class);
    jsonFileReader.readFromFile("invalidCustomers.txt", Customer.class);
  }
}