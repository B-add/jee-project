package com.mti.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by val on 10/07/17.
 */

@Data
@Entity
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
