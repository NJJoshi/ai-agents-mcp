package com.nj.mcp.demo.mcpplayground.sec04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class SectionRunnerApplication {

    @SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.host")
    static class Host {
        static void main(String[] args) {
            SpringApplication.run(Host.class, "--section=sec04","--config=host");
        }
    }

    @SpringBootApplication(scanBasePackages = "com.nj.mcp.demo.mcpplayground.${section}.server")
    static class Server {
        static void main(String[] args) {
            SpringApplication.run(Server.class, "--section=sec04","--config=server", "--version=v1");
        }
    }


}
