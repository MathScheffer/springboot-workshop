package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

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
    private String nome;
    private String phone;
    private String password;


    @JsonIgnore
    //Um cliente pode ter vários pedidos
    @OneToMany(mappedBy = "client") // O mapped aponta para o nome da variavel que está mapeada no Order
    private List<Order> orders = new ArrayList<>();
    public User() {
    }


    public User(Long id, String nome, String phone, String password) {
        super();
        this.id = id;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassord() {
        return password;
    }

    public void setPassord(String passord) {
        this.password = passord;
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
