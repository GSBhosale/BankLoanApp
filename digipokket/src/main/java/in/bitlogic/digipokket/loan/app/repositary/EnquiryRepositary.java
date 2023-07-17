package in.bitlogic.digipokket.loan.app.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bitlogic.digipokket.loan.app.model.Enquiry;

@Repository
public interface EnquiryRepositary extends JpaRepository<Enquiry, Integer> {


	public List<Enquiry> findAllByEnquiryStatusOrEnquiryStatusOrEnquiryStatus(String string, String string2,
			String string3);

	

}
