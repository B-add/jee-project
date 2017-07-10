package com.mti.entities;

import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sabrine.Elbahri on 10/07/2017.
 */

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "body")
    private String body;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;
}
