package com.CrudSpring.app.controller;

import com.CrudSpring.app.entity.User;
import com.CrudSpring.app.service.UserService;
import com.CrudSpring.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //crear nuevo usuario
    @PostMapping
    public ResponseEntity<?>create (@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.Save(user));
    }
    //leer un usuario
    @GetMapping("/{id}")
    public ResponseEntity<?> read (@PathVariable Long id){
        Optional<User> oUser =userService.finById(id);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }

    //actulizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable Long id) {
        Optional<User> user = userService.finById(id);

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.get().setName(userDetails.getName());
        user.get().setSurname(userDetails.getSurname());
        user.get().setEmail(userDetails.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.Save(user.get()));
    }

    //borrar un usuario
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
        if (!userService.finById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //leer todos los usuarios
    @GetMapping
    public List<User> readAll(){
        List<User> users = StreamSupport
                .stream(userService.finAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }
}
