package com.bins.dao;

import com.bins.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Long> {
    User findByNameAndPassword(String name, String password);


    @Query(value = "select * from t_user where role_id=?1",nativeQuery = true)
    Page<User> findAllByRole(Long id, Pageable pageable);
}
