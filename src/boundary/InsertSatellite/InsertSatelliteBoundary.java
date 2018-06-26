package boundary.InsertSatellite;

import boundary.Main;
import control.SatelliteHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertSatelliteBoundary {

    @FXML
    private TextField nome;
    @FXML
    private TextField agenzia;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;


    @FXML
    public void initialize() {

    }

    public void onAdd(){

        SatelliteHandler ctr = new SatelliteHandler();
        ctr.insertSatellite(nome.getText(), startDate.getValue(), endDate.getValue(), agenzia.getText());


    }

    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) agenzia.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
