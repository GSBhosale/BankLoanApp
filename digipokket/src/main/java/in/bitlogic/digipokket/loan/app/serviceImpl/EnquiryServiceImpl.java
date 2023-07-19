package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.repositary.EnquiryRepositary;
import in.bitlogic.digipokket.loan.app.service.EnquiryService;
import in.bitlogic.digipokket.loan.enums.EnquiryStatus;
@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	EnquiryRepositary equiryRepo;
	
	@Autowired
	JavaMailSender jms;
	
	@Autowired(required=true)
	RestTemplate rst;

	@Value(value="${spring.mail.username}")
	String fromEmail;

	@Override
	public Enquiry makeEnquiry(Enquiry e) {
		
//		if(e.getCibilScore()>300 && e.getCibilScore()<=550)
//		{
//			e.setCibilStatus("POOR");
//		}
//		else if(e.getCibilScore()>550 && e.getCibilScore()<=650)
//		{
//			e.setCibilStatus("AVERAGE");
//		}
//		else if(e.getCibilScore()>650 && e.getCibilScore()<=750)
//		{
//			e.setCibilStatus("GOOD");
//		}
//		else if(e.getCibilScore()>750 && e.getCibilScore()<=900)
//		{
//			e.setCibilStatus("EXCELLENT");
//		}
		
//		SimpleMailMessage sm=new SimpleMailMessage();
//		
//		sm.setFrom(fromEmail);
//		sm.setTo(e.getEmailId());
//		sm.setSubject("LETTER FOR SUBMITING ENQUIRY OF LOAN");
//		sm.setText("Dear recipient M/s"+e.getFirstName()+" "+e.getLastName()+"\n \n    With regars to your letter enquiring about applying for a loan, could you visit to bank site and discuss the matter with our Relationship Executive during banking hours..");
//				
//		
	//	jms.send(sm);
		
		e.setEnquiryStatus(String.valueOf(EnquiryStatus.CREATED));
		return equiryRepo.save(e);
	}

	@Override
	public List<Enquiry> viewAllEnquiry() {
		return equiryRepo.findAllByEnquiryStatusOrEnquiryStatusOrEnquiryStatus(String.valueOf(EnquiryStatus.CREATED),String.valueOf(EnquiryStatus.CIBIL_REJECT),String.valueOf(EnquiryStatus.CIBIL_OK));
	}

	@Override
	public List<Enquiry> rejectEnquiry(int eid) 
	{
		
		
		Optional<Enquiry> oe=equiryRepo.findById(eid);
		Enquiry e=oe.get();
			e.setEnquiryStatus(String.valueOf(EnquiryStatus.REJECTED));
			SimpleMailMessage sm=new SimpleMailMessage();
			
			sm.setFrom(fromEmail);
			sm.setTo(e.getEmailId());
			sm.setSubject("LETTER FOR REJECT OF LOAN");
			sm.setText("This letter is to notify M/s"+e.getFirstName()+" "+e.getLastName()+" about the rejection of your loan request that you submitted at digipokket Pvt. Ltd.\n Unfortunately,"
					+ "You were failed the criteria of credit score that is must to aaply for the loan"
					+ "your cibil score : "+e.getCibilScore()+"  \n"+"your cibil status : "+e.getEnquiryStatus());
			
			jms.send(sm);
		
		equiryRepo.save(e);
		
		
		
		return equiryRepo.findAll();
	}

	@Override
	public List<Enquiry> viewAllEnquiry(String status1, String status2,String status3) {
		return equiryRepo.findAllByEnquiryStatusOrEnquiryStatusOrEnquiryStatus(status1,status2,status3);
	}

	@Override
	public void forwardToOE(int eid) {

		Optional<Enquiry> oe=equiryRepo.findById(eid);
		if(oe.isPresent())
		{
			Enquiry enquiry=oe.get();
			enquiry.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIl_REQUIRED));
			equiryRepo.save(enquiry);
		}
	}

	@Override
	public Integer checkCIBIL(int eid) {
		
		Optional<Enquiry> oe=equiryRepo.findById(eid);
		
			Enquiry enquiry=oe.get();
			
		Integer cibilScore=rst.getForObject("http://localhost:8080/cibilCalculator/getCibil/"+enquiry.getPanNo(),Integer.class);
		enquiry.setCibilScore(cibilScore);
		if(cibilScore>=750)
		{
			enquiry.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_OK));
			equiryRepo.save(enquiry);
		}
		else if(cibilScore<750)
		{
			enquiry.setEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_REJECT));
			equiryRepo.save(enquiry);
		}
		
		return cibilScore;
	}

	@Override
	public List<Enquiry> getCIBIL() {
		
		return equiryRepo.findAllByEnquiryStatusOrEnquiryStatus(String.valueOf(EnquiryStatus.CIBIL_OK),String.valueOf(EnquiryStatus.CIBIL_REJECT));
	}

	@Override
	public void sendSuccessMail(int eid) {
		
		Optional<Enquiry> oe=equiryRepo.findById(eid);
		Enquiry e=oe.get();
		SimpleMailMessage sm=new SimpleMailMessage();
		
		sm.setFrom(fromEmail);
		sm.setTo(e.getEmailId());
		sm.setSubject("LETTER FOR SUBMITING ENQUIRY OF LOAN");
		sm.setText("Dear recipient M/s"+e.getFirstName()+" "+e.getLastName()+"\n \n    With regars to your letter enquiring about applying for a loan, could you visit to bank site and discuss the matter with our Relationship Executive during banking hours..");
				
		
		jms.send(sm);
	}
}
