package com.nj.mcp.demo.mcpplayground.sec10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class SectionRunner {

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.host")
	public static class Host {

		static void main(String[] args) {
			SpringApplication.run(Host.class, "--section=sec10", "--config=host");
		}

	}

	@SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.server")
	public static class Server {

		static void main(String[] args) {
			SpringApplication.run(Server.class,  "--section=sec10", "--config=server");
		}

	}
}
