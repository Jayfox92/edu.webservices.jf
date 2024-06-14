package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    //Administratörer ska kunna göra ett antal aktiviteter med följande endpoints:
      //      • Lista kunder GET /api/v5/customers
    List<Customer> getCustomers();
}
