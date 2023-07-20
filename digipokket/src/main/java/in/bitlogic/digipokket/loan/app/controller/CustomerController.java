package in.bitlogic.digipokket.loan.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.bitlogic.digipokket.loan.app.model.AllPersonalDocuments;
import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;
import in.bitlogic.digipokket.loan.app.model.User;
import in.bitlogic.digipokket.loan.app.service.CustomerService;
import in.bitlogic.digipokket.loan.enums.EnquiryStatus;
@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value="/createCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestParam (value="passportPhoto") MultipartFile file1,@RequestParam (value="signature") MultipartFile file2,
			@RequestParam (value="thumb") MultipartFile file3,@RequestParam (value="adharCard") MultipartFile file4,@RequestParam (value="panCard") MultipartFile file5,
			@RequestParam (value="addressProof") MultipartFile file6,@RequestParam (value="incomeProof") MultipartFile file7,@RequestParam (value="bankPassbook") MultipartFile file8,
			@RequestParam (value="bankCheque") MultipartFile file9,@RequestParam("data") String json) throws IOException
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
	
	@GetMapping("/areaWiseCustomers/{cityName}")
	public ResponseEntity<List<Customer>> areaWiseUsers(@PathVariable ("cityName") String city)
	{
		
		List<Customer> lc=customerService.areaWiseUsers(city);
		return new ResponseEntity<List<Customer>>(lc,HttpStatus.OK);
		
	}
	
	@GetMapping("/verifyDocuments")
	public ResponseEntity<List<Customer>> verifyDocuments()
	{
	List<Customer> listc=customerService.verifyDocuments();
		return new ResponseEntity<List<Customer>>(listc,HttpStatus.OK);
	}
	
	@GetMapping("/verifyDocs/{customerId}")
	public ResponseEntity<Customer> verifyDocs(@PathVariable int customerId)
	{
		System.out.println("gggg");
		 Customer cust= customerService.verifyDocs(customerId);
		
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	@PutMapping("/completeUplodDocs/{customerId}")
	public ResponseEntity<Customer> completeUplodDocs(@PathVariable int customerId)
	{
		 Customer cust= customerService.completeUplodDocs(customerId);
	
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}

}
