package org.openjfx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
        // Create a thread for running SpringApplication.run
      Thread springThread = new Thread(() -> SpringApplication.run(SpringBootApp.class, args));
      springThread.start();

      Application.launch(JavaFxApp.class, args);


    }
}

