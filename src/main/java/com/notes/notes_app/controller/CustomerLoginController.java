package com.notes.notes_app.controller;

import com.notes.notes_app.model.Customer;
import com.notes.notes_app.service.CustomCustomerDetailServiceImpl;
import com.notes.notes_app.service.CustomerService;
import com.notes.notes_app.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class CustomerLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomCustomerDetailServiceImpl userDetailsService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public void signUp(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(customer.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
