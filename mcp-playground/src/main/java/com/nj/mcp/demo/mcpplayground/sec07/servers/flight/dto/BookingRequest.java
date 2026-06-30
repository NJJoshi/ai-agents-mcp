package com.nj.mcp.demo.mcpplayground.sec07.servers.flight.dto;

import org.springframework.ai.mcp.annotation.McpToolParam;

import java.time.LocalDate;

public record BookingRequest(@McpToolParam(description = "Flight Number (e.g. UA202)")
                             String flightNumber,
                             @McpToolParam(description = "Departure Date in ISO format (e.g. 2026-06-30)")
                             LocalDate travelDate,
                             @McpToolParam(description = "Passenger name and address details")
                             Passenger passenger) {
}
