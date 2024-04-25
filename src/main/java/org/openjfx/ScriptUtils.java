package org.openjfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static org.openjfx.ScriptPaths.TEST;


public class ScriptUtils
{

    private static final Logger log = Logger.getLogger(ScriptUtils.class.getName());


    public void setExecutePermissions(String scriptPath){

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

    public void runner(String scriptPath)
    {
        setExecutePermissions(scriptPath);

        try
        {
            // Run the Bash script
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", scriptPath);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Read output and wait for the script to ask for input
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
//                System.out.println(line);
                appendText(line);
            }

        } catch (Exception e)
        {
            throw new RuntimeException("Error running the script", e);
        }
    }

    @FXML
    private TextArea consoleTextArea;

    public void appendText(String text) {
        consoleTextArea.appendText(text + "\n");
    }

    @FXML
    private Button myButton;


    @FXML
    private void handleButtonClick(ActionEvent event) {
        runner(TEST.getPath());

    }
}

