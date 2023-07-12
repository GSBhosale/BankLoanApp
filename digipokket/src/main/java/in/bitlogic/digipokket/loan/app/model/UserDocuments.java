package in.bitlogic.digipokket.loan.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDocuments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userDocsId;
	@Lob
	private byte[] photo;
	@Lob
	private byte[] signature;
	
}
