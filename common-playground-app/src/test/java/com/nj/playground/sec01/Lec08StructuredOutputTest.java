package com.nj.playground.sec01;

import com.nj.playground.AbstractTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class Lec08StructuredOutputTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec08StructuredOutputTest.class);

    record Book(String title,
                String author,
                int publishedYear) {
    }

    @Test
    public void mapEntityFromText() {
        var prompt = """
                Extract book details from the below text.
                
                I just finished reading "Clean Code" by John.
                The copyright page says it was printed in 2015.
                """;
        var book = this.executePrompt(prompt)
                       .entity(Book.class);
        log.info("{}", book);
    }



    private ChatClient.CallResponseSpec executePrompt(String prompt) {
        return this.chatClient.prompt(prompt)
                              .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                              .call();
    }

}
