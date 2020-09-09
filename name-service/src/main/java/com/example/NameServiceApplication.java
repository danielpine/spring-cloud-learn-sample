package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(NameProperties.class)
public class NameServiceApplication {
  private static String port = "";
  private static String name = "diy-name-service";

  public static void main(String[] args) {
    System.out.println(args);
    if (args.length == 1) {
      NameServiceApplication.port = "8085";
      args = new String[] {args[0], "--server.port=" + NameServiceApplication.port,
          "--spring.application.name=" + NameServiceApplication.name};
    }
    SpringApplication.run(NameServiceApplication.class, args);
  }

}
