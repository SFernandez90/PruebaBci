package com.crudusers.crudusers.Security;

import com.crudusers.crudusers.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userAdmin =  new User();
        userAdmin.setName("admin");
        userAdmin.setEmail("admin@email.com");
        userAdmin.setPassword("$2a$10$6Lq93oX0p0jFS/eXlKU4iOaw7EjlvANfi.zDnejJ9geTk4mxKd9mO");
        return  new UserDetailsImpl(userAdmin);
    }
}
