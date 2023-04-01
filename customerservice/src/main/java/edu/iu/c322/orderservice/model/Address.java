package edu.iu.c322.orderservice.model;

import jakarta.validation.constraints.NotEmpty;

public class Address {
    public Address(String state, String city, int postalcode) {
        this.state = state;
        this.city = city;
        this.postalcode = postalcode;
    }

    @NotEmpty(message = "state must be specified.")
    String state;
    @NotEmpty(message = "city must be specified.")
    String city;
    @NotEmpty(message = "postalcode must be specified.")
    int postalcode;
}
