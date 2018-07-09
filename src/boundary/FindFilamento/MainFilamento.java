package boundary.FindFilamento;

import boundary.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class MainFilamento extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FindFilamento.fxml"));

        Scene s = new Scene(root, 800, 500);
        s.getStylesheets().add(Main.class.getResource("bootstrap3.css").toExternalForm());
        primaryStage.setScene(s);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

