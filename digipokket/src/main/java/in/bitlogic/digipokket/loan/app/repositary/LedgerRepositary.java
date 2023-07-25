package in.bitlogic.digipokket.loan.app.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bitlogic.digipokket.loan.app.model.Ledger;
@Repository
public interface LedgerRepositary extends JpaRepository<Ledger, Integer>{

}
