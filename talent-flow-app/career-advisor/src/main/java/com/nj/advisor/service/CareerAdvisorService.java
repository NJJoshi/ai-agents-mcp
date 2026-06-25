package com.nj.advisor.service;

import com.nj.advisor.client.CandidateClient;
import com.nj.advisor.client.CareerAdvisorClient;
import com.nj.advisor.client.JobClient;
import com.nj.advisor.dto.JobEvaluationResult;
import com.nj.advisor.dto.JobsComparisonResult;
import com.nj.advisor.dto.TailoredResume;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CareerAdvisorService {
    private final JobClient jobClient;
    private final CandidateClient candidateClient;
    private final CareerAdvisorClient careerAdvisorClient;

    public CareerAdvisorService(JobClient jobClient, CandidateClient candidateClient, CareerAdvisorClient careerAdvisorClient) {
        this.jobClient = jobClient;
        this.candidateClient = candidateClient;
        this.careerAdvisorClient = careerAdvisorClient;
    }

    public List<JobEvaluationResult> findJobs(Integer candidateId){
        var candidateDetails = this.candidateClient.getCandidateDetails(candidateId);
        var jobs = this.jobClient.searchBySkills(candidateDetails.skills());
        var resultObj = this.careerAdvisorClient.evaluateJobs(candidateDetails, jobs);
        return resultObj.stream()
                .sorted(Comparator.comparingInt(JobEvaluationResult::matchScore).reversed())
                .toList();
    }

    public JobsComparisonResult compareJobs(Integer candidateId, List<Integer> jobIds) {
        var candidate = this.candidateClient.getCandidateDetails(candidateId);
        var jobs = this.jobClient.getJobsDetails(jobIds);
        return this.careerAdvisorClient.compareJobs(candidate, jobs);
    }

    public TailoredResume generateResume(Integer candidateId, Integer jobId) {
        var candidate = this.candidateClient.getCandidateDetails(candidateId);
        var job = this.jobClient.getJobDetails(jobId);
        return this.careerAdvisorClient.generateResume(candidate, job);
    }
}
