package in.bitlogic.digipokket.loan.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoanDisbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer agreementId;
	private Long loanNo;
	private Double totalLoanAmount;
	//@OneToOne(cascade = CascadeType.ALL)
//	private DealerBankDetails dealerBankDetails;
	//private Double transferAmount;
	// @Enumerated
    private Double disburseAmount;
    private Double remainingDisbursement;
    private String paymentStatus;
    private Double transferAmount;
	 @CreationTimestamp
    private Date amountPaidDate;
	 @Column(length = 1000000)
		@Lob
		private byte[] loanDisbursementLetter;

	
}


