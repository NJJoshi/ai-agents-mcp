package com.nj.mcp.demo.mcpplayground.sec05.server.dto;

import java.time.LocalDate;

public record Order(Integer orderId,
                    String description,
                    Integer price,
                    LocalDate orderDate) {
}
