package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.LoanDisbursementRepositary;
import in.bitlogic.digipokket.loan.app.repositary.SanctionRepository;
import in.bitlogic.digipokket.loan.app.service.LoanDisbursementService;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementService{

	@Autowired
	LoanDisbursementRepositary ldr;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public LoanDisbursement loanAmountDisbursement(LoanDisbursement ld,Integer customerId) {
		
		Optional<Customer> om=	customerRepository.findById(customerId);
		if(om.isPresent())
		{
			Customer customer=om.get();
			ld.setTotalLoanAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
			
			customer.setLoanDisbursement(ld);
			
			customerRepository.save(customer);
		}
			
			return null;
		}


	@Override
	public ByteArrayInputStream createDisbursementPdf(int customerId) 
	{
     
		  Optional<Customer> opsan1=customerRepository.findById(customerId);
           
 	      Customer  customer= opsan1.get();
		
		
		return null;
	}
		 
}
