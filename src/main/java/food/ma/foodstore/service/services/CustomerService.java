package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.service.managers.CustomerManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService implements CustomerManager {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {

        customerRepository.deleteById(customerId);
    }

    @Override// Additional method to retrieve all customers
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
}
