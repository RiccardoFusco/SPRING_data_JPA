package it.aulab.springbootcontroller.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
@JsonIncludeProperties({ "comments"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 200, nullable = false)
    private String title;


    @Column(length = 1000, nullable = false)
    private String body;


    @Column(name= "publish_date", length = 8)
    private String publishDate;

    @JsonIgnoreProperties({ "posts" })
    @ManyToOne
    // @JoinColumn(name = "author_id") //se proprio voglio essere specifico, altrimenti funziona lo stesso
    private Author author;

    @JsonIgnoreProperties({ "posts" })
    @OneToMany(mappedBy = "post")
    private List<Comment> comments= new ArrayList<Comment>();

    private List<Comment> getComments(){

        return comments;      

        }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    


    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
