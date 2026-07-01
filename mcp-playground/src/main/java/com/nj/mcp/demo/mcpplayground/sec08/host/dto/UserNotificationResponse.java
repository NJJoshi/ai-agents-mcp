package com.nj.mcp.demo.mcpplayground.sec08.host.dto;

import java.util.Map;

public record UserNotificationResponse(String progressToken,
                                       boolean confirmed,
                                       Map<String, Object> inputData) implements NotificationEvent {
}
