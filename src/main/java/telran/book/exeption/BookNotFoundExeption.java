package telran.book.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotFoundExeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundExeption(long isbn) {
		super("Book with isbn = " + isbn+" not found");
		// TODO Auto-generated constructor stub
	}



}
