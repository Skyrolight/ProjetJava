package test_interface.GestionClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class  Interface2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            URL fxmlURL=getClass().getResource("FenetreClient.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            primaryStage.setScene(scene);
            primaryStage.setMinHeight(770);
            primaryStage.setMinWidth(600);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Client");
            primaryStage.show();

        }
        catch(Exception e){
            e.printStackTrace();}
    }
}

