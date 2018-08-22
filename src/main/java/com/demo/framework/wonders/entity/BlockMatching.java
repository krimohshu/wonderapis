package com.demo.framework.wonders.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Krishan Shukla
 */

@Entity
public class BlockMatching implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", nullable = true)
    private String firstName;

    @Column(name = "lastName", nullable = true)
    private String lastName;
}
