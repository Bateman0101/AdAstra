package boundary.InsertStrumento;

import boundary.Main;
import control.SatelliteHandler;
import javafx.fxml.FXML;
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
//        ArrayList<Double> list = new ArrayList<>();
//
//        String[] bandeD = bandeS.split("/");
//
//        for (int i = 0; i<bandeD.length; i++) {
//
//            list.add(Double.parseDouble(bandeD[i]));
//
//        }

//        Double [] listB = list.toArray(new Double[list.size()]);

        String satelliteS = satelliteEntry.getText();

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

}
