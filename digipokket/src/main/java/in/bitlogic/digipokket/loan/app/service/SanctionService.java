package in.bitlogic.digipokket.loan.app.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;

public interface SanctionService 
{

	

	public Customer sanctionLoan(SanctionLetter sanLetter, int customerId);

	public List<Customer> getSanction(String valueOf);

	//public ByteArrayInputStream createPdf(int customerId);

	


	



}
