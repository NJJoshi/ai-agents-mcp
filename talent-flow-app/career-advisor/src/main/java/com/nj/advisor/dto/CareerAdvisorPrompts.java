package com.nj.advisor.dto;

public record CareerAdvisorPrompts(PromptSet compareJobs,
                                   PromptSet evaluateJobs,
                                   PromptSet generateResume) {
}
