package boundary;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class messenger {
    public void messageOne(String title, String message) {
        try {
            URL url = getClass().getResource("messagePage.fxml");
            Stage stage = new Stage();
            AnchorPane anchor = FXMLLoader.load(url);
            TextArea text = (TextArea) anchor.lookup("#textArea1");
            text.setText(message);
            Scene scene = new Scene(anchor);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
