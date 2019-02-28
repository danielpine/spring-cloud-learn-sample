package com.example.reservationclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.hateoas.Resources;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@IntegrationComponentScan
@EnableBinding(ReservationChannels.class)
@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationClientApplication.class, args);
	}

}

interface ReservationChannels {
	@Output
	MessageChannel output(); // orders, customers, products
}

@FeignClient("reservation-service")
interface ReservationReader {

	@GetMapping(value = "reservations")
	Resources<Reservation> read();

}

@MessagingGateway
interface ReservationWriter {

	@Gateway(requestChannel = "output")
	void write(String rn);

}

class Reservation {
	private String reservationName;

	public String getReservationName() {
		return reservationName;
	}
}

@RestController
@RequestMapping("/reservations")
class ReservationApiGateaWay {
	
	private static Logger logger = LoggerFactory.getLogger(ReservationApiGateaWay.class);

	private final ReservationReader reservationReader;

	private final ReservationWriter reservationWriter;

	@Autowired
	public ReservationApiGateaWay(ReservationReader reservationReader, ReservationWriter reservationWriter) {
		this.reservationReader = reservationReader;
		this.reservationWriter = reservationWriter;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void write(@RequestBody Reservation rs) {
		logger.info("reservation-client calling reservation-service!");
		this.reservationWriter.write(rs.getReservationName());
	}

	public Collection<String> fallback() {
		return new ArrayList<>();
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping(value = "/names")
	public Collection<String> names() {
		logger.info("reservation-client calling reservation-service!");
		return this.reservationReader.read().getContent().stream().map(Reservation::getReservationName)
				.collect(Collectors.toList());
	}
}
