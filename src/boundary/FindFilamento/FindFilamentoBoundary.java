package boundary.FindFilamento;

import boundary.Main;
import control.FilamentoHandler;
import entity.Punto;
import exceptions.NoFilamentoException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FindFilamentoBoundary {

    @FXML
    private TextField idEntry;
    @FXML
    private TextField satelliteEntry;
    @FXML
    private ListView<Integer> list;
    @FXML
    private TextField latEntry;
    @FXML
    private TextField lonEntry;
    @FXML
    private TextField extensionEntry;


    @FXML
    public void initialize() {

    }

    public void onButtonPressed(){

        list.getItems().clear();

        int id = Integer.parseInt(idEntry.getText());
        String satellite = satelliteEntry.getText();

        FilamentoHandler ctrl = new FilamentoHandler();

        try {

            Punto p = ctrl.computeCentroid(id, satellite);

            double extension = ctrl.computeExtension(id, satellite);

            ArrayList<Integer> listSeg = ctrl.findSegments(id, satellite);

            latEntry.setText(Double.toString(p.getLatitudine()));
            lonEntry.setText(Double.toString(p.getLongitudine()));

            extensionEntry.setText(Double.toString(extension));

            for (int i = 0; i<listSeg.size(); i++)
            {
                list.getItems().add(listSeg.get(i));
            }

        }catch (NoFilamentoException e) {

            getAlert("Filamento non presente nel sistema.");

            }

    }

    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) idEntry.getScene().getWindow();
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
