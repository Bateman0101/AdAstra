package boundary.SearchFilamento;

import boundary.messenger;
import control.ControllerFil;
import entity.Segmento;
import exceptions.NoEstremoException;
import exceptions.NoPerimetroException;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

public class ListaSegmenti {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="dataBranch"
    private TableView<Segmento> dataBranch; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="idColumn"
    private TableColumn<Segmento, Integer> idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="typeColumn"
    private TableColumn<Segmento, Character> typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="distanceBtn"
    private Button distanceBtn; // Value injected by FXMLLoader

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
    void distanceBtn() {
        try {
            int idSeg = Integer.parseInt(idSegmento.getText());
            int idF = Integer.parseInt(idFil.getText());
            String sat = satellite.getText();
            ControllerFil src = new ControllerFil();
            double[] dist = src.getDistance(idSeg, idF, sat);
            float distInf = (float) dist[0];
            float distSup = (float) dist[1];
            distanceInf.setText(Float.toString(distInf));
            distanceSup.setText(Float.toString(distSup));
        } catch (NumberFormatException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - calcolo distanza estremi dal contorno","Inserire valori numerici nel campo ID segmento.");
        } catch (NoPerimetroException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - calcolo distanza estremi dal contorno","Perimetro inesitente.");
        } catch (SQLException e){
            messenger msg = new messenger();
            int error = e.getErrorCode();
            msg.messageOne("Errore - ricerca per ellitticità","Si è verificato un errore. Codice errore: " + error + ".");
        } catch (NoEstremoException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - calcolo distanza estremi dal contorno","Il segmento è privo di uno o entrambi gli estremi.");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert distanceBtn != null : "fx:id=\"distanceBtn\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert idFil != null : "fx:id=\"idFil\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert satellite != null : "fx:id=\"satellite\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert idSegmento != null : "fx:id=\"idSegmento\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert distanceInf != null : "fx:id=\"distanceInf\" was not injected: check your FXML file 'lista_segmenti.fxml'.";
        assert distanceSup != null : "fx:id=\"distanceSup\" was not injected: check your FXML file 'lista_segmenti.fxml'.";

    }
}
