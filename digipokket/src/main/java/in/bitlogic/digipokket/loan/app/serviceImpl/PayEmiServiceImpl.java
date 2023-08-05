package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.EMI;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.PayEmiRepository;
import in.bitlogic.digipokket.loan.app.service.PayEmiService;
import in.bitlogic.digipokket.loan.enums.EMIStatus;
@Service
public class PayEmiServiceImpl implements PayEmiService{

	@Autowired
	PayEmiRepository emiRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer payEmi(int emiId, int customerId) {

		Optional<EMI> oemi= emiRepository.findById(emiId);
		EMI emi=oemi.get();
		emi.setEmiStatus(String.valueOf(EMIStatus.PAID));
		
		emiRepository.save(emi);
		
		Optional<Customer> oc=customerRepository.findById(customerId);
		Customer customer=oc.get();
		customer.getSanctionLetter().setTotalLoanAmountWithInterest(emi.getRemainingAmount());
		customer.getLedger().setNoOfEmiPaid(customer.getLedger().getNoOfEmiPaid()+1);
		customer.getLedger().setNoOfEmiUnpaid(customer.getLedger().getNoOfEmiUnpaid()-1);
		
		return customerRepository.save(customer);
	}

	@Override
	public Customer getAllEmi(int customerId) {

		Optional<Customer> oc=customerRepository.findById(customerId);
		Customer customer=oc.get();
		return customer;
	}
	
	
}
