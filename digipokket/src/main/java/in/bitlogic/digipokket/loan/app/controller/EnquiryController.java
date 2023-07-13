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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.Enquiry;
import in.bitlogic.digipokket.loan.app.service.EnquiryService;


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
	
	@DeleteMapping("/rejectEnquiry/{enquiryId}")
	public ResponseEntity<List<Enquiry>> rejectEnquiry(@PathVariable("enquiryId") int eid)
	{
		List<Enquiry> listOfEnquiry=enquiryService.rejectEnquiry(eid);
		
		return new ResponseEntity<List<Enquiry>>(listOfEnquiry,HttpStatus.OK);
	}
}
