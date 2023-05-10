package com.crudusers.crudusers.Service;

import java.util.List;
import java.util.Optional;
import com.crudusers.crudusers.DTO.ResponseDTO;
import com.crudusers.crudusers.DTO.UserDTO;
import com.crudusers.crudusers.Entity.User;

public interface UserService {

    public ResponseDTO save(UserDTO user);

    List<User> findAll();

    public Optional<User> findById(Long id);

    public void deleteById(Long id);

    public ResponseDTO upDate(UserDTO user, Long id, Optional<User> exist);

}
