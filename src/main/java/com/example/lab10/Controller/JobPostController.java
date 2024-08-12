package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/jopPost")
public class JobPostController {
    private final JobPostService jobPostService;

//5. Get all JobPosts: Retrieves a list of all Job Posts.

    @GetMapping("/get")
    public ResponseEntity getAllJopPosts() {
        return ResponseEntity.status(200).body(jobPostService.getAllJP());
    }

//6. Add a new JobPost: Adds a new job post to the system.
@PostMapping("/add")
public ResponseEntity addJopPost(@Valid @RequestBody JobPost jobPost, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    jobPostService.addJobPost(jobPost);
    return ResponseEntity.status(200).body(new ApiResponse("Jop Post Successfully added!"));
}


//7. Update a JobPost: Updates an existing job post ’s information.
@PutMapping("/update/{id}")
public  ResponseEntity updateJopPost(@PathVariable Integer id,@Valid @RequestBody JobPost jobPost , Errors errors){
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    boolean isUpdated = jobPostService.updateJobPost(id, jobPost);
    if(isUpdated){
        return ResponseEntity.status(200).body(new ApiResponse("Job Post updated successfully!"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("Job Post not found!"));
}
//8. Delete a JobPost: Deletes a job post from the system. Note: Verify that the job post exists.
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteCoffee(@PathVariable Integer id){
    boolean isDeleted = jobPostService.deleteJobPost(id);
    if(isDeleted){
        return ResponseEntity.status(200).body(new ApiResponse("Job Post deleted successfully!"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("Job Post not found!"));
}
}
