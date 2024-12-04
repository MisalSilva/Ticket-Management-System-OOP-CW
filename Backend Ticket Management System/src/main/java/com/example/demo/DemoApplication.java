package com.example.demo;

//import Configuration.AppConfig;
//import Model.ConfigParameters;
//import MultiThreading.Customer;
//import MultiThreading.TicketPool;
//import MultiThreading.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

//		AppConfig appConfig = new AppConfig();
//		ConfigParameters config = appConfig.getConfigFormCLI();

//		TicketPool ticketPool = new TicketPool();
//		new Thread(new Vendor(ticketPool)).start();
//		new Thread(new Customer(ticketPool)).start();
	}

}
