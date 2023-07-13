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
	private byte[] addressProof;
	@Lob
	private byte[] panCard;
	@Lob
	private byte[] adharCard;
	@Lob
	private byte[] incomeProof;
	@Lob
	private byte[] bankPassbook;
	@Lob
	private byte[] passportPhoto;
	@Lob
	private byte[] signature;
	@Lob
	private byte[] thumb;
	@Lob
	private byte[] bankCheque;
}
