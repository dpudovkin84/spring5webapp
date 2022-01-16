package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher eskmo=new Publisher("Eskmo","Moskow,Kremlin");
//        Publisher redStar=new Publisher("Red Star","Lytkarino, Kolhoznay 10");

        eskmo=publisherRepository.save(eskmo);

        Author eric=new Author("Eric","Evans");
        Book ddd=new Book("Domain driven design","125334");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(eskmo);
        eskmo.getBooks().add(ddd);



        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author rod=new Author("Rod","Johnson");
        Book noEJB=new Book("J2EE Development without EJB",
                "5329625");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(eskmo);
        eskmo.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(eskmo);

//        redStar=publisherRepository.save(redStar);


        System.out.println(eskmo);
//        System.out.println(redStar);

        System.out.println("Started in BootStrap");
        System.out.println("Number of books:"+bookRepository.count());
        System.out.println("Number of publishers: "+publisherRepository.count());
        System.out.println("Number of publisher books:"+eskmo.getBooks().size());

    }
}
