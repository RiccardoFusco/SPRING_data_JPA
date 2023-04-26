package it.aulab.springbootcontroller.util.mapper;

import org.modelmapper.PropertyMap;

import it.aulab.springbootcontroller.dto.CreatePostDTO;
import it.aulab.springbootcontroller.model.Post;

public class CreatePostDTOToPostPropertyMap extends PropertyMap<CreatePostDTO, Post> {


    @Override
    protected void configure(){
        map().setId(null);
        map().getAuthor().setId(source.getAuthorId());
    }
    
}
