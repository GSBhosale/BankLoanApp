package in.bitlogic.digipokket.loan.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ledger {
	
	@Id
	private String ledgerId;
	@CreationTimestamp
	private Date ledgerCreatedDate;
	private Double totalLoanAmount;
	private Double payableAmountwithInterest;
	private Integer loanTenureInYears;
	private Double monthlyEMI;	
	private Double amountPaidTillDate;
	private Double remainingAmount;
	private String nextEmiDateStart;	
	private String nextEmiDateEnd;	
	private Integer defaulterCount; // no of emi bounce
	@CreationTimestamp
	private Date currentEmiPaidDate;
	private String currentEmiPaidMode;
	private String previousEmiStatus; // paid unpaid
	private String currentMonthEmiStatus;
	private String loanEmiStartDate;
	private String loanEmiEndDate;
	private Integer noOfEmisPaid;
	private Integer totalNoOfEmi;
	private Integer remainingEmi;

}
