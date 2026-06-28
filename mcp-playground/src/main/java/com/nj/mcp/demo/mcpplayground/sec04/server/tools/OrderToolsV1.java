package com.nj.mcp.demo.mcpplayground.sec04.server.tools;

import com.nj.mcp.demo.mcpplayground.sec04.server.dto.Order;
import com.nj.mcp.demo.mcpplayground.sec04.server.service.OrderService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "version", havingValue = "v1")
public class OrderToolsV1 {
    private final OrderService orderService;

    public OrderToolsV1(OrderService orderService) {
        this.orderService = orderService;
    }

    @McpTool(description = "List all orders")
    public List<Order> listOrders() {
        return this.orderService.listOrders();
    }

    @McpTool(description = "Check if order is eligible for cancellation")
    public boolean isOrderCancellable(int orderId){
        return this.orderService.isOrderCancellable(orderId);
    }

    @McpTool(description = "Cancel an order")
    public void cancelOrder(int orderId){
        this.orderService.cancelOrder(orderId);
    }
}
