package com.nj.hiring.service;


import com.nj.hiring.dto.JobApplicationSubmissionRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {


    @Transactional
    public void submitApplication(JobApplicationSubmissionRequest request) {

    }

}
