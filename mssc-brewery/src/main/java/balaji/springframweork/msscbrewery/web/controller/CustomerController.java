package balaji.springframweork.msscbrewery.web.controller;

import balaji.springframweork.msscbrewery.web.model.CustomerDTO;
import balaji.springframweork.msscbrewery.web.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@Valid @NotNull @PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.createCustomer(customerDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "api/v1/customer" + savedCustomerDTO.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomer(@Valid @NotNull @PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerId, customerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@Valid @NotNull @PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream().map(constraintViolation
                -> constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage()).collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
