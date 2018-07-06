package boundary.PosizioneStellaSpinaDorsale;

import boundary.Main;
import control.FilamentoHandler;
import control.SearchObjectsInFilamento;
import entity.Stella;
import entity.StellaDistanza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
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

        int id = Integer.parseInt(idTf.getText());
        String satellite = satTf.getText();

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
