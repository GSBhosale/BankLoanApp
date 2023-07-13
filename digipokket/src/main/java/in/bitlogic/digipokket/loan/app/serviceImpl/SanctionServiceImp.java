package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class SanctionServiceImp implements SanctionService
{
    @Autowired
    SanctionRepository sanRepository;
    
    @Autowired
    CustomerRepository customerRepository;
	
	@Override
	public ByteArrayInputStream createPdf(int sanctionId) 
	{

	     Optional<SanctionLetter> opsan=sanRepository.findById(sanctionId);
     	
     	    SanctionLetter sanction = opsan.get();
	
		String title = "Sanction Application Form";

	    Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    String formatedDate = dateFormat.format(date);

     	String to = "Date:-" + formatedDate + "\n " + "To:-";

	    String text = "Subject:-" + "This is the Sanctioned Loan";

	      
	 
		String  thanksText="Thank you,\n regards cjc!";
		
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
		Document document=new Document();
		
		PdfWriter.getInstance(document, out);
		document.open();
//Title			
		Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
		titleFont.setColor(CMYKColor.red);
		Paragraph titlePara=new Paragraph(title,titleFont);
		 titlePara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlePara);
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
		
//		------------1.Table For Loan Application----------------------------------------
		
		PdfPTable table=new PdfPTable(4);
		table.setWidths(new int[] {3,3,3,3});
		table.setWidthPercentage(100F);
		table.setSpacingBefore(30);
		
		
		//-------------Cell--------
		PdfPCell headCell=new PdfPCell();
		headCell.setPadding(5);
		headCell.setPaddingLeft(10);
		headCell.setBackgroundColor(CMYKColor.white);
		Font headCellFont =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headCellFont.setColor(CMYKColor.BLACK);
		
	    //-------Adding Phress-- 
		headCell.setPhrase(new Phrase("Sanction Id",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase("Sanction Date",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase("Applicant Name",headCellFont));
		table.addCell(headCell);
		headCell.setPhrase(new Phrase("Contact Details",headCellFont));
		table.addCell(headCell);
		
		
		PdfPCell dataCell= new PdfPCell();
		dataCell.setPadding(3);
		dataCell.setBackgroundColor(CMYKColor.white);
		Font headCellFont1 =FontFactory.getFont(FontFactory.defaultEncoding);
		headCellFont1.setColor(CMYKColor.BLACK);
		
		dataCell.setPhrase(new Phrase(String.valueOf(sanction.getSanctionId())));
   		table.addCell(dataCell);
   		dataCell.setPhrase(new Phrase(String.valueOf(sanction.getSanctionDate())));
   		table.addCell(dataCell);
   		dataCell.setPhrase(new Phrase(sanction.getApplicantName()));
   		table.addCell(dataCell);
   		dataCell.setPhrase(new Phrase(sanction.getContactDetails()));
   		table.addCell(dataCell);
   	
   		
   		
		document.add(table);
		Paragraph thankPara=new Paragraph(thanksText);
		thankPara.setAlignment(Element.ALIGN_RIGHT);
	
		
		document.add(thankPara);
	
     	document.close();
     	
     	
	
	    return new ByteArrayInputStream(out.toByteArray());
	   
	}

	
	
	@Override
	public SanctionLetter sanctionLoan(SanctionLetter sanLetter, int customerId) {
		
	Optional<Customer> om=	customerRepository.findById(customerId);
	if(om.isPresent())
	{
		Customer customer=om.get();
		
		customer.setSanctionLetter(sanLetter);
		
		double rate=sanLetter.getRateOfInterest();
		int ten=sanLetter.getLoanTenure();
		
		double amount=sanLetter.getLoanAmountSanctioned();
	
		rate=rate/(12*100);
		ten=ten*12;
		//double E=am*r*(Math.pow(1+r,n)/(Math.pow(1+r,n)-1));

		 double E=amount*rate*(Math.pow(1+rate,ten)/(Math.pow(1+rate,ten)-1));
		sanLetter.setMonthlyEmiAmount(E);
		 double totalAm=E*ten;
		 sanLetter.setTotalLoanAmountWithInterest(totalAm);
		 double totalInterest=totalAm-amount;
		 
		 sanLetter.setTotalInterestAmount(totalAm-amount);

		customerRepository.save(customer);
	}
		
		return null;
	}

}
