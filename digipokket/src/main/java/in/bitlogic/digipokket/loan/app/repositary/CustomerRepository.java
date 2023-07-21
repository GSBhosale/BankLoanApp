package in.bitlogic.digipokket.loan.app.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bitlogic.digipokket.loan.app.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public List<Customer> findAllByApplicationStatus(String string);
}
