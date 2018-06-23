package boundary.InsertStrumento;

import boundary.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertStrumentoBoundary {

    @FXML
    private TextField name;
    @FXML
    private TextField bande;
    @FXML
    private ComboBox<String> list;
    @FXML
    private Button apply;


    @FXML
    public void initialize() {

        name.setVisible(false);
        bande.setVisible(false);
        apply.setVisible(false);



    }

    public void onApply(){

    }

    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) apply.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
