package in.bitlogic.digipokket.loan.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SanctionLetter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sanctionId;
	private Date sanctionDate;
	private String applicantName;
	private Long contactDetails;
	private Double loanAmountSanctioned;
	private Double rateOfInterest;
	private Integer loanTenure;
	private Double monthlyEmiAmount;
	private String termsCondition;
	private Double totalLoanAmountWithInterest;
	// @Enumerated
	private String sactionStatus;

	@Column(length = 1000000)
	@Lob
	private byte[] sactionLetter;

}