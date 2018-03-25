package boundary;

import boundary.FileSearch;
import control.InsertFile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InsertFileBoundary {

    @FXML
    private TextField path;
    @FXML
    private ComboBox<String> list;

    @FXML
    public void initialize() {

        ObservableList<String> items = list.getItems();
        String[] obj = {"Filamento", "Stella", "Punto di perimetro"};
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

        InsertFile control = new InsertFile();
        control.insert(type, filePath);

    }

    public void setPath(String str) {

        path.setText(str);
    }

    public void onChooseFile() {

        FileSearch fs = new FileSearch();
        try {
            fs.setFb(this);
            fs.start(new Stage());
           /* Stage stage = (Stage) modifyName.getScene().getWindow();
            stage.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
