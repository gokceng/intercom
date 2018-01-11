package com.gguner.intercom;

import com.gguner.intercom.model.Customer;
import com.gguner.intercom.model.Location;
import com.gguner.intercom.service.customer.CustomerService;
import com.gguner.intercom.service.customer.FileBasedCustomerService;
import com.gguner.intercom.service.customer.exc.CustomerNotReadException;
import com.gguner.intercom.service.io.JsonResourceFileReader;
import com.gguner.intercom.service.io.ResourceReader;
import com.gguner.intercom.service.serdes.json.JsonSerDes;
import com.gguner.intercom.service.serdes.json.gson.GsonSerDes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * Gokcen Guner
 * 09/01/2018 00:00
 **/
public class Main {
  private static final double ORIGIN_LAT = 53.339428;
  private static final double ORIGIN_LON = -6.257664;
  private static final double MAX_DISTANCE = 100;
  private static final String CUSTOMERS_PATH = "customers.txt";

  public static void main(String[] args) throws Exception {
    CustomerService retrieveCustomerService = initializeServices();
    Set<Customer> customers = readData(retrieveCustomerService);
    filterOutTooFarCustomers(customers, ORIGIN_LAT, ORIGIN_LON, MAX_DISTANCE);
    ArrayList<Customer> customerList = new ArrayList<>(customers);
    Collections.sort(customerList);

    for (Customer customer : customerList) {
      System.out.println(customer.getId() + "\t" + customer.getName());
    }
  }

  private static CustomerService initializeServices() {
    JsonSerDes serDes = new GsonSerDes();
    ResourceReader<Customer> customerFileReader = new JsonResourceFileReader<>(serDes);
    return new FileBasedCustomerService(customerFileReader);
  }

  private static Set<Customer> readData(CustomerService retrieveCustomerService) throws CustomerNotReadException {
    Set<Customer> customers = retrieveCustomerService.getCustomers(CUSTOMERS_PATH);
    assert customers.size() == 32;
    return customers;
  }

  private static void filterOutTooFarCustomers(Set<Customer> customers, double lat, double lon, double maxDistance) {
    Iterator<Customer> iterator = customers.iterator();
    while (iterator.hasNext()) {
      Customer customer = iterator.next();
      Location location = customer.getLocation();
      double arcLengthDistance = location.getArcLengthDistance(lat, lon);
      if (arcLengthDistance > maxDistance) {
        iterator.remove();
      }
    }
  }
}
