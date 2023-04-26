package it.aulab.springbootcontroller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.springbootcontroller.model.Comment;
import it.aulab.springbootcontroller.repository.CommentRepository;

@RestController
@RequestMapping(value = "api/comments")
public class CommentRestController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
    
}
