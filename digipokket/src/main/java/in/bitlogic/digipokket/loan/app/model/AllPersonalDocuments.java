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
public class AllPersonalDocuments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer docsId;
	@Lob
	private Byte[] addressProof;
	@Lob
	private Byte[] panCard;
	@Lob
	private Byte[] adharCard;
	@Lob
	private Byte[] incomeProof;
	@Lob
	private Byte[] bankPassbook;
	@Lob
	private Byte[] passportPhoto;
	@Lob
	private Byte[] signature;
	@Lob
	private Byte[] thumb;
	@Lob
	private Byte[] bankCheque;
}
