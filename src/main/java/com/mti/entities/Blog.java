package com.mti.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by val on 10/07/17.
 */

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "isArchived")
    private Boolean isArchived;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private User owner;

}
