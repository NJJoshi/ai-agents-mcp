package com.nj.mcp.demo.mcpplayground.sec05.server.tools;

import com.nj.mcp.demo.mcpplayground.sec05.dto.UserCategory;
import com.nj.mcp.demo.mcpplayground.sec05.dto.UserContext;
import com.nj.mcp.demo.mcpplayground.sec05.server.dto.Order;
import com.nj.mcp.demo.mcpplayground.sec05.server.dto.ToolResult;
import com.nj.mcp.demo.mcpplayground.sec05.server.exception.OrderNotFoundException;
import com.nj.mcp.demo.mcpplayground.sec05.server.service.OrderService;
import org.springframework.ai.mcp.annotation.McpMeta;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Component
public class OrderTools {

    private final OrderService orderService;
    private final JsonMapper jsonMapper;

    public OrderTools(OrderService orderService,  JsonMapper jsonMapper) {
        this.orderService = orderService;
        this.jsonMapper = jsonMapper;
    }

    @McpTool(description = "List all orders")
    public List<Order> listOrders(McpMeta mcpMeta) {
        var userContext = this.getUserContext(mcpMeta);
        return this.orderService.listOrders(userContext.userId());
    }

    @McpTool(description = "Cancel an order")
    public ToolResult cancelOrder(McpMeta mcpMeta, int orderId)
    {
        try {
            var userContext = this.getUserContext(mcpMeta);
            if (UserCategory.PREMIUM.equals(userContext.userCategory())) {
                this.orderService.cancelOrder(userContext.userId(), orderId);
                return new ToolResult("Order cancelled successfully");
            }
            return new ToolResult("This order is not eligible for cancellation");
        } catch (OrderNotFoundException e) {
            return new ToolResult(e.getMessage());
        }
    }

    private UserContext getUserContext(McpMeta meta) {
        return jsonMapper.convertValue(meta.get("userContext"), UserContext.class);
    }
}
