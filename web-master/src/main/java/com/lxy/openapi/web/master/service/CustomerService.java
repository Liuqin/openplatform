package com.lxy.openapi.web.master.service;

import com.github.pagehelper.PageInfo;
import com.lxy.openapi.web.master.pojo.Customer;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer) throws Exception;

    PageInfo<Customer> getCustomerList(Customer criteria, int page, int limit);

    void updateCustomer(Customer customer);

    void deleteCustomer(int[] ids);

    Customer getCustomerById(int id);

    List<Customer> getAllCustomer();

    void addMoney(Integer money,int id);
}
