package boundary.FindStelleRegioneFilamento;

import boundary.Main;
import control.FilamentoHandler;
import control.SearchStars;
import entity.Stella;
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

@SuppressWarnings("ALL")
public class FindStelleRegioneFilamentoUI {

    @FXML
    private TextField lato1;
    @FXML
    private TextField lato2;
    @FXML
    private TextField centroideLat;
    @FXML
    private TextField centroideLon;
    @FXML
    private TextField preInTf;
    @FXML
    private TextField preEsTf;
    @FXML
    private TextField proInTf;
    @FXML
    private TextField proEsTf;
    @FXML
    private TextField formInTf;
    @FXML
    private TextField formEsTf;
    @FXML
    private TextField unInTf;
    @FXML
    private TextField unEsTf;
    @FXML
    private Button searchBtn;
    @FXML
    private Button homeBtn;

    private ObservableList<Stella> listStelle = FXCollections.observableArrayList();

    @FXML
    public void initialize() {


    }

    public void onSearchPressed() {

        listStelle.removeAll(listStelle);
        SearchStars searchStars = new SearchStars();
        Double[] tipiStelle = new Double[8];
        Double[] centroide = new Double[2];

        double latoA = Double.parseDouble(lato1.getText());
        double latoB = Double.parseDouble(lato2.getText());
        double centroLat = Double.parseDouble(centroideLat.getText());
        double centroLon = Double.parseDouble(centroideLon.getText());

        centroide[0] = centroLat;
        centroide[1] = centroLon;

        tipiStelle = searchStars.searchStarsRect(latoA, latoB, centroide);

        preInTf.setText(Double.toString(tipiStelle[0]));
        proInTf.setText(Double.toString(tipiStelle[1]));
        formInTf.setText(Double.toString(tipiStelle[2]));
        unInTf.setText(Double.toString(tipiStelle[3]));
        preEsTf.setText(Double.toString(tipiStelle[4]));
        proEsTf.setText(Double.toString(tipiStelle[5]));
        formEsTf.setText(Double.toString(tipiStelle[6]));
        unEsTf.setText(Double.toString(tipiStelle[7]));



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
