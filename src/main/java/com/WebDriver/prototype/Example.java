package com.WebDriver.prototype;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RestController
@EnableAutoConfiguration
public class Example {

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	Map home(@RequestParam(value = "img", required = false) String img) {

		Map result = new HashMap();
		try {
			// WebDriver ->get google image --> "avacado
			String params = "f756b834-0ad5-477e-bdc3-25d5d8e9c6bf";
			params = img;
			String imgUrl = "https://firebasestorage.googleapis.com/v0/b/trueorgs.appspot.com/o/test.jpg?alt=media&token="

					+ params;
			//imgUrl = "https://www.medicalnewstoday.com/content/images/headlines/270/270406/avocado-dissection.jpg";
			String gurl = "https://images.google.com/";
			System.out.println("IMAGE URL: " + imgUrl);
			String purl = "http://192.168.33.10/php/form.html";
			// processRequest(purl,imgUrl);

			// ?sourceImg={https://firebasestorage.googleapis.com/v0/b/trueorgs.appspot.com/o/test.jpg?alt=media&token=0ea99427-a248-4ff2-acd9-a2ed6984deec}

			
			result = Collections.singletonMap("response", fetchWebDriverResponseString(imgUrl));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String fetchWebDriverResponseString(String sourceImage) {
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		firefoxBinary.addCommandLineOptions("--headless");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setBinary(firefoxBinary);
		FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
		System.out.println("Web Driver started");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://images.google.com/");
		WebElement element = driver.findElement(By.className("S3Wjs"));
		element.click();
		element = driver.findElement(By.id("qbui"));
		element.sendKeys(sourceImage);
		element.submit();
		element = driver.findElement(By.className("fKDtNb"));
		String resp = null;
		try {
			resp = "title: "+element.getText();
			System.out.println("response string: " + resp);

			driver.navigate().to("https://www.google.com/webhp");
			element = driver.findElement(By.name("q"));
			element.sendKeys(resp + " nutritional facts");
			element.submit();
			element = driver.findElement(By.className("i4J0ge"));

			System.out.println(element.getText());
			
			resp+="\n body: "+element.getText();

		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
		return resp;
	}

	void processRequest(String url, String img) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", "first.last@example.com");
		// map.add("image_url", img);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		System.out.print(response);
	}

}
