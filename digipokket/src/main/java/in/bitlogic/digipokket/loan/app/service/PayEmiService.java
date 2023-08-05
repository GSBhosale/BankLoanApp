package in.bitlogic.digipokket.loan.app.service;

import in.bitlogic.digipokket.loan.app.model.Customer;

public interface PayEmiService {

	Customer payEmi(int emiId, int customerId);

	Customer getAllEmi(int customerId);

}
