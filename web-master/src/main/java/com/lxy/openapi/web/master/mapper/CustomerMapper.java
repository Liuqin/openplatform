package com.lxy.openapi.web.master.mapper;

import com.lxy.openapi.web.master.pojo.Customer;

import java.util.List;

public interface CustomerMapper {

    int insertCustomer(Customer object);

    int updateCustomer(Customer object);

    int update(Customer.UpdateBuilder object);

    List<Customer> queryCustomer(Customer object);

    Customer queryCustomerLimit1(Customer object);

    Customer getCustomerById(int id);

    List<Customer> getAllCustomer();
}