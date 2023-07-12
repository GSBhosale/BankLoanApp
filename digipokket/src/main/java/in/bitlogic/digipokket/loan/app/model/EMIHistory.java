package in.bitlogic.digipokket.loan.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EMIHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer emiId;
	private  Date emiDate;
	private Double emiAmount;
	private Double remainingAmount;
	
}
