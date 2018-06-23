package boundary.InsertUtente;

import boundary.Main;
import control.UtenteHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

        UtenteHandler ctrl = new UtenteHandler();

        ctrl.insertNewUser(id, nome, cognome, email, password, tipo);

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
