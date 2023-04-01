package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.Repository.CustomerRepository;
import edu.iu.c322.orderservice.Repository.InMemoryCustomerRepository;
import edu.iu.c322.orderservice.model.Customer;
import edu.iu.c322.orderservice.model.Item;
import edu.iu.c322.orderservice.model.Order;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    //get localhost:8080/customers
    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }



    //ORDER COMMANDS
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/orders/return")
    public void returnOrderItem(@PathVariable int id, @RequestBody Order order, @RequestBody int itemid, @RequestBody String reason) {
        InMemoryCustomerRepository myrepo = (InMemoryCustomerRepository) repository;
        myrepo.getCustomerbyId(id).getOrders().get(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/orders/{orderId}")
    public void cancelOrder(@PathVariable int id, @PathVariable int orderId) {
        InMemoryCustomerRepository repository1 = (InMemoryCustomerRepository) repository;
        repository1.getCustomerbyId(id).getOrders().remove(orderId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders}")
    public int create(@Valid @RequestBody Customer customer,String state, String city, int postalcode, Item[] items){
        Order order = new Order(city,state, customer.getId(), items, postalcode);
        customer.orders.add(order);
        return order.getOrderid();
    }




    @GetMapping
    public List<Customer> findAll(){

        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid @RequestBody Customer customer){
        Customer newCustomer = repository.save(customer);
        return newCustomer.getId();
    }

    //Put localhost:800/customers/2
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){
        customer.setId(id);
        repository.save(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Customer customer = new Customer();
        customer.setId(id);
        repository.delete(customer);
    }
}
