package boundary.Login;

import boundary.HomePageBoundary;
import boundary.Main;
import control.UtenteHandler;
import exceptions.NoUserException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginBoundary {

        @FXML
        private TextField idEntry;
        @FXML
        private PasswordField passwordEntry;


        @FXML
        public void initialize() {


        }

        public void onSubmitPressed() {

            String id = idEntry.getText();
            String password = passwordEntry.getText();

            if (password.length() < 6 || id.length() < 6) {

                getAlert("Inserire ID/password di minimo 6 caratteri");

                return;

            }

            UtenteHandler ctrl = new UtenteHandler();

            try {

                ctrl.isUserPresent(id, password);

                String tipo = ctrl.getTipo(id, password);

                HomePageBoundary.setTipo(tipo);

                getHomePage();

            }catch (NoUserException e){

                getAlert("Utente non presente nel sistema!");
            }


        }

    public void getAlert(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
