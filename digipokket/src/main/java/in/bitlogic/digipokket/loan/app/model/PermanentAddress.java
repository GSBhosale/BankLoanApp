package in.bitlogic.digipokket.loan.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PermanentAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flatNo;
	private String landmark;
	private String areaName;
	private String cityName;
	private Integer pincode;

}
