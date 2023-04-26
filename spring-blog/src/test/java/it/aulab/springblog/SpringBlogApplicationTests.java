package it.aulab.springblog;

import java.util.List;

import javax.swing.text.html.parser.Entity;

// import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
// import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
// import org.springframework.data.domain.Sort.Direction;

import it.aulab.springblog.model.Author;
import it.aulab.springblog.model.Comment;
import it.aulab.springblog.model.Post;
// import it.aulab.springblog.repository.CrudAuthorRepository;
import it.aulab.springblog.repository.JpaAuthorRepository;
// import it.aulab.springblog.repository.PagingAndSortingAuthorRepository;
import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SpringBlogApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private JpaAuthorRepository authorRepository;

	// @Autowired
	// private CrudAuthorRepository crudAuthorRepository;

	// @Autowired
	// private PagingAndSortingAuthorRepository pAndSAuthorRepository;


	@BeforeEach
	public void createData() {
		Author a1 = new Author();
		a1.setFirstname("Mirko");
		a1.setLastname("Abbrescia");
		a1.setEmail("mirkoabbrescia@aulab.it");
		
		entityManager.persist(a1);


		Author a2 = new Author();
		a2.setFirstname("Andrea");
		a2.setLastname("Mininni");
		a2.setEmail("andreamininni@aulab.it");
		
		entityManager.persist(a2);



		Post p1 = new Post();
		p1.setAuthor(a1);
		p1.setTitle("il nuovo Pixel 7 Pro");
		p1.setBody("Lorem ipsum");
		p1.setPublishDate("20230411");

		entityManager.persist(p1);


		Post p2 = new Post();
		p2.setAuthor(a2);
		p2.setTitle("il nuovo IPhone 14");
		p2.setBody("Lorem ipsum");
		p2.setPublishDate("20230411");

		entityManager.persist(p2);



		Comment c1 = new Comment() ;
		c1.setEmail("andreamininni@aulab.it");
		c1.setPost(p1) ;
		c1.setBody("Non mi è piace");
		c1.setPublishDate("20230411");
		

		Comment c2 = new Comment() ;
		c2.setEmail("andreamininni@aulab.it");
		c2.setPost(p2);
		c2.setBody("Bellissimo, consigliato");
		c2.setPublishDate ("20230412");

		entityManager.persist(c1);
		entityManager.persist(c2);
	}

	@Test
	void authorCheck() {

		List<Author> authors = entityManager.createQuery("SELECT a FROM Post a", Author.class).getResultList();

		assertThat(authors).hasSize(1);
	}



	@Test
	void postCheck() {

		List<Post> posts = entityManager.createQuery("SELECT p FROM Post p", Post.class).getResultList();

		assertThat(posts).hasSize(2);
	}



	@Test
	void commentCheck() {

		List<Post> posts = entityManager
						   .createQuery("SELECT p FROM Post p", Post.class)
						   .getResultList();

		System.out.println(posts.get(0).getId());


		List<Comment> commentsPost1 = 
		entityManager.createQuery(
						"SELECT c FROM Comment c WHERE c.post.id = ?1", Comment.class)
					.setParameter(1, posts.get(0).getId())
					.getResultList();

		assertThat(commentsPost1).hasSize(1);

		System.out.println(posts.get(1).getId());



		List<Comment> commentsPost2 = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id = ?1", Comment.class)
			.setParameter(1, posts.get(1).getId()).getResultList();

		assertThat(commentsPost2).hasSize(1);


	}



	@Test
	void queryCheck() {

		// List<Comment> comment = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id = :i AND c.email = ?2", Comment.class)
		// 	// .setParameter(1, 1)
		// 	// .setParameter(2, "andreamininni@aulab.it")

		// 	.setParameter("id", 1)
		// 	.setParameter("email", "andreamininni@aulab.it")
		// 	.getResultList();


		// assertThat(comment).hasSize(2);




		List<Comment> comment = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id = ?1 AND c.email = ?2", Comment.class)
			// .setParameter(1, 1)
			// .setParameter(2, "andreamininni@aulab.it")

			.setParameter(1, 1)
			.setParameter(2, "andreamininni@aulab.it")
			.getResultList();


		assertThat(comment).hasSize(0);
	}



	@Test
	void authorRepositoryCheck(){
		assertThat(authorRepository.count()).isEqualTo(1);

		assertThat(authorRepository.findAll()).first().extracting("firstname").isEqualTo("Mirko");
	
		Author a = authorRepository.findAll().get(0);

		assertThat(authorRepository.findById(a.getId()).get())
			.isNotNull()
			.extracting("lastname")
			.isEqualTo("Abbrescia");
	}



	@Test
	void findByLastName(){
		assertThat(authorRepository.findByLastName("Abbrescia")).first().extracting("lastname").isEqualTo("Abbrescia");
	}




	@Test
	void findByFirstNameAndLastName(){
		assertThat(authorRepository.findByFirstNameAndLastName("Andrea", "Abbrescia")).hasSize(0); // per vedere se ce un author che si chiama Andrea Abbrescia
	
		assertThat(authorRepository.findByFirstNameOrLastName("Andrea", "Abbrescia")).hasSize(2); // per vedere se ce un author che si chiama Andrea Abbrescia

	}



	@Test
	void notIgnoreCase(){
		assertThat(authorRepository.findByFirstNameNotIgnoreCase("andrea")).hasSize(1);
		assertThat(authorRepository.findByFirstNameNotIgnoreCase("Andrea")).hasSize(1);


		assertThat(authorRepository.findByFirstNameContains("r")).hasSize(2);


	}



	@Test
	void create(){
		Author a = new Author();
		a.setFirstname("Gianvito");
		a.setLastname("Palmisano");
		a.setEmail("gvpalm@gmail.com");

		authorRepository.save(a);

		assertThat(authorRepository.findAll()).hasSize(3);


	}



	@Test
	void sorting() {
		assertThat(authorRepository.findAll(Sort.by("lastName")))
		.first()
		.extracting("lastName")
		.isEqualTo("Abbrescia");
		

		// assertThat(pAndSAuthorRepository.findAll(Sort.by("lastName")))
		// .extracting("lastName")
		// .isEqualTo("Abbrescia");

	}



	// @Test
	// void sorting2() {
	// 	Author a2 = new Author();
	// 	a2.setFirstname("Paolo");
	// 	a2.setLastname("abbrescia");
	// 	a2.setEmail("paolo@aulab.it");

	// 	entityManager.persist(a2);
		

	// 	List<Author> sorted = authorRepository.findAll(
	// 		Sort.by("lastName").descending()
	// 				.and(
	// 						Sort.by("firstName").ascending()));

		
	// 	for (Author a : sorted) {
	// 		System.out.println("Nome: " + a.getFirstname());
	// 		System.out.println("Cognome: " + a.getLastname());
	// 		System.out.println("email: " + a.getEmail());

	// 	}


	// 	// assertThat(pAndSAuthorRepository.findAll(Direction.DESC, ("lastName")))
	// 	// .extracting("firstName")
	// 	// .contains("Mirko", atIndex(0))
	// 	// .contains("Paolo", atIndex(1))
	// 	// .contains("Andrea", atIndex(2));



	// 	assertThat(authorRepository.findAll(
	// 		Sort.by("lastName").descending()
	// 		.and(
	// 			Sort.by("firstName").ascending()
	// 			)
	// 		)
	// 	)
	// 	.extracting("firstName")
	// 	.contains("Andrea", atIndex(0))
	// 	.contains("Mirko", atIndex(1))
	// 	.contains("Paolo", atIndex(2));

	// }



	@Test
	void paging() {

		authorRepository.deleteAll();

		for (int i = 0; i < 100; i ++) {
			Author a = new Author();
			a.setFirstname(String.valueOf(i));
			a.setLastname(String.valueOf(i));
			a.setEmail (String.valueOf (i));
		
			authorRepository.save(a);
		}

		assertThat (authorRepository.findAll()).hasSize(100);
		// assertThat (authorRepository.count()).isEqualTo(100);
		

		int pageSize = 10;
		PageRequest.of(0, pageSize);
		assertThat(authorRepository.findAll(PageRequest.of(0, pageSize)).getContent())
				.extracting("firstName")
				.contains("0", atIndex(0))
				.contains("1", atIndex(1));


		assertThat(authorRepository.findAll(PageRequest.of(1, pageSize)).getContent())
				.extracting("lastName")
				.contains("10", atIndex(0))
				.contains("11", atIndex(1));


		assertThat(authorRepository.findAll(PageRequest.of(9, pageSize)).getContent())
				.extracting("email")
				.contains("98", atIndex(8))
				.contains("99", atIndex(9));
	}



	@Test
	void customRepository() {
		List<Author> find = authorRepository.findQualcosa("nn");
		
		assertThat(find).hasSize(1);
		
		assertThat(find.get(0))
		.extracting("firstName")
		.isEqualTo("Andrea");
	}



		@Test
	void sorting3() {
		Author a2 = new Author();
		a2.setFirstname("Paolo");
		a2.setLastname("abbrescia");
		a2.setEmail("paolo@aulab.it");

		entityManager.persist(a2);
		

		List<Author> sorted = authorRepository.findAll(
			Sort.by("lastName").descending()
					.and(
							Sort.by("firstName").ascending()));

		
		for (Author a : sorted) {
			System.out.println("Nome: " + a.getFirstname());
			System.out.println("Cognome: " + a.getLastname());
			System.out.println("email: " + a.getEmail());

		}


		// assertThat(pAndSAuthorRepository.findAll(Direction.DESC, ("lastName")))
		// .extracting("firstName")
		// .contains("Mirko", atIndex(0))
		// .contains("Paolo", atIndex(1))
		// .contains("Andrea", atIndex(2));



		assertThat(authorRepository.findAll(
			Sort.by("lastName").descending()
			.and(
				Sort.by("firstName").ascending()
				)
			)
		)
		.extracting("firstName")
		.contains("Andrea", atIndex(0))
		.contains("Mirko", atIndex(1))
		.contains("Paolo", atIndex(2));

	}
}

