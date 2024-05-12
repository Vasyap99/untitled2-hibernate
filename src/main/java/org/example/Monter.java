package org.example;

import javax.persistence.*;
//import org.hibernate.annotations.Entity;

@Entity
@Table(name="monter")
public class Monter {
    @Id
    @GeneratedValue
    @Column(name="i")
    public int i;
    @Column(name="name")
    public String name;
    Monter(String name){this.name=name;}
    Monter(){}
}
