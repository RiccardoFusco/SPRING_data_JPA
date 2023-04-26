package it.aulab.springbootcontroller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.aulab.springbootcontroller.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{      
    
    public List<Author> findByLastName(String lastname);

    public List<Author> findByFirstNameNotIgnoreCase(String s);

    public List<Author> findByFirstNameAndLastName(String firstname, String lastname);

    public List<Author> findByFirstNameOrLastName(String firstname, String lastname);

    public List<Author> findByFirstNameContains(String s);


    


}
