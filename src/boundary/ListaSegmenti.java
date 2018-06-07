package boundary;

import control.ControllerFil;
import entity.SegmentoAdam;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

public class ListaSegmenti {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="dataBranch"
    private TableView<SegmentoAdam> dataBranch; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idColumn"
    private TableColumn<SegmentoAdam, Integer> idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="typeColumn"
    private TableColumn<SegmentoAdam, Character> typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="distanceBtn"
    private Button distanceBtn; // Value injected by FXMLLoader

    @FXML // fx:id="nomeFil"
    private TextField nomeFil; // Value injected by FXMLLoader

    @FXML // fx:id="idFil"
    private TextField idFil; // Value injected by FXMLLoader

    @FXML // fx:id="satellite"
    private TextField satellite; // Value injected by FXMLLoader

    @FXML // fx:id="idSegmento"
    private TextField idSegmento; // Value injected by FXMLLoader

    @FXML // fx:id="distanceInf"
    private Text distanceInf; // Value injected by FXMLLoader

    @FXML // fx:id="distanceSup"
    private Text distanceSup; // Value injected by FXMLLoader

    @FXML
    void distanceBtn(ActionEvent event) {
        int idSeg = Integer.parseInt(idSegmento.getText());
        int idF = Integer.parseInt(idFil.getText());
        String sat = satellite.getText();
        ControllerFil src = new ControllerFil();
        double[] dist = src.getDistance(idSeg, idF, sat);
        float distInf = (float) dist[0];
        float distSup = (float) dist[1];
        distanceInf.setText(Float.toString(distInf));
        distanceSup.setText(Float.toString(distSup));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert distanceBtn != null : "fx:id=\"distanceBtn\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert nomeFil != null : "fx:id=\"nomeFil\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert idFil != null : "fx:id=\"idFil\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert satellite != null : "fx:id=\"satellite\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert idSegmento != null : "fx:id=\"idSegmento\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert distanceInf != null : "fx:id=\"distanceInf\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert distanceSup != null : "fx:id=\"distanceSup\" was not injected: check your FXML file 'lista_segmenti.fxml'.";

    }
}
