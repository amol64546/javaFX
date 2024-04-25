package org.openjfx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {
      Thread springThread = new Thread(() -> SpringApplication.run(SpringBootApp.class, args));

      // Create a thread for running Application.launch
      Thread javaFxThread = new Thread(() -> Application.launch(JavaFxApp.class, args));

      // Start both threads
      springThread.start();
      javaFxThread.start();


    }
}

