package it.aulab.springbootcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.springbootcontroller.repository.AuthorRepository;

@Controller
@RequestMapping(value = "posts")
public class PostController {
    
    @Autowired
    private AuthorRepository postRepository;

}
