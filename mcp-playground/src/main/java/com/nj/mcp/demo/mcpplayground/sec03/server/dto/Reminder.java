package com.nj.mcp.demo.mcpplayground.sec03.server.dto;

import org.springframework.ai.mcp.annotation.McpToolParam;

import java.time.LocalDateTime;

public record Reminder(@McpToolParam(description = "Task Description (e.g. Doctors appointment)") String task,
                       @McpToolParam(description = "Reminder date-time in ISO format (e.g. 2026-06-26T17:00)") LocalDateTime remindAt) {
}
