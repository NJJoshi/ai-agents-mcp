package com.nj.mcp.demo.mcpplayground.sec04.server.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Order id %d is not found";

    public OrderNotFoundException(Integer id) {
        super(String.format(MESSAGE, id));
    }
}
