/**
 * Sample Skeleton for 'ricerca.fxml' Controller Class
 */

package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import control.ControllerFil;
import dao.RicercaSegmenti;
import entity.SegmentoAdam;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import entity.Filamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ricerca {

    List<Filamento> list = new ArrayList<Filamento>();
    int num;
    int i;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Inf"
    private TextField Inf; // Value injected by FXMLLoader

    @FXML // fx:id="Sup"
    private TextField Sup; // Value injected by FXMLLoader

    @FXML // fx:id="plusInf"
    private Button plusInf; // Value injected by FXMLLoader

    @FXML // fx:id="plusSup"
    private Button plusSup; // Value injected by FXMLLoader

    @FXML // fx:id="search"
    private Button search; // Value injected by FXMLLoader

    @FXML // fx:id="minusInf"
    private Button minusInf; // Value injected by FXMLLoader

    @FXML // fx:id="minusSup"
    private Button minusSup; // Value injected by FXMLLoader

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
    void back(ActionEvent event) {
        if(i>1){
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
    void next(ActionEvent event) {
        if(num < (list.size() - 1)){
            int limit = num + 21;
            int p = list.size()/20;
            i++;
            if(list.size()%20 != 0) p++;
            data.getItems().clear();
            Filamento fil;
            num++;
            while(num < list.size() && num < limit) {
                fil = list.get(num);
                data.getItems().add(fil);
                num++;
            }
            page.setText("pagina " + i + " di " + p);
        }
    }

    @FXML
    void minusInf(ActionEvent event) {

    }

    @FXML
    void minusSup(ActionEvent event) {

    }

    @FXML
    void plusInf(ActionEvent event) {

    }

    @FXML
    void plusSup(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {
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
            if (list != null) {
                i = 1;
                int p = list.size()/20;
                if(list.size()%20 != 0) p++;
                numElements.setText(list.size() + " elementi trovati.");
                page.setText("pagina " +  i + " di " + p);
                Filamento fil;
                for (num = 0; num < Math.min(20, list.size()); num++) {
                    fil = list.get(num);
                    data.getItems().add(fil);
                }
            } else {
                if (sup < inf + 2) {
                    System.out.println("");

                } else {
                    //errore
                }
            }
        } catch (NumberFormatException e) {
            int x = 35/20;
            System.out.println(x);
        }
    }

    @FXML
    void searchBranch(ActionEvent event) {
        String satellite = sat.getText();
        int idF = Integer.parseInt(idFil.getText());
        URL url = getClass().getResource("lista_segmenti.fxml");
        Stage stage = new Stage();
        RicercaSegmenti source = new RicercaSegmenti();
        int numS;
        List<SegmentoAdam> listS;
        listS = source.search(idF, satellite);
        try {
            SegmentoAdam seg;
            AnchorPane anchor = FXMLLoader.load(url);
            TableView tv = (TableView) anchor.lookup("#dataBranch");
            TextField idFT = (TextField) anchor.lookup("#idFil");
            idFT.setText(idFil.getText());
            TextField satFT = (TextField) anchor.lookup("#satellite");
            satFT.setText(sat.getText());
            for(numS=0; numS < listS.size(); numS++){
                seg = listS.get(numS);
                tv.getItems().add(seg);
            }
            Scene scene = new Scene(anchor);
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
                e.printStackTrace();
        }
    }/*
    @FXML
    void searchEll(ActionEvent event) {
        try {
            data.getItems().clear();
            numElements.setText("");
            page.setText("");
            i = 0;
            num = 0;

            ControllerFil source = new ControllerFil();
            list = source.ricercaEll();
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
                int l;
                if (l < 0) {
                    System.out.println("");

                } else {
                    //errore
                }
            }
        } catch (NumberFormatException e) {
            int x = 35/20;
            System.out.println(x);
        }
    }
*/
    @FXML
    void searchLum(ActionEvent event) {
        try {
            data.getItems().clear();
            numElements.setText("");
            page.setText("");
            i=0;
            num=0;
            ControllerFil source = new ControllerFil();
            float l = Float.parseFloat(lum.getText());
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
                if (l < 0) {
                    System.out.println("");

                } else {
                    //errore
                }
            }
        } catch (NumberFormatException e) {
            int x = 35/20;
            System.out.println(x);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Inf != null : "fx:id=\"Inf\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert Sup != null : "fx:id=\"Sup\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert plusInf != null : "fx:id=\"plusInf\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert plusSup != null : "fx:id=\"plusSup\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert minusInf != null : "fx:id=\"minusInf\" was not injected: check your FXML file 'ricerca.fxml'.";
        assert minusSup != null : "fx:id=\"minusSup\" was not injected: check your FXML file 'ricerca.fxml'.";
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



