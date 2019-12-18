package telran.book.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "publisherName" })
@Entity
public class Publisher implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	String publisherName;

	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}
}
