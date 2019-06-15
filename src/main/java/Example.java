import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RestController
@EnableAutoConfiguration
public class Example {

	@CrossOrigin(origins = "http://localhost:8081")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	Map home() {

		Map result = null;
		// WebDriver ->get google image --> "avacado

		result = Collections.singletonMap("response", "avacado");

		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(Example.class, args);
	}

}
