package balaji.springframweork.msscbrewery.web.controller;

import balaji.springframweork.msscbrewery.web.model.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCustomerById() throws Exception {
        mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Test")
                .build();

        String customerDTOJSON = objectMapper.writeValueAsString(customerDTO);

        mockMvc.perform(post("/api/v1/customer")
                .content(customerDTOJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Test")
                .build();

        String customerDTOJSON = objectMapper.writeValueAsString(customerDTO);

        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                .content(customerDTOJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}