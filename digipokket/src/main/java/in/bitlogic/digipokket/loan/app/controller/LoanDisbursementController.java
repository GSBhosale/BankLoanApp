package in.bitlogic.digipokket.loan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;
import in.bitlogic.digipokket.loan.app.service.LoanDisbursementService;

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
}
