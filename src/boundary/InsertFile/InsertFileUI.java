package boundary.InsertFile;

import boundary.Main;
import control.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

@SuppressWarnings("ALL")
public class InsertFileUI {

    @FXML
    private TextField path;
    @FXML
    private ComboBox<String> listCorpi;
    @FXML
    private ComboBox<String> listSat;
    @FXML
    private Button homeBtn;

    @FXML
    public void initialize() {

        ObservableList<String> items = listCorpi.getItems();
        ObservableList<String> itemsSat = listSat.getItems();
        String[] obj = {"Filamento", "Stella", "Perimetro", "Scheletro"};
        String[] objSat = {"Herschel", "Spitzer"};

        int lenSat = objSat.length;
        int j = 0;
        while (j < lenSat) {
            itemsSat.add(objSat[j]);
            j++;
        }
        int len = obj.length;
        int i = 0;
        while (i < len) {
            items.add(obj[i]);
            i++;
        }

    }

    public void onImportFile() {

        try {

            String type = listCorpi.getValue();
            String filePath = path.getText();
            String satellite = listSat.getValue();

            switch (type) {
                case "Filamento":
                    CsvFilamenti csvFilamenti = new CsvFilamenti();
                    csvFilamenti.insert(filePath);
                    break;
                case "Stella":
                    CsvStelle csvStelle = new CsvStelle();
                    csvStelle.insert(filePath);
                    break;
                case "Perimetro":
                    CsvPerimetro csvPerimetro = new CsvPerimetro();
                    csvPerimetro.insert(filePath, satellite);
                    break;
                case "Scheletro":
                    CsvScheletro csvScheletro = new CsvScheletro();
                    csvScheletro.insert(filePath, satellite);
                    break;
            }

        } catch (NullPointerException n) {
            n.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un path valido");
            alert.showAndWait();
        }
    }



    public void setPath(String str) {

        path.setText(str);
    }

    public void onChooseFile() {

        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) listCorpi.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            setPath(file.getAbsolutePath());
        }
    }

    public void getHomePage() {
        Main c = new Main();
        try {
            c.start(new Stage());
            Stage stage = (Stage) homeBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
