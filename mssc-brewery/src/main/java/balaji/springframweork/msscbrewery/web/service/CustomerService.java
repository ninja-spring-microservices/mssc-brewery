package balaji.springframweork.msscbrewery.web.service;

import balaji.springframweork.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {

    public CustomerDTO getCustomerById(UUID customerId);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    void updateCustomer(UUID customerId, CustomerDTO customerDTO);

    void deleteCustomerById(UUID customerId);
}
