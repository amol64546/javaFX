package org.openjfx;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import static org.openjfx.ScriptPaths.INSTALL_REDIS;
import static org.openjfx.ScriptPaths.TEST;


public class BashRunner {

  private static final Logger log = Logger.getLogger(BashRunner.class.getName());
  @FXML private TextArea consoleTextArea;

  public void setExecutePermissions(String scriptPath) {

    try {
      Process chmodProcess = Runtime.getRuntime().exec("chmod +x " + scriptPath);
      // Wait for the chmod process to finish
      int chmodExitCode = chmodProcess.waitFor();
      // Check if chmod executed successfully
      if (chmodExitCode != 0) {
        log.severe("Error setting execute permissions on the script file");
      }
    } catch (InterruptedException | IOException e) {
      throw new RuntimeException("Error setting execute permissions on the script file", e);
    }

  }

  public void apacheRunner(String bashScript) {
    setExecutePermissions(bashScript);
    try {
      CommandLine cmdLine = CommandLine.parse(bashScript);
      DefaultExecutor executor = new DefaultExecutor();

      // Create a ByteArrayOutputStream to capture the output
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
      executor.setStreamHandler(streamHandler);

      executor.setExitValue(0);  // Optionally set the exit value

      // Execute the command in a background thread
      Thread executorThread = new Thread(() -> {
        try {
          executor.execute(cmdLine);

          // Read the output line by line
          try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray())))) {
            String line;
            while ((line = reader.readLine()) != null) {
              final String outputLine = line;
              if (outputLine.startsWith("echo")) { // Check if line starts with "echo"
                String finalLine = outputLine;
                Platform.runLater(() -> appendText(finalLine.substring(5)));
                System.out.println("Output of the script:\n" + outputLine);

              }
            }
          }
        } catch (ExecuteException e) {
          // Handle execution errors
          e.printStackTrace();
        } catch (IOException e) {
          // Handle IO errors
          e.printStackTrace();
        }
      });
      executorThread.start();
    } catch (Exception e) {
      // Handle any other exceptions
      e.printStackTrace();
    }
  }

  public void runner(String scriptPath) {
    setExecutePermissions(scriptPath);


    try {

      // Execute the command in a background thread
      ProcessBuilder pb = new ProcessBuilder("bash", "-c", scriptPath);
      pb.redirectErrorStream(true);
      Process process = pb.start();


      // Start a separate thread to continuously read and print the output
      Thread outputReaderThread = new Thread(() -> {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
          try {
            if (!((line = reader.readLine()) != null)) break;
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          if (line.startsWith("echo")) { // Check if line starts with "echo"
            String finalLine = line;
            Platform.runLater(() -> appendText(finalLine.substring(5)));
          }
        }

      });



      outputReaderThread.start();
    } catch (Exception e) {
      // Handle any other exceptions
      e.printStackTrace();
    }

  }

  public void appendText(String text) {
    System.out.println("calling appendText");
    consoleTextArea.appendText(text + "\n");
  }

  @FXML
  private void runTestScript(ActionEvent event) {
    runner(TEST.getPath());
  }

  @FXML
  private void installRedis(ActionEvent event) {
    runner(INSTALL_REDIS.getPath());
  }

  @FXML
  private void handleClearButtonClick(ActionEvent event) {
    consoleTextArea.clear();
  }
}

