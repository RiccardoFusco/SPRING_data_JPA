package it.aulab.springblog.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname", length = 100, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String email;



    @OneToMany(mappedBy = "")
    List<Post> posts = new ArrayList<Post>();
    
    
    public Author() {
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getFirstname() {
        return firstName;
    }



    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }



    public String getLastname() {
        return lastName;
    }



    public void setLastname(String lastname) {
        this.lastName = lastname;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

}
