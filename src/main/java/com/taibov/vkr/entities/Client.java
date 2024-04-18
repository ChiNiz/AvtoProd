package com.taibov.vkr.entities;

import com.taibov.vkr.Main;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@Data
@Getter
@Setter
//@ToString
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "psswrd")
    private String psswrd;

    @Column(name = "role")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_address")
    private Address fk_address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    List<Order> orders;

    public void AddOrder(Order order){
        orders.add(order);
    }

    public Client(String email, String psswrd)
    {
        this.email = email;
        this.psswrd = psswrd;
        this.role = "user";
    }

    @Builder
    public Client(String email, String psswrd, Address address) {
        this.email = email;
        this.psswrd = psswrd;
        fk_address = address;
        this.role = "user";
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", psswrd='" + psswrd + '\'' +
                ", fk_address=" + fk_address +
                ", orders=" + orders +
                '}';
    }

    public Client Delete(){
        Main.DoInSession(session -> {
            session.delete(this);
        });
        return this;
    }
}



