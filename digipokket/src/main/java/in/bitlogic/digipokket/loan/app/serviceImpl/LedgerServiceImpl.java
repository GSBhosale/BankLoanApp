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
	public Ledger addLedger(Ledger l) {
		return lr.save(l);
	}

	@Override
	public List<Ledger> getLedger() {
		return lr.findAll();
	}

	@Override
	public Set<Ledger> createLedger(int customerId) {
             Optional<Customer> optionalCustomer = cr.findById(customerId);
             if(optionalCustomer.isPresent())
             {
            	 Customer customer=optionalCustomer.get();
            	 SanctionLetter sanction=customer.getSanctionLetter();
            	 
            	 for(int i=0;i<sanction.getLoanTenure()*12;i++)
            	 {
            		 if( customer.getLedger().size()<=0)
            		 {
            			Ledger firstEmi=new Ledger();
            			firstEmi.setLedgerId(String.valueOf(customer.getCustomerId())+"EmiNo-"+i);
            	
            			firstEmi.setTotalLoanAmount(sanction.getLoanAmountSanctioned());
            			firstEmi.setPayableAmountwithInterest(sanction.getTotalLoanAmountWithInterest());
            			firstEmi.setLoanTenureInYears(sanction.getLoanTenure());
            			firstEmi.setMonthlyEMI(sanction.getMonthlyEmiAmount());
        
            			firstEmi.setRemainingAmount(sanction.getTotalLoanAmountWithInterest());
            			
            			Date todayDate=new Date();
            		int month=	todayDate.getMonth();
            			int year =todayDate.getYear();
            			if(month<12) {
            		    firstEmi.setNextEmiDateStart("01/"+month+1+"/"+year);
            		    firstEmi.setNextEmiDateEnd("30/"+month+1+"/"+year);
            			}else {
            				  firstEmi.setNextEmiDateStart("01/"+1+"/"+year+1);
                  		    firstEmi.setNextEmiDateEnd("30/"+1+"/"+year+1);
            			}
            			firstEmi.setDefaulterCount(0);
            			DateFormat format=new SimpleDateFormat("dd-MM-YYYY");
            			     
            			firstEmi.setLoanEmiStartDate(format.format(todayDate));
            			String startDate=format.format(todayDate);
            			int lenth=startDate.length();
                char ch=			startDate.charAt(lenth-1);
                 Integer lastEmiChar=(ch-'0')+sanction.getLoanTenure();
         			firstEmi.setLoanEmiEndDate(startDate.replace(ch, lastEmiChar.toString().charAt((0))));
         			firstEmi.setNoOfEmisPaid(0);
         			firstEmi.setTotalNoOfEmi(sanction.getLoanTenure()*12);
         			firstEmi.setRemainingEmi(sanction.getLoanTenure()*12);
         			customer.getLedger().add(firstEmi);
         			
         			}
            		 else {
            			 
            			   List<Ledger> ledgerList = customer.getLedger().stream().collect(Collectors.toList());
            			                 Ledger lastEmi = ledgerList.get(i-1);
            			                 
            			                 
            			                 Ledger newEmi=new Ledger();
            			                 newEmi.setLedgerId(String.valueOf(customer.getCustomerId())+"EmiNo-"+i);
            		            	
            			                 newEmi.setTotalLoanAmount(sanction.getLoanAmountSanctioned());
            			                 newEmi.setPayableAmountwithInterest(sanction.getTotalLoanAmountWithInterest());
            			                 newEmi.setLoanTenureInYears(sanction.getLoanTenure());
            			                 newEmi.setMonthlyEMI(sanction.getMonthlyEmiAmount());
            		            	
            			                 newEmi.setRemainingAmount(lastEmi.getRemainingAmount()-sanction.getMonthlyEmiAmount());
            			                 
            			                 
            			                 String lastEmiSartDate=lastEmi.getLoanEmiEndDate();
            			                  DateFormat dateFormat=new SimpleDateFormat("dd-MM-YYYY");
            			                    Date lastDateEnd;
											try {
												System.out.println(lastEmiSartDate);
												lastDateEnd = (Date) dateFormat.parse(lastEmiSartDate);
											     int month=  lastDateEnd.getMonth();
		            			                  int year=lastDateEnd.getYear();
		            			          		if(month<11) {
		            			          			newEmi.setNextEmiDateStart("01/"+month+1+"/"+year);
		            		            		    newEmi.setNextEmiDateEnd("30/"+month+1+"/"+year);
		            		            			}else {
		            		         
		            		            				newEmi.setNextEmiDateStart("01/"+1+"/"+year+1);
		            		            				newEmi.setNextEmiDateEnd("30/"+1+"/"+year+1);
		            		            			}
		            			                 
											} catch (ParseException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
            			             

            			          		newEmi.setDefaulterCount(0);
            		            			DateFormat format=new SimpleDateFormat("dd-MM-YYYY");
            		            			     
            		            			newEmi.setLoanEmiStartDate(lastEmi.getLoanEmiStartDate());
            		     
            		            			newEmi.setLoanEmiEndDate(lastEmi.getLoanEmiEndDate());
            		            			newEmi.setNoOfEmisPaid(0);
            		            			newEmi.setTotalNoOfEmi(sanction.getLoanTenure()*12);
            		            			newEmi.setRemainingEmi(sanction.getLoanTenure()*12);
            		         			customer.getLedger().add(newEmi);
            		         		
            			                 
            			 }
            		 
            	 }
            	 System.out.println(customer.getLedger());
            	 
            	 return customer.getLedger() ;
             }
               
            return null;
	}

	
}
