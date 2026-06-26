package com.nj.mcp.demo.mcpplayground.sec01.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class SimpleTools {
    private static Logger  logger = LoggerFactory.getLogger(SimpleTools.class);

    @McpTool(name = "generate-random-number", description = "Generate Random Number")
    public Integer generateRandomNumber(){
        var random = ThreadLocalRandom.current().nextInt(1,1000);
        logger.info("Generated random number is {}",random);
        return random;
    }

    @McpTool(description = "Save the text content to a file")
    public void saveContentToFile(String text) {
        logger.info("saving content: {}", text);
    }
}
