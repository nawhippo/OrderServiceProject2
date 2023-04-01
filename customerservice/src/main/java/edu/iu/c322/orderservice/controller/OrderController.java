package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.Repository.CustomerRepository;
import edu.iu.c322.orderservice.Repository.InMemoryCustomerRepository;
import edu.iu.c322.orderservice.model.Customer;
import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Order;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {

    //get localhost:8080/customers
    private CustomerRepository repository;

    public OrderController(CustomerRepository repository) {
        this.repository = repository;
    }


    //ORDER COMMANDS

    @GetMapping("/orders/{id}")
    public ArrayList<Order> getOrders(@PathVariable int id) {
        InMemoryCustomerRepository repository1 = (InMemoryCustomerRepository) repository;
        return repository1.getCustomerbyId(id).getOrders();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/orders/return")
    public void returnOrderItem(@PathVariable int id, @RequestBody int orderid, @RequestBody int itemid, @RequestBody String reason) {
        InMemoryCustomerRepository myrepo = (InMemoryCustomerRepository) repository;
        myrepo.getCustomerbyId(id).getOrders().get(orderid).getItems()[itemid].setReason(reason);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/orders/{orderId}")
    public void cancelOrder(@PathVariable int id, @PathVariable int orderId) {
        InMemoryCustomerRepository repository1 = (InMemoryCustomerRepository) repository;
        repository1.getCustomerbyId(id).getOrders().remove(orderId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders}")
    public int create(@Valid @RequestBody Customer customer, String state, String city, int postalcode, Item[] items) {
        Order order = new Order(city, state, customer.getId(), items, postalcode);
        customer.getOrders().add(order);
        return order.getOrderid();
    }
}
