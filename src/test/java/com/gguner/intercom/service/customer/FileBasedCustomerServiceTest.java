package com.gguner.intercom.service.customer;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.model.Location;
import com.gguner.intercom.service.customer.exc.CustomerNotReadException;
import com.gguner.intercom.service.io.ResourceReader;
import com.gguner.intercom.service.io.exc.ResourceNotReadException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Gokcen Guner
 * 11/01/2018 00:44
 **/
@RunWith(MockitoJUnitRunner.class)
public class FileBasedCustomerServiceTest {
  private CustomerService customerService;
  @Mock
  private ResourceReader<Customer> customerResourceReader;
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    customerService = new FileBasedCustomerService(customerResourceReader);
  }

  @Test
  public void getCustomersSuccess() throws Exception {
    String path = "doesn't matter actually";

    HashSet<Customer> customers = new HashSet<Customer>() {{
      add(new Customer(7, "John Doe", new Location(23.12312, -76.87632)));
    }};
    when(customerResourceReader.readFromFile(eq(path), eq(Customer.class))).thenReturn(customers);

    Set<Customer> customerSet = customerService.getCustomers(path);
    Assert.assertSame(customers, customerSet);
  }

  @Test
  public void getCustomersResourceReadFailure() throws Exception {
    String path = "doesn't matter actually";
    ResourceNotReadException testException = new ResourceNotReadException("Test Exception", null);
    when(customerResourceReader.readFromFile(eq(path), eq(Customer.class))).thenThrow(testException);

    exception.expect(CustomerNotReadException.class);
    customerService.getCustomers(path);
  }

  @Test
  public void getCustomersOtherFailure() throws Exception {
    String path = "doesn't matter actually";
    RuntimeException testException = new NullPointerException("Test Exception");
    when(customerResourceReader.readFromFile(eq(path), eq(Customer.class))).thenThrow(testException);

    exception.expect(NullPointerException.class);
    customerService.getCustomers(path);
  }
}