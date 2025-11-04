package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user") // por que o H2 possui user como palavra reservada
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // define a estratégia de autoincremento
    private Long id;
    private String name;
    private  String email;
    private String phone;
    private String password;


    @JsonIgnore
    //Um cliente pode ter vários pedidos
    @OneToMany(mappedBy = "client") // O mapped aponta para o nome da variavel que está mapeada no Order
    private List<Order> orders = new ArrayList<>();
    public User() {
    }


    public User(Long id, String name, String phone, String password) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
