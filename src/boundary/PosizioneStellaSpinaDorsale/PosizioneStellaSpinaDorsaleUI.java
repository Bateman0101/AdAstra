package boundary.PosizioneStellaSpinaDorsale;

import boundary.Main;
import control.SearchObjectsInFilamento;
import entity.StellaDistanza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Vector;

@SuppressWarnings("ALL")
public class PosizioneStellaSpinaDorsaleUI {

    @FXML
    private Button homeBtn;
    @FXML
    private TextField idTf;
    @FXML
    private TextField satTf;
    @FXML
    private TableView<StellaDistanza> tableView;
    @FXML
    private TableColumn<StellaDistanza, Integer> idTc;
    @FXML
    private TableColumn<StellaDistanza, String> nomeTc;
    @FXML
    private TableColumn<StellaDistanza, String> tipoTc;
    @FXML
    private TableColumn<StellaDistanza, Double> flussoTc;
    @FXML
    private TableColumn<StellaDistanza, Double> latTc;
    @FXML
    private TableColumn<StellaDistanza, Double> lonTc;
    @FXML
    private TableColumn<StellaDistanza, Double> disTc;

    private ObservableList<StellaDistanza> listStella = FXCollections.observableArrayList();


    @FXML
    public void initialize() {

        idTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, Integer>("id"));
        nomeTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, String>("nome"));
        tipoTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, String>("tipo"));
        flussoTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, Double>("flusso"));
        latTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, Double>("latitudine"));
        lonTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, Double>("longitudine"));
        disTc.setCellValueFactory(new PropertyValueFactory<StellaDistanza, Double>("distanza"));

    }

    public void onSubmitPressed() {

        listStella.removeAll(listStella);

        try{
            int id = Integer.parseInt(idTf.getText());
            String satellite = satTf.getText();
            if(!satellite.equals("Herschel") && !satellite.equals("Spitzer")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Inserisci un satellite valido");
                alert.showAndWait();
            }else {
                SearchObjectsInFilamento searchO = new SearchObjectsInFilamento();
                Vector<StellaDistanza> vec = searchO.positionStella(id, satellite);
                int len = vec.size();
                int i = 0;
                while (i < len) {
                    listStella.add(vec.get(i));
                    i++;

                }

                tableView.setItems(listStella);
            }



        }catch (NumberFormatException n){
            n.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un formato ID valido");
            alert.showAndWait();
        }


        /*
        Vector<StellaDistanza> vec = new Vector<>();
        StellaDistanza s1 = new StellaDistanza(1, "Stella1", "A",
                25.2, "BB", "CC", 20.00, 20.00, 53);
        StellaDistanza s2 = new StellaDistanza(1, "Stella2", "A",
                25.2, "BB", "CC", 20.00, 20.00, 786);
        StellaDistanza s3 = new StellaDistanza(1, "Stella3", "A",
                25.2, "BB", "CC", 20.00, 20.00, 876);
        StellaDistanza s4 = new StellaDistanza(1, "Stella4", "A",
                25.2, "BB", "CC", 20.00, 20.00, 75);
        StellaDistanza s5 = new StellaDistanza(1, "Stella5", "A",
                25.2, "BB", "CC", 20.00, 20.00, 87);
        vec.add(s1);
        vec.add(s2);
        vec.add(s3);
        vec.add(s4);
        vec.add(s1);
        vec.add(s1);
        vec.add(s2);
        vec.add(s4);
        vec.add(s2);
        vec.add(s3);
        vec.add(s5);
        */



    }



    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) homeBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
