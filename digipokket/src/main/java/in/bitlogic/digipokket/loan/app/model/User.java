package in.bitlogic.digipokket.loan.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String firstName;
	private String lastName;
	private String designation;
	private Long mobileNumber;
	private String emailId;
	private String username;  ///auto
	private String password;    //auto
	private Double salary;
	@OneToOne(cascade = CascadeType.ALL)
	private UserDocuments userDocs=new UserDocuments();

}
