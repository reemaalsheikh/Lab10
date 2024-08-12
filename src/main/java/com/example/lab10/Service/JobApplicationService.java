package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

//9. Get all Job Applications: Retrieves a list of all Job Application.
    public List<JobApplication> getAllJobApplication(){
        return jobApplicationRepository.findAll();
    }

    //10. Apply For Job: Adds a new job application to the system.
    public boolean ApplyforJob(JobApplication jobApplication){
        if(userRepository.existsById(jobApplication.getUserId()) && jobPostRepository.existsById(jobApplication.getJopPostId())){
            jobApplicationRepository.save(jobApplication);
            return true;
        }
        return false;
    }

    public boolean updateJobApplocation(Integer id,JobApplication jobApplication){
        JobApplication oldJobApplication = jobApplicationRepository.getById(id);
        if(oldJobApplication == null){
            return false;
        }
        oldJobApplication.setUserId(jobApplication.getUserId());
        oldJobApplication.setJopPostId(jobApplication.getJopPostId());
        jobApplicationRepository.save(oldJobApplication);
        return true;
    }

    //11. Withdraw Job Application: Deletes a job application from the system.
   //Note: Verify that the job application exists.
    public boolean WithdrawJobApplication(Integer id){
        JobApplication oldJobApplication = jobApplicationRepository.getReferenceById(id);
        if(oldJobApplication == null){
            return false;
        }
       jobApplicationRepository.delete(oldJobApplication);
        return true;
    }








}
