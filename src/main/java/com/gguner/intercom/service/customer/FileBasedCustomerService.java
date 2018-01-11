package com.gguner.intercom.service.customer;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.service.customer.exc.CustomerNotReadException;
import com.gguner.intercom.service.io.ResourceReader;
import com.gguner.intercom.service.io.exc.ResourceNotReadException;

import java.util.Set;

/**
 * Gokcen Guner
 * 09/01/2018 00:08
 **/
public class FileBasedCustomerService implements CustomerService {
  private final ResourceReader<Customer> customerFileReader;

  public FileBasedCustomerService(ResourceReader<Customer> customerFileReader) {
    this.customerFileReader = customerFileReader;
  }

  @Override
  public Set<Customer> getCustomers(String resourcePath) throws CustomerNotReadException {
    try {
      return customerFileReader.readFromFile(resourcePath, Customer.class);
    } catch (ResourceNotReadException e) {
      throw new CustomerNotReadException("For given path " + resourcePath, e);
    }
  }
}
