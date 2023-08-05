package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.bitlogic.digipokket.loan.app.model.Address;
import in.bitlogic.digipokket.loan.app.model.AllPersonalDocuments;
import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.model.Ledger;
import in.bitlogic.digipokket.loan.app.repositary.AddressRepositary;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.EnquiryRepositary;
import in.bitlogic.digipokket.loan.app.service.CustomerService;
import in.bitlogic.digipokket.loan.enums.ApplicationStatus;
import in.bitlogic.digipokket.loan.enums.EnquiryStatus;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressRepositary addressRepositary;
	
	@Autowired
	JavaMailSender jms;
	
	
	@Value(value="${spring.mail.username}")
	String fromEmail;
	
	
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
		
		customer.setApplicationStatus(String.valueOf(ApplicationStatus.APPLIED));
		Ledger l=new Ledger();
		customer.setLedger(l);
		customer.setDesignation("Customer");
		Customer customer2=customerRepository.save(customer);
		
		Optional<Customer> oe=customerRepository.findByFirstNameAndLastName(customer.getFirstName(),customer.getLastName());
		Customer e=oe.get();
			
			SimpleMailMessage sm=new SimpleMailMessage();
			
			sm.setFrom(fromEmail);
			sm.setTo(e.getEmailId());
			sm.setSubject("LETTER FOR REJECT OF LOAN");
			sm.setText("This letter is to notify M/s"+e.getFirstName()+" "+e.getLastName()+" about the Successfullloan request that you submitted at digipokket Pvt. Ltd.\n "
					+ "Your username: "+e.getUsername()+"\n"+"Your password: "+e.getPassword());
					
			
			jms.send(sm);
		
		
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

	@Override
	public List<Customer> verifyDocuments() {
		
		List<Customer> c=customerRepository.findAll();
		return c;
	}

	@Override
	public Customer verifyDocs(int customerId) {
		
//		Customer cust=customerRepository.save(null);
		Customer cust= customerRepository.getById(customerId);
		System.out.println(cust);
		cust.setApplicationStatus(String.valueOf(ApplicationStatus.VERIFIED));
		System.out.println(cust.getApplicationStatus());
		return customerRepository.save(cust);
		
	}

	@Override
	public Customer completeUplodDocs(int customerId) {
		Customer cust= customerRepository.getById(customerId);
		System.out.println(cust);
		cust.setApplicationStatus(String.valueOf(ApplicationStatus.REUPLOAD_DOCUMENTS));
		System.out.println(cust.getApplicationStatus());
		return customerRepository.save(cust);
	}

	@Override
	public Customer authCustomer(String uname, String pass) {
		return customerRepository.findByUsernameAndPassword(uname,pass);
	}

	@Override
	public Customer getSingleCustomer(int customerId) {

		Optional<Customer> oc=customerRepository.findById(customerId);
		return oc.get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}


	
	

}
