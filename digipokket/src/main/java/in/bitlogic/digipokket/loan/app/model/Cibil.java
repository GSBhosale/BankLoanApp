package in.bitlogic.digipokket.loan.app.model;

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
public class Cibil {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer cibilId;
private int cibilScore;
private String cibilStatus;
}
