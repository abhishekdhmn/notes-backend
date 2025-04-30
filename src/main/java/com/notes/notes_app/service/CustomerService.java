package com.notes.notes_app.service;

import com.notes.notes_app.model.Customer;
import com.notes.notes_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void addNewCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(String username) {
        return customerRepository.findByUsername(username);
    }

    public Customer updateCustomerPassword(Customer customer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customerInDB = customerRepository.findByUsername(username);
        customerInDB.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customerInDB);
    }

    public void deleteCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerRepository.findByUsername(username);
        customerRepository.delete(customer);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
}
