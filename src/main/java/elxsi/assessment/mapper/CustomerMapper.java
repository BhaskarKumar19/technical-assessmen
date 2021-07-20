package elxsi.assessment.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import elxsi.assessment.entity.Customer;
import elxsi.assessment.models.CustomerDto;

@Component
public class CustomerMapper {

	public List<Customer> customerDtoToEntity(List<CustomerDto> customerDto) {
		return customerDto.stream().map(entity -> new Customer(entity.getId(), entity.getName(), entity.getEmail(),
				entity.getStatus(), entity.getPhone())).collect(Collectors.toList());
	}

	public List<CustomerDto> customerEntityToDto(List<Customer> customers) {
		return customers.stream().map(
				dto -> new CustomerDto(dto.getId(), dto.getName(), dto.getEmail(), dto.getStatus(), dto.getPhone()))
				.collect(Collectors.toList());
	}

	public Customer customerDtoToEntity(CustomerDto customerDto) {
		return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getEmail(), customerDto.getStatus(),
				customerDto.getPhone());
	}

	public CustomerDto customerEntityToDto(Customer customer) {
		return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getStatus(),
				customer.getPhone());
	}
}
