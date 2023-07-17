package in.bitlogic.digipokket.loan.app.service;

import java.util.List;

import in.bitlogic.digipokket.loan.app.model.Enquiry;

public interface EnquiryService {

	public Enquiry makeEnquiry(Enquiry e);

	public List<Enquiry> viewAllEnquiry();

	public List<Enquiry> rejectEnquiry(int eid);

      public List<Enquiry> viewAllEnquiry(String status1, String status2,String status3);

	public void forwardToOE(int eid);

}
