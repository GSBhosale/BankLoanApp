package in.bitlogic.digipokket.loan.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer enquiryId;
	private String firstName;
	private String middleName;
	private String lastName;
	@CreationTimestamp
	private Date enquiryDate;
	private Integer age;
	private Long mobileNo;
	private String panNo;
	private String city;
	private String dateOfBirth;
	private String emailId;
	private String gender;
	private Integer cibilScore;
	private String enquiryStatus;  //-----------enums
	// maintain enquiryStatus
	
}
