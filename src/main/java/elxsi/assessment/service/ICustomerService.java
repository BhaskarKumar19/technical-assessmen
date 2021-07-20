package elxsi.assessment.service;

import java.util.List;

import elxsi.assessment.models.CustomerDto;

public interface ICustomerService {

    CustomerDto getCustomerById(int customerId);
	List<CustomerDto> getAllCustomers();
	CustomerDto updateCustomer(CustomerDto customer);
	CustomerDto createNewCustomer(CustomerDto customer);
    void deleteCustomerById(int customerId);
	
}
