package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.InvalidEmailException;
import com.saidul.Splitwise.Exception.InvalidPasswordException;
import com.saidul.Splitwise.Exception.RegistrationException;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User signUp(String name, String email, String password){
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser != null){
            throw new RegistrationException("The email address you entered is already in use");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }
    @Override
    public User login(String email, String password){
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null){
            throw new InvalidEmailException("We could not find an account associated with given email address");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.encode(password);
        if(encoder.matches(password, savedUser.getPassword())){
            return savedUser;
        }else {
            throw new InvalidPasswordException("Incorrect password");
        }
    }
}
