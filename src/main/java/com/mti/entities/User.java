package com.mti.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by val on 10/07/17.
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Blog> blogs;
}