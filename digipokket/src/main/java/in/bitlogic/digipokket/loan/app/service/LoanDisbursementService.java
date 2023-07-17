package in.bitlogic.digipokket.loan.app.service;

import java.io.ByteArrayInputStream;

import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;

public interface LoanDisbursementService {

    public	LoanDisbursement loanAmountDisbursement(LoanDisbursement ld,Integer customerId);

	public ByteArrayInputStream createDisbursementPdf(int customerId);

}
