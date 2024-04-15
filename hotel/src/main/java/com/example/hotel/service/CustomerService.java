package com.example.hotel.service;

import com.example.hotel.dto.Customer;


public interface CustomerService {
    public Customer loginCustomer(Customer customer) throws Exception;
    public void updateBalance(Customer customer) throws Exception;
    public Customer getBalance(Customer customer);
}
