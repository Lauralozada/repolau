package com.CrudSpring.app.service;

import com.CrudSpring.app.entity.User;
import com.CrudSpring.app.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRespository userRespository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> finAll() {
        return userRespository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<User> finALL(Pageable pageable) {
        return userRespository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> finById(Long id) {
        return userRespository.findById(id);
    }

    @Override
    @Transactional
    public User Save(User user) {
        return userRespository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    userRespository.deleteById(id);
    }
}
