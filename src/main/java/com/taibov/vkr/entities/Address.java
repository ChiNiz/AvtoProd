package com.taibov.vkr.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Data
//@ToString
@Getter
@Setter
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city, street, house, flat;

    @OneToOne(mappedBy = "fk_address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    public Address(String city, String street, String house, String flat)
    {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Address(String city, String street, String house, String flat, Client client)
    {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.client = client;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }
}

