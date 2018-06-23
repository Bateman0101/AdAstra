package boundary.Login;

import boundary.HomePageBoundary;
import boundary.Main;
import control.UtenteHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

            UtenteHandler ctrl = new UtenteHandler();

            if (!ctrl.isUserPresent(id, password))
                return;

            String tipo = ctrl.getTipo(id, password);

            HomePageBoundary.setTipo(tipo);

            getHomePage();

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
