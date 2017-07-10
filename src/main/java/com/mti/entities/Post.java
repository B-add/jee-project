package com.mti.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by val on 10/07/17.
 */
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;
}
