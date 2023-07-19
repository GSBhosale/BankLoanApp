package in.bitlogic.digipokket.loan.app.service;

import java.util.List;

import in.bitlogic.digipokket.loan.app.model.Customer;

public interface CustomerService 
{

	public Customer createCustomer(Customer customer);

	public List<Customer> areaWiseUsers(String city);

}
