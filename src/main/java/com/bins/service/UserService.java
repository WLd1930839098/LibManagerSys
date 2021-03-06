package com.bins.service;

import com.bins.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User login(String name,String password);

    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void add(User user);

    void deleteById(Long id);

    Page<User> findAllByRole(Long roleId,Pageable pageable);
}
