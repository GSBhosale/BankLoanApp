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
	public Customer createCustomer(Customer customer)
	{
		customer.getAccountDetails().setAccoubntHoldersName(customer.getFirstName()+" "+customer.getMiddleName()+" "+customer.getLastName());
		//customer.getAddress().getSetCustomer().add(customer);
		if(customer.getCibil().getCibilScore()>300 && customer.getCibil().getCibilScore()<=550)
		{
			customer.getCibil().setCibilStatus("POOR");
		}
		else if(customer.getCibil().getCibilScore()>550 && customer.getCibil().getCibilScore()<=650)
		{
			customer.getCibil().setCibilStatus("AVERAGE");
		}
		else if(customer.getCibil().getCibilScore()>650 && customer.getCibil().getCibilScore()<=750)
		{
			customer.getCibil().setCibilStatus("GOOD");
		}
		else if(customer.getCibil().getCibilScore()>750 && customer.getCibil().getCibilScore()<=900)
		{
			customer.getCibil().setCibilStatus("EXCELLENT");
		}
		
		int count=customer.getOccupation().length();
		String username="digi"+customer.getFirstName().charAt(0)+customer.getLastName().charAt(0)+count;
		customer.setUsername(username);
		
		String password="dp"+customer.getFirstName().charAt(1)+customer.getLastName().charAt(1)+"@"+customer.getFirstName().length()+customer.getLastName().length()+7532;
		customer.setPassword(password);
		
		Customer customer2=customerRepository.save(customer);
		
		return customer2;
	}

}
