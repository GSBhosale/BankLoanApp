package in.bitlogic.digipokket.loan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.service.EnquiryService;
import in.bitlogic.digipokket.loan.enums.EnquiryStatus;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

	@Autowired
	EnquiryService enquiryService;
	
	@PostMapping("/makeEnquiry")
	public ResponseEntity<Enquiry> makeEnquiry(@RequestBody Enquiry e)
	{
		Enquiry enquiry=enquiryService.makeEnquiry(e);
		
		return new ResponseEntity<Enquiry>(enquiry,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAllEnquiry")
	public ResponseEntity<List<Enquiry>> viewAllEnquiry()
	{
		List<Enquiry> listOfEnquiry=enquiryService.viewAllEnquiry();
		
		return new ResponseEntity<List<Enquiry>>(listOfEnquiry,HttpStatus.OK);
	}
	
	@GetMapping("/sendRejectMail/{enquiryId}")
	public ResponseEntity<List<Enquiry>> rejectEnquiry(@PathVariable("enquiryId") int eid)
	{
		List<Enquiry> listOfEnquiry=enquiryService.rejectEnquiry(eid);
		
		return new ResponseEntity<List<Enquiry>>(listOfEnquiry,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllEnquiryToOE")
	public ResponseEntity<List<Enquiry>> viewAllEnquiryToOE()
	{
		List<Enquiry> listOfEnquiry=enquiryService.viewAllEnquiry(String.valueOf(EnquiryStatus.CIBIl_REQUIRED),String.valueOf(EnquiryStatus.CIBIL_REJECT),String.valueOf(EnquiryStatus.CIBIL_OK));
		
		return new ResponseEntity<List<Enquiry>>(listOfEnquiry,HttpStatus.OK);
	}
	
	@GetMapping("/forwardToOE/{enquiryId}")
	public ResponseEntity<List<Enquiry>> forwardToOE(@PathVariable("enquiryId") int eid)
	{
		enquiryService.forwardToOE(eid);
		return null;
	}
	
	@GetMapping("/checkCIBIL/{enquiryId}")
	public ResponseEntity<Integer> checkCIBIL(@PathVariable("enquiryId") int eid)
	{
		Integer cibilScore=enquiryService.checkCIBIL(eid);
		
		return new ResponseEntity<Integer>(cibilScore,HttpStatus.OK);
	}
	
	@GetMapping("/getCIBIL")
	public  ResponseEntity<List<Enquiry>> getCIBIL()
	{
		List<Enquiry> le=enquiryService.getCIBIL();
		
		return new ResponseEntity<List<Enquiry>>(le,HttpStatus.OK);
	}
	

	@GetMapping("sendSuccessMail/{enquiryId}")
	public ResponseEntity<Object> sendSuccessMail(@PathVariable("enquiryId") int eid)
	{
		enquiryService.sendSuccessMail(eid);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@GetMapping("apply/{enquiryId}")
	public ResponseEntity<Object> apply(@PathVariable("enquiryId") int eid)
	{
        enquiryService.apply(eid);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
