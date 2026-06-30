package com.nj.mcp.demo.mcpplayground.sec07.servers.city.dto;

import java.util.List;

public record Recommendation(List<String> restaurants,
                             List<String> sightseeing) {
}
