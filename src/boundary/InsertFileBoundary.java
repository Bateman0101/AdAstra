package boundary;

import control.FileHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InsertFileBoundary {

    @FXML
    private TextField path;
    @FXML
    private ComboBox<String> list;

    @FXML
    public void initialize() {

        ObservableList<String> items = list.getItems();
        String[] obj = {"Filamento", "Stella", "Punto di perimetro", "Punto di segmento"};
        int len = obj.length;
        int i = 0;
        while (i < len) {
            items.add(obj[i]);
            i++;
        }

    }

    public void onImportFile() {

        String type = list.getValue();
        String filePath = path.getText();

        FileHandler control = new FileHandler();
        control.insert(type, filePath);

    }

    public void setPath(String str) {

        path.setText(str);
    }

    public void onChooseFile() {

        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) list.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            setPath(file.getAbsolutePath());
        }
    }
}
