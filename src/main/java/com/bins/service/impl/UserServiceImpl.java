package com.bins.service.impl;

import com.bins.bean.User;
import com.bins.dao.UserDao;
import com.bins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String name, String password) {
        return userDao.findByNameAndPassword(name,password);
    }

    public Page<User> findAll(Pageable pageable){
        return userDao.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }
}
