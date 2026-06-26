package com.nj.mcp.demo.mcpplayground.sec01.host.dto;

import java.util.List;

public record McpSessionManifest(String modelName,
                                 List<String> availableTools,
                                 List<String> suggestedInputs) {
}
