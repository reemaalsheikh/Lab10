package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.User;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/JopApp")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

//9. Get all Job Applications: Retrieves a list of all Job Application.
@GetMapping("/get")
public ResponseEntity getAllJobApplications() {
    return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
}

//10. Apply For Job: Adds a new job application to the system.
@PostMapping("/add")
 public ResponseEntity ApplyForJob(@Valid @RequestBody JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isApplied = jobApplicationService.ApplyforJob(jobApplication);
         if(isApplied){
        jobApplicationService.ApplyforJob(jobApplication);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application Added Successfully"));
         }
         return ResponseEntity.status(400).body(new ApiResponse("Error! Job Application cannot be Added!"));
}


@PutMapping("/update/{id}")
public ResponseEntity updateJ0pbApplication(@PathVariable Integer id,@Valid @RequestBody JobApplication jobApplication, Errors errors) {
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    boolean isUpdated = jobApplicationService.updateJobApplocation(id, jobApplication);
    if (isUpdated) {
        return ResponseEntity.status(200).body(new ApiResponse("Job Application Updated Successfully"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("Job Application Not Found!"));
}

 //11. Withdraw Job Application: Deletes a job application from the system.
//Note: Verify that the job application exists.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity WithdrawJobApplication(@PathVariable Integer id){
        boolean isDeleted = jobApplicationService.WithdrawJobApplication(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Job Application deleted Successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Job Application not found!"));
    }



}
