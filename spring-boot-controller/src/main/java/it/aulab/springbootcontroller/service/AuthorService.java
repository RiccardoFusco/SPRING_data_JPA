package it.aulab.springbootcontroller.service;

import java.util.List;

import it.aulab.springbootcontroller.model.Author;

public interface AuthorService {
    List<Author> read(String firstName, String lastName);
    Author create(Author author);
    Author update(Long id, Author author) throws Exception;
    Author delete(Long id) throws Exception;
    void transaction();
    void noTransaction();
}
