package in.bitlogic.digipokket.loan.app.service;

import java.util.List;
import java.util.Optional;

import in.bitlogic.digipokket.loan.app.model.Customer;

public interface CustomerService 
{

	public Customer createCustomer(Customer customer);

	public List<Customer> areaWiseUsers(String city);

	public List<Customer> verifyDocuments();

	public Customer verifyDocs(int customerId);

	public Customer completeUplodDocs(int customerId);

}
