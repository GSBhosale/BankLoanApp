package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Address;
import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.repositary.AddressRepositary;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressRepositary addressRepositary;
	
	@Override
	public Customer createCustomer(Customer customer)
	{
		customer.getAccountDetails().setAccoubntHoldersName(customer.getFirstName()+" "+customer.getMiddleName()+" "+customer.getLastName());
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
		
		return customer;
	}

	@Override
	public List<Customer> areaWiseUsers(String city) {
		List<Customer> lc=customerRepository.findAll();
		List<Customer> listArea=new ArrayList();
		for(Customer c:lc) {
			if(c.getAddress().getLocalAddress().equals(city))
			{
				listArea.add(c);
			}
		}
		
		return listArea;
		
	}
	
	

}
