// Keep the main application class in the root property package.
package com.property;

// Import SpringApplication to start Spring Boot.
import org.springframework.boot.SpringApplication;
// Import the annotation for the main Spring Boot application.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Mark this class as the Spring Boot application.
@SpringBootApplication
// Start the application from this class.
public class PropertyBillingApplication {
    public static void main(String[] args) {
        SpringApplication.run(PropertyBillingApplication.class, args);
    }
}
