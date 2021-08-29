package balaji.springframweork.msscbrewery.web.service;

import balaji.springframweork.msscbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDTO getCustomerById(UUID customerId) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Balaji")
                .build();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name(customerDTO.getName())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        // TODO update logic
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        // TODO delete logic
        log.debug("Deleting a customer");
    }
}
