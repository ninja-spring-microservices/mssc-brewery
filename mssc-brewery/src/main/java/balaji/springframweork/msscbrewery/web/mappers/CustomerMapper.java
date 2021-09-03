package balaji.springframweork.msscbrewery.web.mappers;

import balaji.springframweork.msscbrewery.domain.Customer;
import balaji.springframweork.msscbrewery.web.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerToCustomerDTO(CustomerDTO customerDTO);

    CustomerDTO customerDTOToCustomer(Customer customer);

}
