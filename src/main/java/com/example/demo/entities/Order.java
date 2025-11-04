package com.example.demo.entities;

import com.example.demo.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * JsonIgnore faz com que o objeto não carregue a relação. Em uma relação para muitos, se nenhum dos objetos
    * possuir a notação ocorrerá um loop em que um chamará o outro eternamente.
    *
    @JsonIgnore*/
    @ManyToOne //Um cliente pode ter muitos pedidos
    @JoinColumn(name = "client_id")
    private User client;

    private Integer orderStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant momment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order",
            cascade = CascadeType.ALL) //Mapeia o id do pagamento com o id do pedido (order) para serem o mesmo id.payment = id.order
    private Payment payment;

    public Order() {
    }

    public Order(Long id, Instant momment, OrderStatus orderStatus,User client) {
        this.id = id;
        this.client = client;
        this.momment = momment;
        setOrderStatus(orderStatus);
    }

    public Double getTotal() {
        Double sum = 0.0;

        for(OrderItem x : items){
            sum += x.getSubtotal();
        }

        return sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null ) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Instant getMomment() {
        return momment;
    }

    public void setMomment(Instant momment) {
        this.momment = momment;
    }

    public Set<OrderItem> getItems(){
        return this.items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
