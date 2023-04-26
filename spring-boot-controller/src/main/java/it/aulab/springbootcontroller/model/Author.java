package it.aulab.springbootcontroller.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname", length = 100, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 100, nullable = false)
    private String lastName;

    @JsonIgnore
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



    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastname) {
        this.lastName = lastname;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

}
