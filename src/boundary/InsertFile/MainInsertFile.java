package boundary.InsertFile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInsertFile extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("InsertFile.fxml"));

        primaryStage.setScene(new Scene(root, 760, 440));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
