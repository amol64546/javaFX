package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {

    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) {
        Stage stage = event.getStage();

      Parent root = null;
      try {
        root = FXMLLoader.load(getClass().getResource("scene.fxml"));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }


}



