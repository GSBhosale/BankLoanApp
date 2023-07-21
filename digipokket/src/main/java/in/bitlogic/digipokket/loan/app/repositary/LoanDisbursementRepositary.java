package in.bitlogic.digipokket.loan.app.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bitlogic.digipokket.loan.app.model.Customer;
import in.bitlogic.digipokket.loan.app.model.LoanDisbursement;

@Repository
public interface LoanDisbursementRepositary extends JpaRepository<LoanDisbursement, Integer>{
	

}
