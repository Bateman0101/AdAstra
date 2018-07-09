package boundary.FindInRegion;

import boundary.Main;
import control.FilamentoHandler;
import entity.Filamento;
import exceptions.NoFilamentoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FindInRegionBoundary {

    @FXML
    private TextField latEntry;
    @FXML
    private TextField lonEntry;
    @FXML
    private TextField radiusEntry;
    @FXML
    private ComboBox<String> listRegion;
    @FXML
    private TableView<Filamento> tableEntry;
    @FXML
    private TableColumn<Filamento, Integer> filId;
    @FXML
    private TableColumn<Filamento, String> filNome;
    @FXML
    private TableColumn<Filamento, String> filSatellite;
    @FXML
   private TableColumn<Filamento, String> filFlusso;
     @FXML
    private TableColumn<Filamento, String> filDensita;
    @FXML
    private TableColumn<Filamento, Double> filEllitticita;
    @FXML
    private TableColumn<Filamento, Double> filContrasto;
    @FXML
     private TableColumn<Filamento, Double> filTemperatura;

    private ObservableList<Filamento> listFilamento = FXCollections.observableArrayList();

    @FXML
    public void initialize() {


        ObservableList<String> items = listRegion.getItems();
        String[] obj = {"Cerchio", "Quadrato"};
        int len = obj.length;
        int i = 0;
        while (i < len) {
            items.add(obj[i]);
            i++;
        }

        filId.setCellValueFactory(new PropertyValueFactory<Filamento, Integer>("id"));
        filNome.setCellValueFactory(new PropertyValueFactory<Filamento, String>("nome"));
        filSatellite.setCellValueFactory(new PropertyValueFactory<Filamento, String>("satellite"));
        filFlusso.setCellValueFactory(new PropertyValueFactory<Filamento, String>("flusso"));
        filDensita.setCellValueFactory(new PropertyValueFactory<Filamento, String>("densita"));
        filEllitticita.setCellValueFactory(new PropertyValueFactory<Filamento, Double>("ellitticita"));
        filContrasto.setCellValueFactory(new PropertyValueFactory<Filamento, Double>("contrasto"));
        filTemperatura.setCellValueFactory(new PropertyValueFactory<Filamento, Double>("temperatura"));

    }

    public void onSubmitPressed() {

        listFilamento.removeAll(listFilamento);

        FilamentoHandler ctrl = new FilamentoHandler();

        try {

            String region = listRegion.getValue();
            double radius = Double.parseDouble(radiusEntry.getText());
            double lat = Double.parseDouble(latEntry.getText());
            double lon = Double.parseDouble(lonEntry.getText());

            if (Double.toString(radius).length() == 0 || Double.toString(radius).length() == 0
                    || Double.toString(lon).length() == 0){

                getAlert("inserire dati corretti");
                return;
            }

            switch (region) {

                case "Cerchio":

                    try {

                        ArrayList<Filamento> listFil = ctrl.isInsideCircle(lat, lon, radius);
                        int len = listFil.size();
                        int i = 0;
                        while (i < len) {
                            listFilamento.add(listFil.get(i));
                            i++;
                        }

                        tableEntry.setItems(listFilamento);
                    } catch (NoFilamentoException e) {

                        getAlert("Filamento non presente nel sistema!");


                    }

                case "Quadrato":

                    try {

                        ArrayList<Filamento> listFil2 = ctrl.isInsideSquare(lat, lon, radius);
                        int len2 = listFil2.size();
                        int j = 0;
                        while (j < len2) {
                            listFilamento.add(listFil2.get(j));
                            j++;
                        }

                        tableEntry.setItems(listFilamento);
                    } catch (NoFilamentoException e) {

                        getAlert("Filamento non presente nel sistema!");
                    }
                default:
                    break;

            }
        } catch (NumberFormatException e) {

            getAlert("Inserire dati corretti");
        }
    }

    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) latEntry.getScene().getWindow();
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
