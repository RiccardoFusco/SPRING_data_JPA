package it.aulab.springbootcontroller.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.springbootcontroller.controller.*;
import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.repository.AuthorRepository;
import it.aulab.springbootcontroller.service.AuthorService;

@RestController
@RequestMapping(value = "api/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;
    

    // @RequestMapping(value = "", method = RequestMethod.GET)




    @GetMapping
    public List<Author> getAll(
        @RequestParam(name = "firstName", required = false) String firstName,
        @RequestParam(name = "lastName", required = false) String lastName
    ){
            return authorService.read(firstName, lastName);
    }


    
    // @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping
    public Author post(@RequestBody Author author){
        return authorService.create(author);
    }



    @PutMapping("{id}")
    public Author put(@PathVariable("id") Long id, @RequestBody Author author) throws Exception{
        return authorService.update(id, author);
    }


    // @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) throws Exception{
        return authorService.delete(id);
}
    
}
