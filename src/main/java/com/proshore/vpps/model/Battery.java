package com.proshore.vpps.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer postCode;
    private Integer capacity;
}
