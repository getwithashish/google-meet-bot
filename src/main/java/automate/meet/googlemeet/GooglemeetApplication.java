package automate.meet.googlemeet;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GooglemeetApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(GooglemeetApplication.class, args);
	}

}
