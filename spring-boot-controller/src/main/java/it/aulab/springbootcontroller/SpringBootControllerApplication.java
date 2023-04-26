package it.aulab.springbootcontroller;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import it.aulab.springbootcontroller.dto.CreatePostDTO;
import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.model.Post;

@SpringBootApplication
public class SpringBootControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootControllerApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();
		// nel caso non funziona prova mettere la maiuscola a createPostDto alla "c"
		PropertyMap<CreatePostDTO, Post> createPostDtoToPostMapping = new PropertyMap<CreatePostDTO, Post>() {
			protected void configure(){
				map().setId(null);
				map().getAuthor().setId(source.getAuthorId());
				
			}
		};
		mapper.addMappings(createPostDtoToPostMapping);
		return mapper;
	}

}
