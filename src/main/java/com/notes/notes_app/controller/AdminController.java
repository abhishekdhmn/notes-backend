package com.notes.notes_app.controller;

import com.notes.notes_app.model.Customer;
import com.notes.notes_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getalluser")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }


}
