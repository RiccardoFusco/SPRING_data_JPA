package it.aulab.springbootcontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.aulab.springbootcontroller.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    

    @Modifying
    @Query("SELECT c FROM Comment c WHERE c.email LIKE 'mirko%'")
    void deleteMirko();
}
