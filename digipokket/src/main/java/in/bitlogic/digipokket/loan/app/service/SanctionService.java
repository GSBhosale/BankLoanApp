package in.bitlogic.digipokket.loan.app.service;

import java.io.ByteArrayInputStream;

import in.bitlogic.digipokket.loan.app.model.SanctionLetter;

public interface SanctionService 
{

	public ByteArrayInputStream createPdf(int customerId);

	public SanctionLetter sanctionLoan(SanctionLetter sanLetter, int customerId);

}
