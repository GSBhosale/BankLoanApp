package in.bitlogic.digipokket.loan.app.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.EMI;
import in.bitlogic.digipokket.loan.app.model.Ledger;
import in.bitlogic.digipokket.loan.app.model.SanctionLetter;
import in.bitlogic.digipokket.loan.app.repositary.CustomerRepository;
import in.bitlogic.digipokket.loan.app.repositary.LedgerRepositary;
import in.bitlogic.digipokket.loan.app.service.LedgerService;
import in.bitlogic.digipokket.loan.enums.EMIStatus;
@Service
public class LedgerServiceImpl implements LedgerService{

	@Autowired
    LedgerRepositary lr;
	
	@Autowired
     CustomerRepository cr;

	@Override
	public List<EMI> createEMI(int cid) {

		Optional<Customer>cust=cr.findById(cid);
		Customer c=cust.get();
		c.getSanctionLetter().setTotalLoanAmountWithInterest(c.getSanctionLetter().getTotalLoanAmountWithInterest()-c.getSanctionLetter().getProcessingFees());
		for(int i=1;i<=(c.getSanctionLetter().getLoanTenure()*12);i++)
		{
			EMI emi=new EMI();
			emi.setEmiStatus(String.valueOf(EMIStatus.UNPAID));
			emi.setRemainingAmount(c.getSanctionLetter().getTotalLoanAmountWithInterest()-c.getSanctionLetter().getMonthlyEmiAmount());
			c.getSanctionLetter().setTotalLoanAmountWithInterest(emi.getRemainingAmount());
			c.getLedger().getEmis().add(emi);
		}
		
		cr.save(c);
		return c.getLedger().getEmis();
	}

	

	
}	