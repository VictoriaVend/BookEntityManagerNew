package telran.book.dao;

import java.util.List;
import java.util.Optional;



import telran.book.model.Publisher;

public interface PublisherRepository {

	List<String> findPublishersByAuthorsName(String authorName);
	Optional<Publisher> findById(String publisherName);
	//List<Publisher> findByBooksAuthorsName(String name);

	Publisher save(Publisher publisher);


}
