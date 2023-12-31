package in.bitlogic.digipokket.loan.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String firstName;
	private String middleName;
	private String designation;
	private String lastName;
	private Long mobileNo;
	private String dateOfBirth;
	private String gender;
	private Integer age;
	private String emailId;
	private String education;
	private String panNo;
	private String username;
	private String password;
	private String occupation;
	private String applicationStatus;
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;                 //at start
	@OneToOne(cascade = CascadeType.ALL)
	private AllPersonalDocuments userDocs =new AllPersonalDocuments();   //at start
	@OneToOne(cascade = CascadeType.ALL)
	private AccountsDetails accountDetails=new AccountsDetails() ;     //at start
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;                //at start
	@OneToOne(cascade = CascadeType.ALL)
	private Ledger ledger;                
	@OneToOne(cascade = CascadeType.ALL)
	private SanctionLetter sanctionLetter=new SanctionLetter();
	@OneToOne(cascade = CascadeType.ALL)
	private LoanDisbursement loanDisbursement=new LoanDisbursement();
	
}
