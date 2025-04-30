package com.notes.notes_app.service;

import com.notes.notes_app.model.Customer;
import com.notes.notes_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomCustomerDetailServiceImpl implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);

        if (customer != null){
            UserDetails userDetails = User.builder()
                    .username(customer.getUsername())
                    .password(customer.getPassword())
                    .roles(customer.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }

        throw new UsernameNotFoundException("user not found with username: " + username);
    }
}
