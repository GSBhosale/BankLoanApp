package in.bitlogic.digipokket.loan.app.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;

public interface LoanDisbursementService {

    public	LoanDisbursement loanAmountDisbursement(LoanDisbursement ld,Integer customerId);

	public ByteArrayInputStream createDisbursementPdf(int customerId);

	public List<Customer> getAllSanction(String string);

}
