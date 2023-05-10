package com.crudusers.crudusers.Utils;

import org.springframework.stereotype.Component;

import com.crudusers.crudusers.DTO.UserDTO;
import com.crudusers.crudusers.Entity.User;

@Component
public class Converter implements IMapper {

	@Override
	public User userDTOToUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setPhones(userDTO.getPhones());
		return user;
	}

}
