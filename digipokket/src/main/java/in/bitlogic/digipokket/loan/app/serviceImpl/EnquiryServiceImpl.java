package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.repositary.EnquiryRepositary;
import in.bitlogic.digipokket.loan.app.service.EnquiryService;
@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	EnquiryRepositary equiryRepo;
	
	@Autowired
	JavaMailSender jms;

	@Value(value="${spring.mail.username}")
	String fromEmail;

	@Override
	public Enquiry makeEnquiry(Enquiry e) {
		
		if(e.getCibilScore()>300 && e.getCibilScore()<=550)
		{
			e.setCibilStatus("POOR");
		}
		else if(e.getCibilScore()>550 && e.getCibilScore()<=650)
		{
			e.setCibilStatus("AVERAGE");
		}
		else if(e.getCibilScore()>650 && e.getCibilScore()<=750)
		{
			e.setCibilStatus("GOOD");
		}
		else if(e.getCibilScore()>750 && e.getCibilScore()<=900)
		{
			e.setCibilStatus("EXCELLENT");
		}
		
		return equiryRepo.save(e);
	}

	@Override
	public List<Enquiry> viewAllEnquiry() {
		return equiryRepo.findAll();
	}

	@Override
	public List<Enquiry> rejectEnquiry(int eid) {
		
		
		Enquiry e=equiryRepo.getById(eid);
			
			SimpleMailMessage sm=new SimpleMailMessage();
			
			sm.setFrom(fromEmail);
			sm.setTo(e.getEmailId());
			sm.setSubject("LETTER FOR REJECT OF LOAN");
			sm.setText("This letter is to notify M/s"+e.getFirstName()+" "+e.getLastName()+" about the rejection of your loan request that you submitted at digipokket Pvt. Ltd.\n Unfortunately,"
					+ "You were failed the criteria of credit score that is must to aaply for the loan"
					+ "your cibil score : "+e.getCibilScore()+"  \n"+"your cibil status : "+e.getCibilStatus());
			
			jms.send(sm);
		
		equiryRepo.deleteById(eid);
		
		
		
		return equiryRepo.findAll();
	}
}
