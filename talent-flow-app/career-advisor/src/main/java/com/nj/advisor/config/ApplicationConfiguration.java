package com.nj.advisor.config;

import tools.jackson.databind.json.JsonMapper;
import com.nj.advisor.client.CandidateClient;
import com.nj.advisor.client.CareerAdvisorClient;
import com.nj.advisor.client.JobClient;
import com.nj.advisor.dto.CareerAdvisorPrompts;
import com.nj.advisor.dto.PromptSet;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.Charset;

@Configuration
public class ApplicationConfiguration {
    private static final String USER_TEMPLATE_PATH_FORMAT = "classpath:prompt-templates/%s/user.txt";
    private static final String SYSTEM_TEMPLATE_PATH_FORMAT = "classpath:prompt-templates/%s/system.txt";

    private final ResourceLoader resourceLoader;

    public ApplicationConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public JobClient jobClient(RestClient.Builder builder, @Value("${job-service.url}") String baseUrl){
        var restClient = builder.baseUrl(baseUrl).build();
        return new JobClient(restClient);
    }

    @Bean
    public CandidateClient candidateClient(RestClient.Builder builder, @Value("${candidate-service.url}") String baseUrl){
        var restClient = builder.baseUrl(baseUrl).build();
        return new CandidateClient(restClient);
    }

    @Bean
    public CareerAdvisorClient  careerAdvisorClient(ChatClient.Builder builder, JsonMapper jsonMapper){
        var chatClient = builder.build();
        var prompts = new CareerAdvisorPrompts(
                this.getPromptSet("compare-jobs"),
                this.getPromptSet("evaluate-jobs"),
                this.getPromptSet("generate-resume")
        );
        return new CareerAdvisorClient(chatClient, jsonMapper, prompts);
    }

    private String getResourceContent(String resourcePath){
        try{
            var resource = this.resourceLoader.getResource(resourcePath);
            return resource.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PromptSet getPromptSet(String feature) {
        return new PromptSet(
                this.getResourceContent(SYSTEM_TEMPLATE_PATH_FORMAT.formatted(feature)),
                this.getResourceContent(USER_TEMPLATE_PATH_FORMAT.formatted(feature))
        );
    }
}
