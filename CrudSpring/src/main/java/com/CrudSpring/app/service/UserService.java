package com.CrudSpring.app.service;

import com.CrudSpring.app.entity.User;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

     Iterable<User> finAll();
     Page<User> finALL(Pageable pageable);
     Optional<User> finById(Long id);
     User Save(User user);


     void deleteById(Long id);
}
