package elxsi.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elxsi.assessment.entity.Customer;
import elxsi.assessment.exception.InactiveCustomerException;
import elxsi.assessment.exception.NoRecordFoundException;
import elxsi.assessment.mapper.CustomerMapper;
import elxsi.assessment.models.CustomerDto;
import elxsi.assessment.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@Override
	public CustomerDto getCustomerById(int customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new NoRecordFoundException("customer does not exist with given id : " + customerId);
		}
		if (!customer.get().getStatus()) {
			throw new InactiveCustomerException("Inactive Customer Exception");
		}
		return customerMapper.customerEntityToDto(customer.get());

	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		return customerMapper.customerEntityToDto(customerRepository.findAll());
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());
		if (optionalCustomer.isEmpty()) {
			throw new NoRecordFoundException("customer does not exist with given id : " + customerDto.getId());
		}
		Customer customer = optionalCustomer.get();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhone(customerDto.getPhone());
		customer.setStatus(customerDto.getStatus());
		return customerMapper.customerEntityToDto(customerRepository.save(customer));
	}

	@Override
	public CustomerDto createNewCustomer(CustomerDto customerDto) {
		Customer customer = customerMapper.customerDtoToEntity(customerDto);
		return customerMapper.customerEntityToDto(customerRepository.save(customer));
	}

	@Override
	public void deleteCustomerById(int customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (null == customer) {
			throw new NoRecordFoundException("customer does not exist with given id : " + customerId);
		}
		customerRepository.delete(customer.get());
	}

}
