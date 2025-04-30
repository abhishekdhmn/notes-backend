package com.notes.notes_app.controller;

import com.notes.notes_app.model.Customer;
import com.notes.notes_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getcustomer")
    public Customer getCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return customerService.getCustomer(username);
    }

    @PutMapping("/updatepassword")
    public Customer updateCustomerPassword(@RequestBody Customer customer) {
        return customerService.updateCustomerPassword(customer);
    }

    @DeleteMapping("/deletecustomer")
    public void deleteCustomer() {
        customerService.deleteCustomer();
    }
}
