package in.bitlogic.digipokket.loan.app.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;
import in.bitlogic.digipokket.loan.app.service.SanctionService;
import in.bitlogic.digipokket.loan.enums.ApplicationStatus;

@RestController
@RequestMapping("/sanction")
@CrossOrigin("*")
public class SanctionController 
{
	@Autowired
	SanctionService sanctionService;
	
	@PostMapping("insertData/{customerId}")
	public ResponseEntity<SanctionLetter> sanctionLoan(@RequestBody SanctionLetter sanLetter,@PathVariable int customerId)
	{
		        
		SanctionLetter sanctionLetter=sanctionService.sanctionLoan(sanLetter,customerId);
		
		return new ResponseEntity<SanctionLetter>(sanctionLetter,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/mailPdf/{customerId}")
	public ResponseEntity<InputStreamResource> getPdfDocument(@PathVariable int customerId)
	{
		
		    ByteArrayInputStream pdfArray=	sanctionService.createPdf(customerId);
		    HttpHeaders headers=new HttpHeaders();
		    headers.add("Content-Disposition","inline;filename=mypdf.pdf");
			return ResponseEntity.ok()
					              .headers(headers)
					              .contentType(MediaType.APPLICATION_PDF)
					              .body(new InputStreamResource(pdfArray));
		
	}
	
	@GetMapping("/sanctions")
	public ResponseEntity<List<Customer>> getSanction()
	{
		List<Customer> customer=sanctionService.getSanction(String.valueOf(ApplicationStatus.VERIFIED));
		  return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
	}
}