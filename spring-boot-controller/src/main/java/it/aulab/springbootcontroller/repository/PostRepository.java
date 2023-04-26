package it.aulab.springbootcontroller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.aulab.springbootcontroller.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query("SELECT p FROM Post p WHERE p.author.firstName ='Mirko'")
    List<Post> findMirko();


    @Query("SELECT p FROM Post p WHERE p.author.firstName = ?1")
    List<Post> findByAuthorFirstName(String firstName);


    @Query("SELECT p FROM Post p WHERE p.author.firstName = :firstName AND p.author.lastName = :lastName")
    List<Post> findByAuthorFirstNameAndLastName(@Param("lastName") String lastname,
            @Param("firstname") String firstName);


}


