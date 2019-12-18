package telran.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;
import telran.book.servise.BookServise;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookServise bookServise;

	@PostMapping
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookServise.addBook(bookDto);
	}

	@GetMapping("/{isbn}")
	public BookDto findBookByIsbn(@PathVariable long isbn) {
		return bookServise.findBookByIsbn(isbn);
	}
	@DeleteMapping("/{isbn}")
	public BookDto removeBook(@PathVariable long isbn) {
		return bookServise.removeBook(isbn);
	}
	@DeleteMapping("/author/{authorName}")
	public AuthorDto removeAuthor(@PathVariable String authorName) {
		return bookServise.removeAuthor(authorName);
	}
	@PutMapping("/{isbn}/title/{title}")
	public BookDto updateBook(@PathVariable long isbn,@PathVariable String title) {
		return bookServise.updateBook(isbn, title);
	}
	@GetMapping("/books/author/{authorName}")
	public Iterable<BookDto> findBooksByAuthor(@PathVariable String authorName){
		return bookServise.findBooksByAuthor(authorName);
	}
	
	@GetMapping("/books/publisher/{publisher}")
	public Iterable<BookDto>findBooksByPublisher(@PathVariable String publisher){
		return bookServise.findBooksByPublisher(publisher);
	}
	
	@GetMapping("/autors/book/{isbn}")
	public Iterable<AuthorDto> findAuthorsByBooks(@PathVariable Long isbn){
		return bookServise.findBookAuthor(isbn);
	}
	@GetMapping("/publisher/author/{author}")
	public Iterable<String> findPublisherByAuthor(@PathVariable String author){
		return bookServise.findPublishersByAuthor(author);
	}
	
}
