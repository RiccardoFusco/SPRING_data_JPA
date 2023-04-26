package it.aulab.springbootcontroller.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.model.Post;
import it.aulab.springbootcontroller.repository.AuthorRepository;
import it.aulab.springbootcontroller.repository.PostRepository;
import jakarta.transaction.Transactional;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService{


    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;


    @Override
	@Transactional
	private void transaction(){	

		Author a = new Author();
		a.setFirstName("null");
		a.setLastName("null");
		a.setEmail("null");

		authorRepository.saveAndFlush(a);

		Post p1 = new Post();
		p1.setAuthor(a);
		p1.setBody("Lorem ipsum");
		p1.setTitle("Lorem ipsum");
		p1.setPublishDate("20230514");

		postRepository.save(p1);


		Post p2 = new Post();
		p2.setAuthor(a);
		p2.setBody("Lorem ipsum 2");
		p2.setTitle("Lorem ipsum 2");
		p2.setPublishDate("20230514");


        throw new RuntimeException();

		postRepository.saveAndFlush(p2);

}




    @Override
	public void noTransaction() {

        Author a = new Author();
        a.setFirstName("null");
        a.setLastName("null");
        a.setEmail("null");

        authorRepository.save(a);

        Post p1 = new Post();
        p1.setAuthor(a);
        p1.setBody("Lorem ipsum");
        p1.setTitle("Lorem ipsum");
        p1.setPublishDate("20230514");

        postRepository.saveAndFlush(p1);


        Post p2 = new Post();
        p2.setAuthor(a);
        p2.setBody("Lorem ipsum 2");
        p2.setTitle("Lorem ipsum 2");
        p2.setPublishDate("20230514");

        throw new RuntimeException();

}

    @Override
    public List<Author> read(String firstName, String lastName){
    if (firstName != null && lastName != null) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    } else if (firstName != null) {
        return authorRepository.findByFirstName(firstName);
    
    } else if (lastName != null) {
         return authorRepository.findByLastName(lastName);
         
    } else {
        return authorRepository.findAll();
    }
    
    
}




    @Override
    public Author create(Author author) {
        return authorRepository.findAll();

    }






    @Override
    public Author update(Long id, Author author) throws Exception {
        Optional<Author> dbOptionalAuthor = authorRepository.findById(id);
        if (dbOptionalAuthor.isPresent()) {
            Author dbAuthor = dbOptionalAuthor.get();
            dbAuthor.setFirstName(author.getFirstName());
            dbAuthor.setFirstName(author.getLastName());
            dbAuthor.setEmail(author.getEmail());
            authorRepository.save(dbAuthor);
            return dbAuthor;
        }
        throw new Exception();
    }




    @Override
    public String delete(Long id) throws Exception {
        if (authorRepository.findById(id).isPresent()) {
             authorRepository.deleteById(id);
             return "OK";
    }
    throw new Exception();

}
}








