package it.aulab.springblog.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import it.aulab.springblog.model.Author;

public interface CrudAuthorRepository extends ListCrudRepository<Author, Long> {  
    public List<Author> findByLastName(String lastname);

    public List<Author> findByFirstNameNotIgnoreCase(String s);

    public List<Author> findByFirstNameAndLastName(String firstname, String lastname);

    public List<Author> findByFirstNameOrLastName(String firstname, String lastname);

    public List<Author> findByFirstNameContains(String s);


    


}
