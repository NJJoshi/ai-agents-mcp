package com.nj.advisor.client;

import com.nj.advisor.dto.JobDetails;
import com.nj.advisor.dto.JobSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class JobClient {
    private static final String JOB_BY_ID_URI = "/api/jobs/{id}";
    private static final String JOB_BY_IDS_URI = "/api/jobs?ids={ids}";
    private static final String JOB_BY_SKILLS_URI = "/api/jobs?skills={skills}";
    private final RestClient restClient;

    public JobClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public JobDetails getJobDetails(Integer jobId){
        return this.restClient.get()
                .uri(JOB_BY_ID_URI, jobId)
                .retrieve()
                .body(JobDetails.class);
    }

    public List<JobDetails> getJobsDetails(List<Integer> jobIds){
        return this.restClient.get()
                .uri(JOB_BY_IDS_URI, jobIds)
                .retrieve()
                .body(new ParameterizedTypeReference<List<JobDetails>>() {
                });
    }

    public List<JobSummary> searchBySkills(List<String> skills){
        return this.restClient.get()
                .uri(JOB_BY_SKILLS_URI, skills)
                .retrieve()
                .body(new ParameterizedTypeReference<List<JobSummary>>() {
                });
    }


}
