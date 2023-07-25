package in.bitlogic.digipokket.loan.app.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.EMI;
import in.bitlogic.digipokket.loan.app.model.Ledger;
import in.bitlogic.digipokket.loan.app.service.LedgerService;

@CrossOrigin
@RestController
@RequestMapping("/ledger")
public class LedgerController {
	
	@Autowired
	LedgerService ls;
	
	@GetMapping("/createEMI/{customerId}")
	public List<EMI> createEMI(@PathVariable("customerId") int cid)
	{
		 List<EMI> emis=ls.createEMI(cid);
		 return emis;
		 
	}
	

	
}
