package com.nj.mcp.demo.mcpplayground.sec03.server.tools;

import com.nj.mcp.demo.mcpplayground.sec03.server.dto.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ReminderTools {
    private static final Logger logger = LoggerFactory.getLogger(ReminderTools.class);
    private final List<Reminder> reminders = new CopyOnWriteArrayList<>();

    @McpTool(name="create-reminder", description = "Create reminder with task and scheduled date-time")
    public void createReminder(Reminder reminder) {
       logger.info("Current time: {}, request: {}", LocalDateTime.now(), reminder);
       reminders.add(reminder);
    }

    @McpTool(name = "list-reminders", description = "List all scheduled reminders")
    public List<Reminder> listReminders() {
        logger.info("Listing reminders");
        return List.copyOf(reminders);
    }


}
