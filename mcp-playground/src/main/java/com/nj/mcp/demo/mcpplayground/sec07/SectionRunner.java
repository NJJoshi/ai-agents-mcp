package com.nj.mcp.demo.mcpplayground.sec07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class SectionRunner {

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.host")
	static class Host {

		static void main(String[] args) {
			SpringApplication.run(Host.class, "--section=sec07", "--config=host");
		}

	}

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.servers.city")
	static class CityService {

		static void main(String[] args) {
			SpringApplication.run(CityService.class,  "--section=sec07", "--config=city-service");
		}

	}

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.servers.flight")
	static class FlightService {

		static void main(String[] args) {
			SpringApplication.run(FlightService.class,  "--section=sec07", "--config=flight-service");
		}

	}

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.servers.user")
	static class UserService {

		static void main(String[] args) {
			SpringApplication.run(UserService.class,  "--section=sec07", "--config=user-service");
		}

	}

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.servers.weather")
	static class WeatherService {

		static void main(String[] args) {
			SpringApplication.run(WeatherService.class,  "--section=sec07", "--config=weather-service");
		}

	}

}
