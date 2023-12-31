package in.bitlogic.digipokket.loan.app.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bitlogic.digipokket.loan.app.model.SanctionLetter;

@Repository
public interface SanctionRepository extends JpaRepository<SanctionLetter, Integer>
{

}
