package boundary.InsertUtente;

import boundary.Main;
import control.UtenteHandler;
import exceptions.NoUserException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertUtenteBoundary {


    @FXML
    private TextField idEntry;
    @FXML
    private TextField nomeEntry;
    @FXML
    private TextField emailEntry;
    @FXML
    private TextField cognomeEntry;
    @FXML
    private PasswordField passwordEntry;
    @FXML
    private ComboBox<String> listTipo;

    @FXML
    public void initialize(){

        ObservableList<String> items = listTipo.getItems();
        String[] obj = {"utente", "admin"};
        int len = obj.length;
        int i = 0;
        while (i < len) {
            items.add(obj[i]);
            i++;
        }

    }

    public void onSubmitPressed() {

        String id = idEntry.getText();
        String nome = nomeEntry.getText();
        String cognome = cognomeEntry.getText();
        String email = emailEntry.getText();
        String password = passwordEntry.getText();
        String tipo = listTipo.getValue();

        if (id == null || nome == null || cognome == null
                || email == null || password == null || tipo == null ||
                id.length() < 6 || password.length() < 6){

            getAlert("Inserire dati corretti.");
            return;
        }

        UtenteHandler ctrl = new UtenteHandler();

        try {
            ctrl.insertNewUser(id, nome, cognome, email, password, tipo);
            getAlert("Utente inserito");
            clear();

        }catch (NoUserException e){

            getAlert("Utente già presente nel sistema");
            clear();
        }

    }
    public void clear(){

        idEntry.clear();
        nomeEntry.clear();
        cognomeEntry.clear();
        emailEntry.clear();
        passwordEntry.clear();
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

    public void getAlert(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
        return;
    }

}
