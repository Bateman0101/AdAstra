package boundary.InsertSatellite;

import boundary.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSatellite extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("InsertSatellite.fxml"));

        Scene s = new Scene(root, 800, 500);
        s.getStylesheets().add(Main.class.getResource("bootstrap3.css").toExternalForm());
        primaryStage.setScene(s);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

