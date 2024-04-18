package com.taibov.vkr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float price;

    private String description;

    private Model model;

    public Car(String name, float price)
    {
        this.name = name;
        this.price = price;
    }

    public Car(String name, float price, Model model)
    {
        this.name = name;
        this.price = price;
        this.model = model;
    }

    public Car(String name, float price, String description, Model model)
    {
        this.name = name;
        this.price = price;
        this.description = description;
        this.model = model;
    }

    public String GetNameForThymeLeaf(){
        return "'" + name + "'";
    }
}