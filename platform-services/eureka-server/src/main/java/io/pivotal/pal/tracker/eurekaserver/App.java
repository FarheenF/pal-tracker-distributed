/**
 * 
 */
package io.pivotal.pal.tracker.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Farheen
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class App {

	public static void main(String args[]) {

		SpringApplication.run(App.class, args);
	}

}
