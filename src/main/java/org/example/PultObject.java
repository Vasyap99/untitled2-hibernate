package org.example;

import javax.persistence.*;

@Entity
@Table(name="object")
public class PultObject {
    @Id
    @GeneratedValue
    @Column(name="i")
    int i;
    @Column(name="name")
    String name;
    @Column(name="a_district")
    String a_district;
    @Column(name="a_np")
    String a_np;
    @Column(name="a_street")
    String a_street;
    @Column(name="a_house_n")
    String a_house_n;
    @ManyToOne
    @JoinColumn(name="monter_n", nullable=false)
    Monter m;
    //@Column(name="monter_n")
    //int monter_n;
    @Column(name="pult_n")
    int pult_n;

    PultObject(){}
    PultObject(Monter m, String name,String a_district,String a_np,String a_street,String a_house_n){
        this.name=name;
        this.a_district=a_district;
        this.a_np=a_np;
        this.a_street=a_street;
        this.a_house_n=a_house_n;
        this.m=m;
    }
}
