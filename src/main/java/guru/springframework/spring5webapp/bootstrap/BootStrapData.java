package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started in Bootstrap");
		
		Publisher publisher = new Publisher("VP", "Test Street 1", "Hullu-Bullu-City", "Germany", "675544");
		publisherRepository.save(publisher);
		
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain driven design", "123321");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(publisher);
		
		Book wow = new Book("World of Warcraft", "234243");
		eric.getBooks().add(wow);
		wow.getAuthors().add(eric);
		wow.setPublisher(publisher);
		publisher.getBooks().add(wow);
		
		authorRepository.save(eric);
		bookRepository.save(wow);
		publisherRepository.save(publisher);
		
		Author road = new Author("Road", "Johnson");
		Book noEJB = new Book("J2EE", "23423424");
		road.getBooks().add(ddd);
		noEJB.getAuthors().add(road);
		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);
		
		authorRepository.save(road);
		bookRepository.save(noEJB);
		publisherRepository.save(publisher);
		
		System.out.println("Number of books: " + bookRepository.count());
		System.out.println("Number of autors: " + authorRepository.count());
		System.out.println("Number of publishers: " + publisherRepository.count());
		System.out.println("Number of publishers books: " + publisher.getBooks().size());
		
		bookRepository.findAll().forEach(book -> System.out.println(book.toString()));
	}

}
