package com.example.myproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	
	
	@CrossOrigin(origins = "http://spring.local:8080/")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	Map home() {

		Map result = null;
		// WebDriver ->get google image --> "avacado

		String imgUrl = "https://firebasestorage.googleapis.com/v0/b/trueorgs.appspot.com/o/test.jpg?alt=media&token=0ea99427-a248-4ff2-acd9-a2ed6984deec";
		String gurl = "https://images.google.com/";
		String purl = "http://192.168.33.10/php/form.html";
		processRequest(purl,imgUrl);

		result = Collections.singletonMap("response", "avacado");

		return result;
	}

	void processRequest(String url,String img) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("email", "first.last@example.com");
		//map.add("image_url", img);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		System.out.print(response);
	}

}
