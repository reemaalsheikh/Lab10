package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class JobPostService {

    private final JobPostRepository jobPostRepository;

//5. Get all JobPosts: Retrieves a list of all Job Posts.
    public List<JobPost> getAllJP(){
        return jobPostRepository.findAll();
    }

//6. Add a new JobPost: Adds a new job post to the system.
    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

//7. Update a JobPost: Updates an existing job post ’s information.
    public boolean updateJobPost(Integer id,JobPost jobPost){
        JobPost j = jobPostRepository.getById(id);
        if(j == null){
            return false;
        }
        j.setTitle(jobPost.getTitle());
        j.setDescription(jobPost.getDescription());
        j.setLocation(jobPost.getLocation());
        j.setSalary(jobPost.getSalary());
        j.setPostingDate(jobPost.getPostingDate());
        jobPostRepository.save(j);
        return true;
    }

//8. Delete a JobPost: Deletes a job post from the system. Note: Verify that the job post exists.
    public boolean deleteJobPost(Integer id){
        JobPost j = jobPostRepository.getById(id);
        if(j == null){
            return false;
        }
        jobPostRepository.delete(j);
        return true;
    }
}
