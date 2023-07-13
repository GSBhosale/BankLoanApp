package in.bitlogic.digipokket.loan.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;
import in.bitlogic.digipokket.loan.app.service.CustomerService;
@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value="/createCustomer",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestParam (value="passportPhoto") MultipartFile file1,@RequestParam (value="signature") MultipartFile file2,
			@RequestParam (value="thumb") MultipartFile file3,@RequestParam (value="adharCard") MultipartFile file4,@RequestParam (value="panCard") MultipartFile file5,
			@RequestParam (value="addressProof") MultipartFile file6,@RequestParam (value="incomeProof") MultipartFile file7,@RequestParam (value="bankPassbook") MultipartFile file8,
			@RequestParam (value="bankCheque") MultipartFile file9,@RequestParam("data") String json) throws IOException, JsonProcessingException
	{
		
		ObjectMapper om=new ObjectMapper();
		Customer customer=om.readValue(json, Customer.class);
		customer.getAllPersonalDocuments().setPassportPhoto(file1.getBytes());
		customer.getAllPersonalDocuments().setSignature(file2.getBytes());
		customer.getAllPersonalDocuments().setThumb(file3.getBytes());
		customer.getAllPersonalDocuments().setAdharCard(file4.getBytes());
		customer.getAllPersonalDocuments().setPanCard(file5.getBytes());
		customer.getAllPersonalDocuments().setAddressProof(file6.getBytes());
		customer.getAllPersonalDocuments().setIncomeProof(file7.getBytes());
		customer.getAllPersonalDocuments().setBankPassbook(file8.getBytes());
		customer.getAllPersonalDocuments().setBankCheque(file9.getBytes());
		Customer cust=customerService.createCustomer(customer);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
				
	}

}
