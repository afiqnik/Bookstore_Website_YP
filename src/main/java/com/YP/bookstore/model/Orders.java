package com.YP.bookstore.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(
        name = "orderproduct",
        joinColumns = @JoinColumn(name = "ordersID"),
        inverseJoinColumns = @JoinColumn(name = "productID")
    )
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name="userID",unique = false,nullable = false)
    private User user;
    private Integer price;
    private Integer totalprice;
    private String orderdate;
}