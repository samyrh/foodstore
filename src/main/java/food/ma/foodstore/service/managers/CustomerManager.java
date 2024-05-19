package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Customer;

import java.util.List;

public interface CustomerManager {

    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long customerId);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long customerId);
    List<Customer> getAllCustomers();
}
