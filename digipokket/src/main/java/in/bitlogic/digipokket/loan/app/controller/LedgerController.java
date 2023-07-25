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

import in.bitlogic.digipokket.loan.app.model.Ledger;
import in.bitlogic.digipokket.loan.app.service.LedgerService;

@CrossOrigin
@RestController
@RequestMapping("/ledger")
public class LedgerController {
	
	@Autowired
	LedgerService ls;
	

	//Post API for Ledger=> http://localhost:9090/addLedger
		@PostMapping("/addLedger")
	public ResponseEntity<Ledger> saveLedger(@RequestBody Ledger l)
	{
		return new ResponseEntity<Ledger>(ls.addLedger(l),HttpStatus.CREATED);
			
	}
		
		
	//Get API for Ledger => http://localhost:9090/getLedger
			@GetMapping("/getLedger")
			public ResponseEntity<List<Ledger>> getLedger()
			{
				return new ResponseEntity<List<Ledger>>(ls.getLedger(),HttpStatus.OK);
			}
			
	        @GetMapping("/generateLedger/{customerId}")
	        public Set<Ledger> genrateLedger(@PathVariable int customerId)
	        {
	           Set<Ledger> ledgers=	ls.createLedger(customerId);
	        	
	        	
	        	return ledgers;
	        }
	
	
}
