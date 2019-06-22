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
import org.springframework.web.bind.annotation.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RestController
@EnableAutoConfiguration
public class Example {

	
	@RestController
	class SecuredServerController{
	     
	    @RequestMapping("/secured")
	    public String secured(){
	        System.out.println("Inside secured()");
	        return "Hello user !!! : " + new Date();
	    }
	}
	
	@CrossOrigin(origins = "http://spring.local:8080/")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	Map home() {

		Map result = null;
		// WebDriver ->get google image --> "avacado

		result = Collections.singletonMap("response", "avacado");

		return result;
	}
	

	
	
}
