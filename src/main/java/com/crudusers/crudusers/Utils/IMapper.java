package com.crudusers.crudusers.Utils;

import com.crudusers.crudusers.DTO.UserDTO;
import com.crudusers.crudusers.Entity.User;

public interface IMapper{
	User userDTOToUser(UserDTO userDTO);
}
