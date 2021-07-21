package elxsi.assessment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import elxsi.assessment.entity.Customer;
import elxsi.assessment.exception.InactiveCustomerException;
import elxsi.assessment.exception.NoRecordFoundException;
import elxsi.assessment.mapper.CustomerMapper;
import elxsi.assessment.models.CustomerDto;
import elxsi.assessment.repository.CustomerRepository;
import elxsi.assessment.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	private CustomerServiceImpl service;

	@Test
	public void shouldGetCustomer() throws Exception {
		when(customerRepository.findById(2))
				.thenReturn(Optional.of(new Customer(2, "testUser", "123@test", true, 2345678765L)));
		assertTrue(service.getCustomerById(2).getId() == 2);
	}

	@Test
	public void shouldGetAllCustomer() throws Exception {
		Customer customer = new Customer(2, "TestCustomer", "TestCustomer@test.com", true, 1234567899L);
		Customer customer2 = new Customer(3, "TestCustomer2", "TestCustomer@test.com", true, 1234567899L);
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		customerList.add(customer2);
		when(customerRepository.findAll()).thenReturn(customerList);
		assertTrue(service.getAllCustomers().get(0).getId() == 2);
	}

	@Test
	public void shouldNotGetInactiveCustomer() throws Exception {
		when(customerRepository.findById(2))
				.thenReturn(Optional.of(new Customer(2, "testUser", "123@test", false, 2345678765L)));
		assertThrows(InactiveCustomerException.class, () -> {
			service.getCustomerById(2);
		});
	}

	@Test
	public void shoulCreateNewCustomer() throws Exception {
		when(customerRepository.save(Mockito.any(Customer.class)))
				.thenReturn(new Customer(2, "testUser", "123@test", true, 2345678765L));
		assertTrue(service.createNewCustomer(new CustomerDto()).getId() == 2);

	}

	@Test
	public void shoulUpdateCustomer() throws Exception {
		Customer customer = new Customer(2, "testUser", "123@test", true, 2345678765L);
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(2);
		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
		when(customerRepository.findById(2)).thenReturn(Optional.of(customer));
		assertTrue(service.updateCustomer(customerDto).getId() == 2);

	}

	@Test
	public void shoulDeleteCustomer() throws Exception {
		when(customerRepository.findById(2)).thenReturn(null);
		assertThrows(NoRecordFoundException.class, () -> {
			service.deleteCustomerById(2);
		});
	}

}
