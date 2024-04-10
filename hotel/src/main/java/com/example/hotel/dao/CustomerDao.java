package com.example.hotel.dao;

import com.example.hotel.dto.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerDao {
    public Customer loginCustomer(Customer customer) throws Exception;
}
