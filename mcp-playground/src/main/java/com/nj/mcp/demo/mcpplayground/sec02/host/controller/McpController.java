package com.nj.mcp.demo.mcpplayground.sec02.host.controller;

import com.nj.mcp.demo.mcpplayground.sec02.host.dto.ChatRequest;
import com.nj.mcp.demo.mcpplayground.sec02.host.dto.McpSessionManifest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/mcp")
public class McpController {

    private final ChatClient chatClient;
    private final McpSessionManifest mcpSessionManifest;

    public McpController(ChatClient chatClient, McpSessionManifest mcpSessionManifest) {
        this.chatClient = chatClient;
        this.mcpSessionManifest = mcpSessionManifest;
    }

    @GetMapping("manifest")
    public McpSessionManifest getMcpSessionManifest() {
        return mcpSessionManifest;
    }

    @PostMapping
    public Flux<String> chat(@RequestBody ChatRequest request) {
        return this.chatClient.prompt(request.message())
                .stream()
                .content();
    }
}
