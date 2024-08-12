package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

//1. Get all Users:
@GetMapping("/get")
public ResponseEntity getAllUsers() {
    return ResponseEntity.status(200).body(userService.getAllUsers());
}
//2. Add a new User: Adds a new user to the system.
@PostMapping("/add")
public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
   userService.addNewUser(user);
    return ResponseEntity.status(200).body(new ApiResponse("User Successfully added!"));
}
//3. Update a User: Updates an existing user’s information.

    @PutMapping("/update/{id}")
    public  ResponseEntity updateUser(@PathVariable Integer id,@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User Successfully updated!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found!"));
    }
//4. Delete a User: Deletes a user from the system. Note: Verify that the user exists.
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteUser(@PathVariable Integer id){
    boolean isDeleted = userService.deleteUser(id);
    if(isDeleted){
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully deleted!"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("User not found!"));
}

}
