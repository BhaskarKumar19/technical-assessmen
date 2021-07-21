package elxsi.assessment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elxsi.assessment.models.CustomerDto;
import elxsi.assessment.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	@GetMapping(path = "/{customerNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	CustomerDto getCustomer(@PathVariable Integer customerNumber) {
		return customerService.getCustomerById(customerNumber);

	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	CustomerDto updateCustomer(@RequestBody CustomerDto customer) {
		return customerService.updateCustomer(customer);
	}

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	CustomerDto createCustomer(@RequestBody CustomerDto customer) {
		return customerService.createNewCustomer(customer);
	}

	@DeleteMapping(path = "/{customerNumber}", consumes = "application/json", produces = "application/json")
	void deleteCustomer(@PathVariable Integer customerNumber) {
		customerService.deleteCustomerById(customerNumber);
	}

	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	List<CustomerDto> getAllCustomers() {
		return customerService.getAllCustomers();
	}

}
