package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.internet.MimeMessage;

import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
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
	

	@Autowired
	JavaMailSender jms;
	
	@Value(value="${spring.mail.username}")
	String fromEmail;
	
	@Override
	public LoanDisbursement loanAmountDisbursement(LoanDisbursement ld,Integer customerId) {
		
		Optional<Customer> om=	customerRepository.findById(customerId);
		if(om.isPresent())
		{
			Customer customer=om.get();
			ld.setTotalLoanAmount(customer.getSanctionLetter().getLoanAmountSanctioned());
			ld.setPaymentStatus("Disbursed");
			customer.setLoanDisbursement(ld);
			customer.setApplicationStatus(String.valueOf(ApplicationStatus.DISBURSED));
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
       ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		
		

	
		String title = "Loan Disbursement Letter";
		  Date date1 = new Date();
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    String formatedDate = dateFormat.format(date1);

	    String dates = "Date:-" + formatedDate;
		String line="______________________________________________________________________________";
		String title2 = "__DigiPokket Finance Service__";
		String content1 = "Dear Customer,\r\n "+ customer.getFirstName()+" "+customer.getLastName()
				+ " This letter is to inform you that we have successfully disbursed the loan amount."
				+ "The loan amount of "+ customer.getSanctionLetter().getLoanAmountSanctioned()
				+ " has been transferred to your Account Number "+ customer.getAccountDetails().getAccountNumbber() +"."
				+ "\r\n We are pleased to have been able to provide you with the financing you needed to get your dreams comestrue. "
				+ "We hope that you are satisfied with our service."
				+ "\n\nThanks for chossing DigiPokket Finance Service, hope your experience is pleasant.";
	   
		String content2=" Your Sincerely,\n DigiPokket Finance Service";
		
		
	    	Document document = new Document();
		
		    PdfWriter.getInstance(document, out);
		
		
		document.open();

		
	//Title
		Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
		titleFont.setColor(CMYKColor.red);
		Paragraph titlePara=new Paragraph(title,titleFont);
		          titlePara.setAlignment(Element.ALIGN_CENTER);
		          document.add(titlePara);
	//Title2	          
	    Font title2Font=FontFactory.getFont(FontFactory.HELVETICA_BOLD,15);   
	    title2Font.setColor(CMYKColor.BLACK);
	    Paragraph title2Para=new Paragraph(title2,title2Font);
	               title2Para.setAlignment(Element.ALIGN_CENTER);
		          document.add(title2Para);
		//line          
		 Paragraph linePara=new Paragraph(line);
		           linePara.setAlignment(Element.ALIGN_CENTER);
		          document.add(linePara);
		         
		  //Date        
		  Font dateFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		  Paragraph datePara=new Paragraph(dates,dateFont);
		            datePara.setSpacingBefore(10);
		            datePara.setSpacingAfter(15);
		  		    document.add(datePara);
	
		
		Paragraph contentpara=new Paragraph(content1);
		document.add(contentpara);
		
		Font contentFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,10);
		titleFont.setColor(CMYKColor.BLACK);
		Paragraph contentPara=new Paragraph(content2,contentFont);
		          contentPara.setAlignment(Element.ALIGN_RIGHT);
		          contentPara.setSpacingBefore(10);
		          document.add(contentPara);
		document.close();
		customer.getLoanDisbursement().setLoanDisbursementLetter(out.toByteArray());
		
		InputStreamSource input=new ByteArrayResource(out.toByteArray());
        MimeMessage m=jms.createMimeMessage();
		
		try 
		{
			MimeMessageHelper helper=new MimeMessageHelper(m,true);
			helper.setTo(customer.getEmailId());
			helper.setFrom(fromEmail);
			helper.setText("In ref to the loan application of M/s "+customer.getFirstName()+" "+customer.getLastName()+". Based on the information you provided in your loan application, \nwe are pleased to inform you of disbursement of your loan for amount "+customer.getSanctionLetter().getLoanAmountSanctioned());
			helper.setSubject("Loan amount disbursed..!");
		helper.addAttachment("disbursement letter.pdf",input);
			jms.send(m);
		} 
		catch (Exception e1) 
		{

		e1.printStackTrace();
		
	    }
	
		customerRepository.save(customer);
	    System.out.println("Disburse");
 
		return new ByteArrayInputStream(out.toByteArray());
		
     	}
	

	  @Override
	   public List<Customer> getAllSanction(String string)
	  {
		List<Customer> customers=customerRepository.findAllByApplicationStatus(String.valueOf(ApplicationStatus.SANCTIONED));
		
		return customers;
	   }
		 
}
