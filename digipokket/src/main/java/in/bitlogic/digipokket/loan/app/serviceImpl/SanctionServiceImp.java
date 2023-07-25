package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.*;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.SanctionRepository;
import in.bitlogic.digipokket.loan.app.service.SanctionService;
import in.bitlogic.digipokket.loan.enums.ApplicationStatus;

@Service
public class SanctionServiceImp implements SanctionService
{
    @Autowired
    SanctionRepository sanRepository;
    
    @Autowired
    CustomerRepository customerRepository;
	
	@Autowired
	JavaMailSender jms;
	
	@Value(value="${spring.mail.username}")
	String fromEmail;
	
	public ByteArrayInputStream createPdf(int customerId) 
	{

//
//	     Optional<SanctionLetter> opsan=sanRepository.findById(customerId);
//     	
//     	    SanctionLetter sanction = opsan.get();
     	                            
     	   Optional<Customer> opsan1=customerRepository.findById(customerId);
     	                 
     	      Customer  customer= opsan1.get();



//	     Optional<SanctionLetter> opsan=sanRepository.findById(sanctionId);
//     	
//     	    SanctionLetter sanction = opsan.get();

		String title = "Final Sanction Letter";

	    Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    String formatedDate = dateFormat.format(date);

     	String to = "Date:-" + formatedDate + "\n " + "To:-";

	    String text = "Dear "+customer.getFirstName()+" "+customer.getLastName()+",\n We Thank you for choosing our digiPOKKET bank, We are pleased to inform you that we have in principal approved loan to you as per Terms and Condition mentioned below.";

	    String termsncondition= "Additional condition to comply prior to disbursal: \n 1.Repayment from digiPOKKET bank. \n 2.Confirmation form Official ID and Copy of ID required. \n 3.The Borrower will be required to reply within 21 days from date of issue of said Notice \n 4. Legal vetting & Search to be done.\r\n"
	   
	    		+ "5. NOC from tenant at offered collateral.\r\n"
	    	
	    		+ "6. Positive Residential & Office CPV Initiated\r\n";
	    	
//	    		+ "7. Confirmation form Official ID and Copy of ID required.\r\n";
	    		
	    		  
	    String line="______________________________________________________________________________";
		String  thanksText="Thank you,\n regards digiPOKKET!";
		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
		Document document=new Document();
		
		PdfWriter.getInstance(document, out);
		document.open();
//Title			
		Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
		titleFont.setColor(CMYKColor.red);
		Paragraph titlePara=new Paragraph(title,titleFont);
		Paragraph linePara=new Paragraph(line);
		 titlePara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlePara);
		document.add(linePara);
//To 			
		Font toFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		Paragraph toPara=new Paragraph(to,toFont);
		toPara.setSpacingBefore(20);
		document.add(toPara);

//Subject			
		Font SubjectFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		Paragraph textPara=new Paragraph(text,SubjectFont);
		textPara.setSpacingBefore(5);
		document.add(textPara);
//terms and condition		
//		Font termconditionfont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//		Paragraph termcon=new Paragraph(termsncondition,termconditionfont);
//		termcon.setSpacingBefore(20);
//		document.add(termcon);		
//		
		

//		------------1.Table For Customer Details----------------------------------------
		
		PdfPTable table1=new PdfPTable(2);
		table1.setWidths(new int[] {4,6});
		table1.setWidthPercentage(100F);
		table1.setSpacingBefore(30);
		
		
		//-------------Cell--------
		PdfPCell headCell2=new PdfPCell();
		headCell2.setPadding(5);
		headCell2.setPaddingLeft(10);
		headCell2.setBackgroundColor(CMYKColor.white);
		Font headCellFont1 =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headCellFont1.setColor(CMYKColor.BLACK);
		
	    //-------Adding Phrase-- 
		headCell2.setPhrase(new Phrase("Customer Id",headCellFont1));
		table1.addCell(headCell2);
		headCell2.setPhrase(new Phrase(String.valueOf(customer.getCustomerId())));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("First Name",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(customer.getFirstName()));
   		table1.addCell(headCell2);
	
   		headCell2.setPhrase(new Phrase("Middle Name",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(customer.getMiddleName()));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("Last Name",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(customer.getLastName()));
   		table1.addCell(headCell2);
   		
		headCell2.setPhrase(new Phrase("Mobile Number",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(String.valueOf(customer.getMobileNo())));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("Date of Birth",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(String.valueOf(customer.getDateOfBirth())));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("Gender",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(customer.getGender()));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("Age",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(String.valueOf(customer.getAge())));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("Email Id",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(String.valueOf(customer.getEmailId())));
   		table1.addCell(headCell2);
   		
   		headCell2.setPhrase(new Phrase("Education",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(customer.getEducation()));
   		table1.addCell(headCell2);
   		
		headCell2.setPhrase(new Phrase("Occupation",headCellFont1));
		table1.addCell(headCell2);
   		headCell2.setPhrase(new Phrase(customer.getOccupation()));
   		table1.addCell(headCell2);
// 		headCell.setPhrase(new Phrase("Loan Tenure",headCellFont));
//		table.addCell(headCell);
//		headCell.setPhrase(new Phrase(String.valueOf(sanction.getLoanTenure())));
//   		table.addCell(headCell);
//   		
//   		headCell.setPhrase(new Phrase("Total Interest Ammount",headCellFont));
//		table.addCell(headCell);
//		headCell.setPhrase(new Phrase(String.valueOf(sanction.getTotalInterestAmount())));
//   		table.addCell(headCell);
//   		
//   		headCell.setPhrase(new Phrase("Monthly EMI",headCellFont));
//		table.addCell(headCell);
//		headCell.setPhrase(new Phrase(String.valueOf(sanction.getMonthlyEmiAmount())));
//   		table.addCell(headCell);
//   		
	
   	
   		
   		
		document.add(table1);
		
		
		
		
		
		
		
		
		
//		------------2.Table For Loan Application----------------------------------------
		
		PdfPTable table=new PdfPTable(2);
		table.setWidths(new int[] {4,6});
		table.setWidthPercentage(100F);
		table.setSpacingBefore(30);
		
		
		//-------------Cell--------
		PdfPCell headCell=new PdfPCell();
		headCell.setPadding(5);
		headCell.setPaddingLeft(10);
		headCell.setBackgroundColor(CMYKColor.white);
		Font headCellFont =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headCellFont.setColor(CMYKColor.BLACK);
		
	    //-------Adding Phrase-- 
		headCell.setPhrase(new Phrase("Sanction Id",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getSanctionId())));
   		table.addCell(headCell);
   		
   		headCell.setPhrase(new Phrase("Sanction Date",headCellFont));
		table.addCell(headCell);
   		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getSanctionDate())));
   		table.addCell(headCell);
	

   		
   		headCell.setPhrase(new Phrase("Loan Ammount Sanctioned",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanAmountSanctioned())));
   		table.addCell(headCell);
		
   		headCell.setPhrase(new Phrase("Rate Of Interest",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getRateOfInterest())));
   		table.addCell(headCell);
		
 		headCell.setPhrase(new Phrase("Loan Tenure",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getLoanTenure())));
   		table.addCell(headCell);
   		
   		headCell.setPhrase(new Phrase("Total Interest Ammount",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getTotalInterestAmount())));
   		table.addCell(headCell);
   		
   		headCell.setPhrase(new Phrase("Monthly EMI",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase(String.valueOf(customer.getSanctionLetter().getMonthlyEmiAmount())));
   		table.addCell(headCell);
   		
		
//		PdfPCell dataCell= new PdfPCell();
//		dataCell.setPadding(3);
//		dataCell.setBackgroundColor(CMYKColor.white);
//		Font headCellFont1 =FontFactory.getFont(FontFactory.defaultEncoding);
//		headCellFont1.setColor(CMYKColor.BLACK);
		
//		dataCell.setPhrase(new Phrase(String.valueOf(sanction.getSanctionId())));
//   		table.addCell(dataCell);
//   		dataCell.setPhrase(new Phrase(String.valueOf(sanction.getSanctionDate())));
//   		table.addCell(dataCell);
//   		dataCell.setPhrase(new Phrase(sanction.getApplicantName()));
//   		table.addCell(dataCell);
//   		dataCell.setPhrase(new Phrase(sanction.getContactDetails()));
//   		table.addCell(dataCell);
   	
   		
   		
		
   		document.add(table);
		
		Paragraph termscon=new Paragraph(termsncondition,headCellFont);
		termscon.setAlignment(Element.ALIGN_LEFT);
		termscon.setSpacingBefore(20);
		document.add(termscon);
		
	
		Paragraph thankPara=new Paragraph(thanksText,headCellFont);
		thankPara.setAlignment(Element.ALIGN_RIGHT);
		document.add(thankPara);
	
     	document.close();
     	ByteArrayInputStream pdf	=new ByteArrayInputStream(out.toByteArray());
//		 byte[] byteArray = pdf.readAllBytes();
//	 byteArray=pdf.readAllBytes();
		
     	customer.getSanctionLetter().setSactionLetter(out.toByteArray());
     	customer.getSanctionLetter().setSactionStatus(String.valueOf(ApplicationStatus.SANCTIONED));
     	customerRepository.save(customer);
    	InputStreamSource input=new ByteArrayResource(out.toByteArray());

MimeMessage m=jms.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(m,true);
			helper.setTo(customer.getEmailId());
			helper.setFrom(fromEmail);
			helper.setText("In ref to the loan application of M/s "+customer.getFirstName()+" "+customer.getLastName()+". Based on the information you provided in your loan application, \nwe are pleased to inform you of approval of your loan for amount "+customer.getSanctionLetter().getLoanAmountSanctioned());
			helper.setSubject("Loan proposal aproved..!");
		helper.addAttachment("sanction letter.pdf", input);
			jms.send(m);
		} catch (Exception e1) {

		e1.printStackTrace();
	}
		return pdf;
	   

	}

	
	
	@Override
	public Customer sanctionLoan(SanctionLetter sanLetter, int customerId) {
		
	Optional<Customer> om=	customerRepository.findById(customerId);
	
		Customer customer=om.get();
		
		customer.setSanctionLetter(sanLetter);
		
		double rate=sanLetter.getRateOfInterest();
		int ten=sanLetter.getLoanTenure();
		
		double amount=sanLetter.getLoanAmountSanctioned();
	double processingFees=amount*(0.25/100);
		rate=rate/(12*100);
		ten=ten*12; 
		//double E=am*r*(Math.pow(1+r,n)/(Math.pow(1+r,n)-1));

		 double E=amount*rate*(Math.pow(1+rate,ten)/(Math.pow(1+rate,ten)-1));
		sanLetter.setMonthlyEmiAmount(E);
		 double totalAm=E*ten;
		 sanLetter.setTotalLoanAmountWithInterest(totalAm+processingFees);
		 double totalInterest=totalAm-amount;
		 sanLetter.setTotalInterestAmount(totalAm-amount);
		 sanLetter.setProcessingFees(processingFees);
		 customer.setApplicationStatus(String.valueOf(ApplicationStatus.SANCTIONED));
		customerRepository.save(customer);
		
		
		return customer;
	}



	@Override
	public List<Customer> getSanction(String valueOf)
	{
		List<Customer> cust=customerRepository.findAllByApplicationStatus(valueOf);
		return cust;
	}

}
