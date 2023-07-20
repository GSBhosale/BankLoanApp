package in.bitlogic.digipokket.loan.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiCalculator {
	
	private Double requriedAmount;
	private Double rateOIintreast;
	private Integer tenour;
	private Double emi;
	private Double totalAmount;
	private Double totalIntreast;
}
