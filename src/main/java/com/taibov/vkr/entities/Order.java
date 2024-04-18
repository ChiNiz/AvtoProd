package com.taibov.vkr.entities;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.*;
import com.taibov.vkr.Main;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

//@Data
@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@TypeDefs({
        @TypeDef(
                defaultForType = int[].class,
                typeClass = IntArrayType.class
        )
})
public class Order implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private float price;
    @Column(name = "cars_id", columnDefinition = "integer[]")
    private int[] cars_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Order(int[] cars_id)
    {
        this.price = 0;
        Main.DoInSession(session -> {
            for(int i = 0; i < cars_id.length; i++){
                Car car = session.get(Car.class, cars_id[i]);
                this.price += car.getPrice();
            }
        });
        this.cars_id = cars_id;
    }

    public Order(int[] cars_id, float price)
    {
        this.price = price;
        this.cars_id = cars_id;
    }

    public Order(int[] cars_id, float price, Client client)
    {
        this.price = price;
        this.cars_id = cars_id;
        this.client = client;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "order_id=" + order_id +
                ", price=" + price +
                ", cars_id=" + Arrays.toString(cars_id) +
                '}';
    }

}
