package edu.iu.c322.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Order {

    public Order(@NotEmpty(message = "item list cannot be empty") Item[] items) {

        this.items = items;
    }

    public Order() {

    }

    public Item[] getItems() {
        return items;
    }

    public Order(String city, String state, int customerid, @NotEmpty Item[] items, int postalcode) {
        this.items = items;
        this.address = new Address(state,city,postalcode);
        this.customerid = customerid;
    }

    private int customerid;
    @NotEmpty(message = "postal code cannot be empty.")
    private int postalcode;

    @NotEmpty(message = "item list cannot be empty")
    private Item[] items;
    private Address address;

    //autogenerate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Orderid;

    public int getOrderid() {
        return this.Orderid;
    }
}
