package boundary;

import boundary.FindFilamento.MainFilamento;
import boundary.FindInRegion.MainRegion;
import boundary.FindStelleInFilamento.MainStellaInFilamento;
import boundary.FindStelleRegioneFilamento.MainFindStelleRegioneFilamento;
import boundary.InsertFile.MainInsertFile;
import boundary.InsertSatellite.MainSatellite;
import boundary.InsertStrumento.MainStrumento;
import boundary.InsertUtente.InsertUtenteMain;
import boundary.PosizioneStellaSpinaDorsale.MainPosizioneStellaSpinaDorsale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@SuppressWarnings("ALL")
public class HomePageBoundary {

    @FXML
    private Button bSat;
    @FXML
    private Button bUtente;
    @FXML
    private Button bStrum;
    @FXML
    private Button button;
    @FXML
    private Button inFileBtn;

    public static String tipo;

    public static void setTipo(String tipo) {
        HomePageBoundary.tipo = tipo;
    }

    @FXML
    public void initialize() {


        if (tipo.equals("utente")) {
            bSat.setVisible(false);
            bStrum.setVisible(false);
            bUtente.setVisible(false);
            inFileBtn.setVisible(false);

        }

    }


    public void onInsertFilePressed(){

        MainInsertFile c = new MainInsertFile();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void onSatellitePressed(){

        MainSatellite c = new MainSatellite();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onStrumentoPressed(){

        MainStrumento c = new MainStrumento();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onInfoPressed(){


        MainFilamento c = new MainFilamento();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onRegionPressed(){

        MainRegion c = new MainRegion();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onFindStelleRegioneFilamento() {

        MainFindStelleRegioneFilamento m = new MainFindStelleRegioneFilamento();
        try {
            m.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPosizioneDiUnaStellaRispettoSpinaDorsale() {

        MainPosizioneStellaSpinaDorsale m = new MainPosizioneStellaSpinaDorsale();
        try {
            m.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStelleInFilPressed(){


        MainStellaInFilamento c = new MainStellaInFilamento();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onUtentePressed(){

        InsertUtenteMain c = new InsertUtenteMain();
        try {
            c.start(new Stage());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
