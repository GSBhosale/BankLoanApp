package in.bitlogic.digipokket.loan.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountsDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accId;
	private String accountType;
	private String accoubntHoldersName;
	private Long accountNumbber;
	private Double requiredLoanAmount;
	private Integer tenureInYear;
	@ManyToOne(cascade = CascadeType.ALL)
	private BankDetails bankdetails;
}
