package boundary.FindStelleInFilamento;

import boundary.Main;
import control.FilamentoHandler;
import entity.Filamento;
import entity.Stella;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FindStelleInFilamentoBoundary {

    @FXML
    private TextField idEntry;
    @FXML
    private TextField satelliteEntry;
    @FXML
    private TableView<Stella> table;
    @FXML
    private TableColumn<Stella, Integer> idS;
    @FXML
    private TableColumn<Stella, String> nomeS;
    @FXML
    private TableColumn<Stella, String> tipoS;
    @FXML
    private TableColumn<Stella, Double> flussoS;
    @FXML
    private TableColumn<Stella, Double> latS;
    @FXML
    private TableColumn<Stella, Double> lonS;

    private ObservableList<Stella> listStella = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        idS.setCellValueFactory(new PropertyValueFactory<Stella, Integer>("id"));
        nomeS.setCellValueFactory(new PropertyValueFactory<Stella, String>("nome"));
        tipoS.setCellValueFactory(new PropertyValueFactory<Stella, String>("tipo"));
        flussoS.setCellValueFactory(new PropertyValueFactory<Stella, Double>("flusso"));
        latS.setCellValueFactory(new PropertyValueFactory<Stella, Double>("latitudine"));
        lonS.setCellValueFactory(new PropertyValueFactory<Stella, Double>("longitudine"));
    }

    public void onSubmitPressed() {

        listStella.removeAll(listStella);

        int id = Integer.parseInt(idEntry.getText());
        String satellite = satelliteEntry.getText();

        FilamentoHandler ctrl = new FilamentoHandler();

        ArrayList<Stella> listS = ctrl.stelleInFilamento(id, satellite);

        int len = listS.size();
        int i = 0;
        while (i < len) {
            listStella.add(listS.get(i));
            i++;
        }

        table.setItems(listStella);



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


}
