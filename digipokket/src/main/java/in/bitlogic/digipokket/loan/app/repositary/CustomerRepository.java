package in.bitlogic.digipokket.loan.app.repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bitlogic.digipokket.loan.app.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	

	public List<Customer> findAllByApplicationStatus(String valueOf);

	public Customer findByUsernameAndPassword(String uname, String pass);

	public Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);



}
