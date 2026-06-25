package com.nj.advisor.client;

import tools.jackson.databind.json.JsonMapper;
import com.nj.advisor.dto.*;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Objects;

public class CareerAdvisorClient {

    private static final String JOBS = "jobs";
    private static final String JOB = "job";
    private static final String CANDIDATES = "candidate";

    private final ChatClient chatClient;
    private CareerAdvisorPrompts prompts;
    private final JsonMapper jsonMapper;


    public CareerAdvisorClient(ChatClient chatClient, JsonMapper jsonMapper, CareerAdvisorPrompts prompts) {
        this.chatClient = chatClient;
        this.jsonMapper = jsonMapper;
        this.prompts = prompts;
    }

    public List<JobEvaluationResult> evaluateJobs(CandidateDetails candidate, List<JobSummary> jobSummaries) {
        record JobEvaluationResultObj(List<JobEvaluationResult> jobEvaluationResults) {
        }
        var result = this.chatClient.prompt()
                .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                .system(this.prompts.evaluateJobs().system())
                .user(spec -> spec.text(this.prompts.evaluateJobs().user())
                        .param(CANDIDATES, this.toJsonString(candidate))
                        .param(JOBS, this.toJsonString(jobSummaries)))
                .call()
                .entity(new ParameterizedTypeReference<JobEvaluationResultObj>() {
                });
        return Objects.requireNonNull(result).jobEvaluationResults();
    }

    public JobsComparisonResult compareJobs(CandidateDetails candidate, List<JobDetails> jobs){
        return this.chatClient.prompt()
                            .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                            .system(this.prompts.compareJobs().system())
                            .user(spec -> spec.text(this.prompts.compareJobs().user())
                                    .param(CANDIDATES, this.toJsonString(candidate))
                                    .param(JOBS, this.toJsonString(jobs)))
                            .call()
                            .entity(JobsComparisonResult.class);
    }

    public TailoredResume generateResume(CandidateDetails candidate, JobDetails job){
        var resumeContent = this.chatClient.prompt()
                                .system(this.prompts.generateResume().system())
                                .user(spec -> spec.text(this.prompts.generateResume().user())
                                                                    .param(CANDIDATES, this.toJsonString(candidate))
                                                                    .param(JOB, this.toJsonString(job)))
                                .call()
                                .content();
        return new TailoredResume(job.id(), candidate.id(), resumeContent);
    }

    private String toJsonString(Object object) {
        try {
            return this.jsonMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }
}
