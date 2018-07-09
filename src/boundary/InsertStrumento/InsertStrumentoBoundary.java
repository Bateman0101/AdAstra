package boundary.InsertStrumento;

import boundary.Main;
import control.SatelliteHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InsertStrumentoBoundary {

    @FXML
    private TextField name;
    @FXML
    private TextField bande;
    @FXML
    private TextField satelliteEntry;
    @FXML
    private Button apply;


    @FXML
    public void initialize() {


    }

    public void onApply(){

        String nameS = name.getText();
        String bandeS = bande.getText();

        String satelliteS = satelliteEntry.getText();

        if (nameS == null || bandeS == null || satelliteS == null){

            getAlert("Inserire tutti i dati.");
            return;
        }

        SatelliteHandler ctrl = new SatelliteHandler();

        ctrl.insertStrumento(nameS, satelliteS, bandeS);

    }

    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) apply.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAlert(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
        return;
    }

}
