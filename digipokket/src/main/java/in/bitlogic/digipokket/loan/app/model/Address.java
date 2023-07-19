package in.bitlogic.digipokket.loan.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	@OneToOne(cascade = CascadeType.ALL)
	private LocalAddress localAddress=new LocalAddress() ;
	@OneToOne(cascade = CascadeType.ALL)
	private PermanentAddress permanentAddress=new PermanentAddress();
	
}
