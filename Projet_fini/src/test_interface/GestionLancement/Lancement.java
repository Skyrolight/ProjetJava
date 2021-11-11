package test_interface.GestionLancement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class  Lancement extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            URL fxmlURL=getClass().getResource("ChoixPersistance.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            primaryStage.setScene(scene);
            primaryStage.setMinHeight(400);
            primaryStage.setMinWidth(600);
            primaryStage.setResizable(false);
            primaryStage.setTitle("RevueOnline");
            primaryStage.show();

        }
        catch(Exception e){
            e.printStackTrace();}
    }
}

