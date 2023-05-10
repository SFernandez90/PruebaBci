package com.crudusers.crudusers.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.crudusers.crudusers.Entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crudusers.crudusers.DTO.ResponseDTO;
import com.crudusers.crudusers.DTO.UserDTO;
import com.crudusers.crudusers.Entity.User;
import com.crudusers.crudusers.Repository.UserRepository;
import com.crudusers.crudusers.Service.UserService;
import com.crudusers.crudusers.Utils.Converter;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converter converter;

    public ResponseDTO save(UserDTO user) {
        ResponseDTO result = new ResponseDTO();
        LocalDateTime creationDate = LocalDateTime.now();
        User userEntity = converter.userDTOToUser(user);
        userEntity.setCreated(creationDate);
        userEntity.setModified(null);
        userEntity.setLastLogin(creationDate);
        userEntity.setActive(true);
        userEntity.setModified(null);
        if (userEntity.getId() == null) {
            User userSave = userRepository.save(userEntity);
            result.setId(userSave.getId());
            result.setCreated(creationDate);
            result.setModified(creationDate);
            result.setLastLogin(creationDate);
            result.setActive(true);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public ResponseDTO upDate(UserDTO user, Long id, Optional<User> exist) {
        Phone phone = user.getPhones().get(0);
		User extract = exist.get();
        ResponseDTO responseUpdated = new ResponseDTO();
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String number = phone.getNumber();
        String cityCode = phone.getCityCode();
        String countryCode = phone.getCountryCode();

        LocalDateTime dateMod = LocalDateTime.now();
        LocalDateTime dateLast = dateMod;
        responseUpdated.setId(id);
        responseUpdated.setCreated(extract.getCreated());
        responseUpdated.setActive(extract.isActive());
        responseUpdated.setModified(dateMod);
        responseUpdated.setLastLogin(dateLast);

        userRepository.upDate(id, name, email, password, dateMod, dateLast);
        userRepository.updatePhone(id, number, cityCode, countryCode);
        return responseUpdated;
    }


}
