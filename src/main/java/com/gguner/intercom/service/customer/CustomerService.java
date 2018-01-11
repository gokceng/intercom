package com.gguner.intercom.service.customer;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.service.customer.exc.CustomerNotReadException;

import java.util.Set;

/**
 * Gokcen Guner
 * 09/01/2018 00:07
 **/
public interface CustomerService {
  Set<Customer> getCustomers(String resourcePath) throws CustomerNotReadException;
}
