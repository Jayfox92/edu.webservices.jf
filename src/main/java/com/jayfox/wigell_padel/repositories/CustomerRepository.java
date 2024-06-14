package com.jayfox.wigell_padel.repositories;

import com.jayfox.wigell_padel.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserName(String userName);
}
