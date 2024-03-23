package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.*;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public User addFriend(int id, String email) {
        User userAddingFriend = userRepository.findUserById(id);
        User userBeingAddedAsFriend = userRepository.findUserByEmail(email);
        if(userAddingFriend == null){
            throw new InvalidUserIdException("Id for user adding friend does not exist");
        } else if (userBeingAddedAsFriend == null) {
            throw new InvalidEmailException("Email for userBeingAddedAsFriend does not exist");
        }
        List<User> friendListOfUserAdding = new ArrayList<>();
        if(userAddingFriend.getFriends() != null){
            friendListOfUserAdding = userAddingFriend.getFriends();
        }
        List<User> userBeingAddedAsFriendList = new ArrayList<>();
        if(userBeingAddedAsFriend.getFriends() != null){
            userBeingAddedAsFriendList = userBeingAddedAsFriend.getFriends();
        }
        validateAdd(userBeingAddedAsFriend, friendListOfUserAdding);
        validateAdd(userAddingFriend, userBeingAddedAsFriendList);
        userRepository.save(userAddingFriend);
        userRepository.save(userBeingAddedAsFriend);
        return userAddingFriend;
    }
    public User getUserById(int id){
        return userRepository.findUserById(id);
    }

    private void validateAdd(User newFriend, List<User> friendList){
        if(friendList.contains(newFriend)){
            throw new InvalidFriendRequestException("Adding the same friend multiple times is not allowed.");
        }else {
            friendList.add(newFriend);
        }
    }
}
