package com.example.pma.dao;

import com.example.pma.entities.Project;
import com.example.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;


public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
    @Override
    List<UserAccount> findAll();

    UserAccount findByUserId(long theId);
}
