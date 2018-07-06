package boundary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerMessagePage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootPane1"
    private AnchorPane rootPane1; // Value injected by FXMLLoader

    @FXML // fx:id="textArea1"
    private TextArea textArea1; // Value injected by FXMLLoader

    @FXML // fx:id="okButton1"
    private Button okButton1; // Value injected by FXMLLoader

    @FXML
    void okButton1() {
        ((Stage) okButton1.getScene().getWindow()).close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert rootPane1 != null : "fx:id=\"rootPane1\" was not injected: check your FXML file 'messagePage.fxml'.";
        assert textArea1 != null : "fx:id=\"textArea1\" was not injected: check your FXML file 'messagePage.fxml'.";
        assert okButton1 != null : "fx:id=\"okButton1\" was not injected: check your FXML file 'messagePage.fxml'.";

    }
}
