package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.util.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Include Auto-Configuration, Component Scan e Spring Boot Configuration
@SpringBootApplication
public class IdsProject2324Application {

    public static void main(String[] args) {
        SpringApplication.run(IdsProject2324Application.class, args);

        /*
        Controller controller = new Controller();
        controller.initialize();
        */
    }
}