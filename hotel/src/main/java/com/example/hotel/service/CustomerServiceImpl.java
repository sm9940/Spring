package com.example.hotel.service;

import com.example.hotel.dao.CustomerDao;
import com.example.hotel.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDao customerDao;
    @Override
    public Customer loginCustomer(Customer customer) throws Exception {
        return customerDao.loginCustomer(customer);
    }

    @Override
    public void updateBalance(Customer customer) throws Exception {
        customerDao.updateBalance(customer);
    }

    @Override
    public Customer getBalance(Customer customer) {
        return customerDao.getBalance(customer);
    }

    @Override
    public void updateCustomerBalance(String customerId, int payment) {
        customerDao.updateCustomerBalance(customerId,payment);
    }
}
