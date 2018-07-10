package boundary.SearchFilamento;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import boundary.messenger;
import control.ControllerFil;
import entity.Segmento;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import entity.Filamento;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ricerca {

    private List<Filamento> list = new ArrayList<>();
    private int num;
    private int i;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Inf"
    private TextField Inf; // Value injected by FXMLLoader

    @FXML // fx:id="Sup"
    private TextField Sup; // Value injected by FXMLLoader

    @FXML // fx:id="search"
    private Button search; // Value injected by FXMLLoader

    @FXML // fx:id="data"
    private TableView<Filamento> data; // Value injected by FXMLLoader

    @FXML // fx:id="nome"
    private TableColumn<Filamento, String> nome; // Value injected by FXMLLoader

    @FXML // fx:id="id"
    private TableColumn<Filamento, Integer> id; // Value injected by FXMLLoader

    @FXML // fx:id="strumCol"
    private TableColumn<Filamento, String> strumCol; // Value injected by FXMLLoader

    @FXML // fx:id="satCol"
    private TableColumn<Filamento, String> satCol; // Value injected by FXMLLoader

    @FXML // fx:id="fluxCol"
    private TableColumn<Filamento, String> fluxCol; // Value injected by FXMLLoader

    @FXML // fx:id="denCol"
    private TableColumn<Filamento, String> denCol; // Value injected by FXMLLoader

    @FXML // fx:id="elliptCol"
    private TableColumn<Filamento, Double> elliptCol; // Value injected by FXMLLoader

    @FXML // fx:id="contrastCol"
    private TableColumn<Filamento, Double> contrastCol; // Value injected by FXMLLoader

    @FXML // fx:id="tempCol"
    private TableColumn<Filamento, Double> tempCol; // Value injected by FXMLLoader

    @FXML // fx:id="next"
    private Button next; // Value injected by FXMLLoader

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="numElements"
    private Text numElements; // Value injected by FXMLLoader

    @FXML // fx:id="page"
    private Text page; // Value injected by FXMLLoader

    @FXML // fx:id="sat"
    private TextField sat; // Value injected by FXMLLoader

    @FXML // fx:id="idFil"
    private TextField idFil; // Value injected by FXMLLoader

    @FXML // fx:id="searchBranch"
    private Button searchBranch; // Value injected by FXMLLoader

    @FXML // fx:id="lum"
    private TextField lum; // Value injected by FXMLLoader

    @FXML // fx:id="ellInf"
    private TextField ellInf; // Value injected by FXMLLoader

    @FXML // fx:id="ellSup"
    private TextField ellSup; // Value injected by FXMLLoader

    @FXML // fx:id="searchLum"
    private Button searchLum; // Value injected by FXMLLoader

    @FXML // fx:id="searchEll"
    private Button searchEll; // Value injected by FXMLLoader

    @FXML // fx:id="frazione"
    private Text frazione; // Value injected by FXMLLoader

    @FXML
    void back() {
        if(i>1){
            num--;
            while((num%20) != 0) num--;
            int limit = num;
            num = num - 20;
            i--;
            int p = list.size()/20;
            if(list.size()%20 != 0) p++;
            data.getItems().clear();
            Filamento fil;
            while(num < list.size() && num < limit) {
                fil = list.get(num);
                data.getItems().add(fil);
                num++;
            }
            page.setText("pagina " + i + " di " + p);
        }
    }

    @FXML
    void next() {
        if(num < list.size()){
            int limit = num + 20;
            int p = list.size()/20;
            i++;
            if(list.size()%20 != 0) p++;
            data.getItems().clear();
            Filamento fil;
            while(num < list.size() && num < limit) {
                fil = list.get(num);
                data.getItems().add(fil);
                num++;
            }
            page.setText("pagina " + i + " di " + p);
        }
    }

    @FXML
    void search() {
        try {
            data.getItems().clear();
            numElements.setText("");
            page.setText("");
            i=0;
            num=0;
            int inf = Integer.parseInt(Inf.getText());
            int sup = Integer.parseInt(Sup.getText());
            ControllerFil source = new ControllerFil();
            list = source.ricercaNumS(inf, sup);
            if (sup < inf + 3 && inf < sup) {
                  messenger msg = new messenger();
                  msg.messageOne("Errore - ricerca filementi per numero di segmenti",
                                                                    "La dimensione dell'intervalo di ricerca deve essere uguale a 3 o superiore.");
            }
            if (sup <= inf) {
                  messenger msg = new messenger();
                  msg.messageOne("Errore - ricerca filementi per numero di segmenti",
                                                       "L'estremo superiore dell'intervallo di ricerca deve essere maggiore di quello inferiore.");
            }
            if (sup < 0 || inf < 0) {
                  messenger msg = new messenger();
                  msg.messageOne("Errore - ricerca filementi per numero di segmenti", "I valori inseriti devono essere maggiori di 0.");
            } else {
                if (list != null) {
                    i = 1;
                    int p = list.size() / 20;
                    if (list.size() % 20 != 0) p++;
                    numElements.setText(list.size() + " elementi trovati.");
                    page.setText("pagina " + i + " di " + p);
                    Filamento fil;
                    for (num = 0; num < Math.min(20, list.size()); num++) {
                        fil = list.get(num);
                        data.getItems().add(fil);
                    }
                } else {
                    messenger msg = new messenger();
                    msg.messageOne("Errore - ricerca filementi per numero di segmenti", "Nessun elemento trovato.");
                }
            }
        } catch (NumberFormatException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - ricerca filementi per numero di segmenti","Inserire valori numerici.");
        } catch (SQLException e) {
            messenger msg = new messenger();
            int error = e.getErrorCode();
            msg.messageOne("Errore - ricerca filementi per numero di segmenti","Si è verificato un errore. Codice errore: " + error + ".");
            e.printStackTrace();
        }
    }

    @FXML
    void searchBranch() {
        try {
            String satellite = sat.getText();
            int idF = Integer.parseInt(idFil.getText());
            URL url = getClass().getResource("lista_segmenti.fxml");
            Stage stage = new Stage();
            ControllerFil source = new ControllerFil();
            int numS;
            List<Segmento> listS;
            listS = source.searchBranch(idF, satellite);
            if(listS != null) {
                Segmento seg;
                AnchorPane anchor = FXMLLoader.load(url);
                TableView<Segmento> tv = (TableView<Segmento>) anchor.lookup("#dataBranch");
                TextField idFT = (TextField) anchor.lookup("#idFil");
                idFT.setText(idFil.getText());
                TextField satFT = (TextField) anchor.lookup("#satellite");
                satFT.setText(sat.getText());
                for (numS = 0; numS < listS.size(); numS++) {
                    seg = listS.get(numS);
                    tv.getItems().add(seg);
                }
                Scene scene = new Scene(anchor);
                stage.setScene(scene);
                stage.show();
            } else {
                messenger msg = new messenger();
                msg.messageOne("Errore - ricerca segmenti", "Nessun elemento trovato.");
            }
        } catch(IOException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - ricerca segmenti","Si è verificato un errore.");
        } catch(NumberFormatException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - ricerca segmenti","Inserire valori numerici nel campo ID Filamento.");
        } catch(SQLException e) {
            messenger msg = new messenger();
            int error = e.getErrorCode();
            msg.messageOne("Errore - ricerca segmenti","Si è verificato un errore. Codice errore: " + error + ".");
        }
    }
    @FXML
    void searchEll() {
        try {
            data.getItems().clear();
            numElements.setText("");
            page.setText("");
            i = 0;
            num = 0;
            Double inf = Double.parseDouble(ellInf.getText());
            Double sup = Double.parseDouble(ellSup.getText());
            if (inf <= 1 || sup >= 10) {
                messenger msg = new messenger();
                msg.messageOne("Errore - ricerca per ellitticità","L'intervallo deve essere compreso tra 1 e 10 esclusi.");
            }
            if (inf >= sup) {
                messenger msg = new messenger();
                msg.messageOne("Errore - ricerca per ellitticità", "L'estremo inferiore dell'interallo di ricerca deve essere più piccolo di quello superiore.");
            } else {
                ControllerFil source = new ControllerFil();
                list = source.ricercaEll(inf, sup);
                int tot = source.getTot();
                if (list != null) {
                    i = 1;
                    int p = list.size() / 20;
                    if (list.size() % 20 != 0) {
                        p = p + 1;
                    }
                    numElements.setText(list.size() + " elementi trovati su " + tot);
                    page.setText("pagina " + i + " di " + p);
                    Filamento fil;
                    for (num = 0; num < Math.min(20, list.size()); num++) {
                        fil = list.get(num);
                        data.getItems().add(fil);
                    }
                }
            }
        } catch (NumberFormatException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - ricerca per ellitticità","Inserire valori numerici decimali per l'intervallo di ricerca.");
        } catch (SQLException e) {
            messenger msg = new messenger();
            int error = e.getErrorCode();
            msg.messageOne("Errore - ricerca per ellitticità","Si è verificato un errore. Codice errore: " + error + ".");
        }
    }

    @FXML
    void searchLum() {
        try {
            data.getItems().clear();
            numElements.setText("");
            page.setText("");
            i=0;
            num=0;
            ControllerFil source = new ControllerFil();
            double l = Double.parseDouble(lum.getText());
            if (l < 0) {
                messenger msg = new messenger();
                msg.messageOne("Errore - ricerca per luminosità","Inserire valore numerico maggiore di 0 nel campo percentuale luminosità.");
            } else {
                list = source.ricercaLum(l);
                int tot = source.getTot();
                if (list != null) {
                    i = 1;
                    int p = list.size()/20;
                    if(list.size()%20 != 0) p++;
                    numElements.setText(list.size() + " elementi trovati su " + tot);
                    page.setText("pagina " + i + " di " + p);
                    Filamento fil;
                    for (num = 0; num < Math.min(20, list.size()); num++) {
                        fil = list.get(num);
                        data.getItems().add(fil);
                    }
                } else {
                    messenger msg = new messenger();
                    msg.messageOne("Errore - ricerca per luminosità","Nessun elemento trovato.");
                }
            }
        } catch (NumberFormatException e) {
            messenger msg = new messenger();
            msg.messageOne("Errore - ricerca per luminosità","Inserire valore numerico nel campo percentuale luminosità.");
        } catch (SQLException e) {
            messenger msg = new messenger();
            int error = e.getErrorCode();
            msg.messageOne("Errore - ricerca segmenti","Si è verificato un errore. Codice errore: " + error + ".");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Inf != null : "fx:id=\"Inf\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert Sup != null : "fx:id=\"Sup\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert data != null : "fx:id=\"data\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert strumCol != null : "fx:id=\"strumCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert satCol != null : "fx:id=\"satCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert fluxCol != null : "fx:id=\"fluxCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert denCol != null : "fx:id=\"denCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert elliptCol != null : "fx:id=\"elliptCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert contrastCol != null : "fx:id=\"contrastCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert tempCol != null : "fx:id=\"tempCol\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert numElements != null : "fx:id=\"numElements\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert page != null : "fx:id=\"page\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert idFil != null : "fx:id=\"idFil\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert searchBranch != null : "fx:id=\"searchBranch\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert sat != null : "fx:id=\"sat\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert lum != null : "fx:id=\"lum\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert ellInf != null : "fx:id=\"ellInf\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert ellSup != null : "fx:id=\"ellSup\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert searchLum != null : "fx:id=\"searchLum\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert searchEll != null : "fx:id=\"searchEll\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert frazione != null : "fx:id=\"frazione\" was not injected: check your FXML file 'ricerca.fxml'.";
    }
}



