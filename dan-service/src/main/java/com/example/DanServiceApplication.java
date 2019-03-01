package com.example;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(ReservationChannels.class)
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class DanServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DanServiceApplication.class, args);
	}
}

@FeignClient("web-service")
interface WebServiceCaller {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getGreeting();
}

@RestController
@RefreshScope
class MessageRestController {
	private final String value;
	private final WebServiceCaller webCaller;
	@Autowired
	public MessageRestController(@Value("${message}") String value, WebServiceCaller webCaller) {
		this.value = value;
		this.webCaller = webCaller;
	}
	@GetMapping(value = "msg")
	String read() {
		return this.value + " | " + this.webCaller.getGreeting();
	}
}

interface ReservationChannels {
	@Input
	SubscribableChannel input();
}

@Component
class SampleDataCLR implements CommandLineRunner {
	private final ReservationRepository rr;

	@Autowired
	public SampleDataCLR(ReservationRepository rr) {
		this.rr = rr;
	}

	@Override
	public void run(String... args) throws Exception {
		Stream.of("Daniel", "Tom", "Vagally", "Peqi", "Begy").forEach(name -> rr.save(new Reservation(name)));
		rr.findAll().forEach(System.out::println);
	}
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

@MessageEndpoint
class ReservationProcessor {
	private final ReservationRepository reservationRepository;
	Log LOG = LogFactory.getLog(this.getClass());

	public ReservationProcessor(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@ServiceActivator(inputChannel = "input")
	public void onNewReservation(String reservationName) {
//		LOG.info("Input ReservationName:" + reservationName);
		this.reservationRepository.save(new Reservation(reservationName));
	}
}

@RepositoryRestResource
interface BookRepository extends JpaRepository<Book, Long> {
}

@Entity
class Book {
	public Book() {
	}

	public Book(String bookName) {
		this.bookName = bookName;
	}

	@Id
	@GeneratedValue
	private Long id;
	private String bookName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + "]";
	}
}

@Entity
class Reservation {
	public Reservation() {
	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}
}
