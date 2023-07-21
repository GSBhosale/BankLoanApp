package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.EnquiryRepositary;
import in.bitlogic.digipokket.loan.app.repositary.LoanDisbursementRepositary;
import in.bitlogic.digipokket.loan.app.repositary.SanctionRepository;
import in.bitlogic.digipokket.loan.app.service.LoanDisbursementService;
import in.bitlogic.digipokket.loan.enums.ApplicationStatus;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementService{

	@Autowired
	LoanDisbursementRepositary ldr;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EnquiryRepositary enquiryRepository;
	
	
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
	public ByteArrayInputStream createDisbursementPdf(int customerId) throws DocumentException 
	{
     
		  Optional<Customer> opsan1=customerRepository.findById(customerId);
           
 	      Customer  customer= opsan1.get();
		
		Date date=new Date();
		customer.getLoanDisbursement().setLoanNo(ThreadLocalRandom.current().nextLong(999, 9999));
		customer.getLoanDisbursement()
		.setTotalLoanAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
		customer.getLoanDisbursement()
		.setTransferAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
		
		customer.getLoanDisbursement()
		.setDisburseAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
		
//customer.getLoanDisbursement().setPaymentStatus(String.valueOf(CustomerStatus.loandisbursed));
//customer.setCustomerStatus(String.valueOf(CustomerStatus.loandisbursed));
customer.getLoanDisbursement().setAmountPaidDate(date);
Optional<Enquiry> optional1 = enquiryRepository.findById(customer.getCustomerId());

	if (optional1.isPresent()) {
		Enquiry enquiry = optional1.get();
		//enquiry.setEnquiryStatus(String.valueOf(CustomerStatus.loandisbursed));
		enquiryRepository.save(enquiry);
		SLF4JLogger slf4jLogger = new SLF4JLogger(null, null);
		slf4jLogger.info("Loan Disbursement PDF started");
	
		String title = "Shree Ganesh Finace";
		String title2 = "\nLoan Disbursement Letter";
		String content1 = "Dear , " + customer.getFirstName()+" "+customer.getLastName()
				+ " This letter is to inform you that we have successfully disbursed the loan funds for your car loan. "
				+ "The loan amount of " + customer.getSanctionLetter().getLoanAmountSanctioned()
				+ " has been transferred to the dealer's account and the car is ready "
				+ "for delivery.\n\nWe are pleased to have been able to provide you with the financing you needed to purchase the car of your dreams. "
				+ "We hope that you are satisfied with our service and that you enjoy driving your new car."
				+ "\n\nThanks for chossing shree Ganesh Finace, hope your expiriance is pleasant.\n\nSincerely,\nShree Ganesh Finace";
	
		ByteArrayOutputStream opt = new ByteArrayOutputStream();
		
		Document document = new Document();
		PdfWriter.getInstance(document, opt);
		
		document.open();
	
	
	}
 
		return null;
	}

	@Override
	public List<Customer> getAllSanction(String string) {
		List<Customer> customers=customerRepository.findAllByApplicationStatus(String.valueOf(ApplicationStatus.SANCTIONED));
		
		return customers;
	}
		 
}
