package in.bitlogic.digipokket.loan.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {

		Customer customer2=customerRepository.save(customer);
		
		return customer2;
	}

}
