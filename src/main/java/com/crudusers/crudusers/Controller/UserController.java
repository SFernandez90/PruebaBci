package com.crudusers.crudusers.Controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crudusers.crudusers.DTO.ResponseDTO;
import com.crudusers.crudusers.DTO.UserDTO;
import com.crudusers.crudusers.Entity.User;
import com.crudusers.crudusers.Repository.UserRepository;
import com.crudusers.crudusers.Service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody UserDTO user) throws Exception {
        try {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new Exception("El correo ya registrado");
            }

            if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d)[A-Za-z\\d@$!%*?&]{8,}$")) {
                throw new Exception("Formato password inválido");
            }

            if (!user.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                throw new Exception("Formato email inválido");
            }

            ResponseDTO responseCreated = userService.save(user);
            if (Objects.nonNull(responseCreated)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(responseCreated);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonIgnoreProperties(value = {"phones"})
    public ResponseEntity<?> findAll() {
        try {
            List<User> userList = userService.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> findById(@PathVariable(name = "id") Long id) {
        System.out.println("ID " + id);
        return userService.findById(id);
    }


    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("usuario eliminado");
    }

    @PatchMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upDate(@RequestBody UserDTO user, @PathVariable(name = "id") Long id) throws Exception {
        Optional<User> exist = userRepository.findById(id);
        if (exist.isPresent()) {
            ResponseDTO userUpdate = userService.upDate(user, id, exist);
            if (Objects.nonNull(userUpdate)) {
                return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
    }


}
