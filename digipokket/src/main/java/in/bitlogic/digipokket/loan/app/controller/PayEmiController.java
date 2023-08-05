package in.bitlogic.digipokket.loan.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.EMI;
import in.bitlogic.digipokket.loan.app.service.PayEmiService;

@CrossOrigin("*")
@RestController
@RequestMapping("/emi")
public class PayEmiController {
	
	@Autowired
	PayEmiService emiService;
	
	@GetMapping("/payEmi/{emiId}/{customerId}")
	public ResponseEntity<Customer> payEmi(@PathVariable("emiId") int emiId,@PathVariable("customerId") int customerId)
	{
		Customer customer=emiService.payEmi(emiId,customerId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllEmi/{customerId}")
	public ResponseEntity<Customer> getAllEmi(@PathVariable("customerId") int customerId)
	{
		Customer customer=emiService.getAllEmi(customerId);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}

}
