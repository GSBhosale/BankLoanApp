package in.bitlogic.digipokket.loan.app.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;
import in.bitlogic.digipokket.loan.app.service.LoanDisbursementService;
import in.bitlogic.digipokket.loan.enums.ApplicationStatus;

@RestController
@RequestMapping("disbursement")
public class LoanDisbursementController {
	
		@Autowired
		LoanDisbursementService loanDisbursementService;

	@PostMapping("/createLoanDisbursement/{customerId}")
	public ResponseEntity<LoanDisbursement> loanAmountDisbursement(@RequestBody LoanDisbursement ld,@PathVariable Integer customerId)
	{
		LoanDisbursement loanDisbursement=loanDisbursementService.loanAmountDisbursement(ld,customerId);
		return new ResponseEntity<>(loanDisbursement,HttpStatus.CREATED);
				
	}
	
	@GetMapping("/mailPdf/{customerId}")
	public ResponseEntity<InputStreamResource> getDisbursementPdf(@PathVariable int customerId)
	{
		
		    ByteArrayInputStream pdfArray=	loanDisbursementService.createDisbursementPdf(customerId);
		    HttpHeaders headers=new HttpHeaders();
		    headers.add("Content-Disposition","inline;filename=mypdf.pdf");
			return ResponseEntity.ok()
					              .headers(headers)
					              .contentType(MediaType.APPLICATION_PDF)
					              .body(new InputStreamResource(pdfArray));
	}
	
	@GetMapping("/loanDisbursement")
	public ResponseEntity<List<Customer>> getAllSanction()
	{
		List<Customer> customers=loanDisbursementService.getAllSanction(String.valueOf(ApplicationStatus.SANCTIONED));
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
}
