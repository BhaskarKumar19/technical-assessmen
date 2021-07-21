package elxsi.assessment;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import elxsi.assessment.api.CustomerController;
import elxsi.assessment.models.CustomerDto;
import elxsi.assessment.service.ICustomerService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ICustomerService service;

	@Test
	@WithMockUser
	public void shouldGetCustomer() throws Exception {
		CustomerDto customer = new CustomerDto(2, "TestCustomer", "TestCustomer@test.com", true, 1234567899L);
		when(service.getCustomerById(3)).thenReturn(customer);
		this.mockMvc.perform(get("/customer/3")).andExpect(status().isOk())
				.andExpect(jsonPath("email", is(customer.getEmail())));
	}

	@Test
	@WithMockUser
	public void shouldUpdateCustomer() throws Exception {
		CustomerDto customer = new CustomerDto(2, "TestCustomer", "TestCustomer@test.com", true, 1234567899L);
		when(service.updateCustomer(Mockito.any(CustomerDto.class))).thenReturn(customer);
		this.mockMvc
				.perform(post("/customer/").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(customer)))
				.andExpect(status().isOk()).andExpect(jsonPath("email", is(customer.getEmail())));

	}

	@Test
	@WithMockUser
	public void shouldCreateCustomer() throws Exception {
		CustomerDto customer = new CustomerDto(2, "TestCustomer", "TestCustomer@test.com", true, 1234567899L);
		when(service.createNewCustomer(Mockito.any(CustomerDto.class))).thenReturn(customer);
		this.mockMvc
				.perform(put("/customer/").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(customer)))
				.andExpect(status().isOk()).andExpect(jsonPath("email", is(customer.getEmail())));

	}

	@Test
	@WithMockUser
	public void shouldDeleteCustomer() throws Exception {
		this.mockMvc.perform(delete("/customer/3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	@WithMockUser
	public void shouldGetAllCustomers() throws Exception {
		CustomerDto customer = new CustomerDto(2, "TestCustomer", "TestCustomer@test.com", true, 1234567899L);
		List<CustomerDto> customerList = new ArrayList<>();
		customerList.add(customer);
		when(service.getAllCustomers()).thenReturn(customerList);
		this.mockMvc.perform(get("/customer/")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email", is(customer.getEmail())));
	}

}
