package com.bins.service.impl;

import com.bins.bean.Role;
import com.bins.bean.User;
import com.bins.dao.UserDao;
import com.bins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String name, String password) {
        return userDao.findByNameAndPassword(name, password);
    }

    public Page<User> findAll(Pageable pageable) {
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

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Page<User> findAllByRole(Long roleId,Pageable pageable) {
        return userDao.findAllByRole(roleId, pageable);
    }

}
