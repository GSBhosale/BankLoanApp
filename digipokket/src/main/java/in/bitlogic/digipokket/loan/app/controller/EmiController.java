package in.bitlogic.digipokket.loan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.bitlogic.digipokket.loan.app.model.EmiCalculator;

@CrossOrigin("*")
@RestController
@RequestMapping("/emiCalculator")
public class EmiController {
	
	
	
	@PostMapping("/emical")
	public EmiCalculator emical(@RequestBody EmiCalculator e)
	{
		//e.setRequriedAmount(am);
		double r=e.getRateOIintreast();
		
		//e.setRateOIintreast(r);
		int n=e.getTenour();
		
		//e.setTenour(n);
		
		double am=e.getRequriedAmount();
		r=r/(12*100);
		n=n*12;
		 double E=am*r*(Math.pow(1+r,n)/(Math.pow(1+r,n)-1));
		 e.setEmi(E);
		 double totalAm=E*n;
		 e.setTotalAmount(totalAm);
		 double totalInterest=totalAm-am;
		 e.setTotalIntreast(totalInterest);
		return e;
		
	}

}
