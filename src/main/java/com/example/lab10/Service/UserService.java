package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//1. Get all Users:
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

//2. Add a new User: Adds a new user to the system.
    public void addNewUser(User user){
        userRepository.save(user);
    }

//3. Update a User: Updates an existing user’s information.
    public boolean updateUser(Integer id,User user){
        User OldUser = userRepository.getById(id);
        if(OldUser == null){
            return false;
        }
        OldUser.setName(user.getName());
        OldUser.setEmail(user.getEmail());
        OldUser.setPassword(user.getPassword());
        OldUser.setAge(user.getAge());
        OldUser.setRole(user.getRole());
        userRepository.save(OldUser);
        return true;
    }

//4. Delete a User: Deletes a user from the system. Note: Verify that the user exists.
    public boolean deleteUser(Integer id){
        User user = userRepository.getById(id);
        if(user == null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

}
