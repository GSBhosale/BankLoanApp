package in.bitlogic.digipokket.loan.app.service;

import java.util.List;
import java.util.Set;

import in.bitlogic.digipokket.loan.app.model.Ledger;

public interface LedgerService {

	public Ledger addLedger(Ledger l);

	public List<Ledger> getLedger();

	public Set<Ledger> createLedger(int customerId);



}
