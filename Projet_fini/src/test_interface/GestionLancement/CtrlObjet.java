package test_interface.GestionLancement;

import dao.Persistance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class CtrlObjet {

    @FXML
    public Button btQuitter;
    @FXML
    private Button btRetour;

    public static Persistance persistanceChoice = CtrlPersistance.persistanceChoisie;
    String title;

    public void onAbonnementClick(MouseEvent mouseEvent) {
        persistanceChoice = CtrlPersistance.persistanceChoisie;
        if(persistanceChoice == Persistance.MYSQL) title = " - MY SQL";
        else title = "- LISTE MÉMOIRE";

        fenetreObjet("/test_interface/GestionAbonnement/FenetreAbonnement.fxml", title);
    }

    public void onClientClick(MouseEvent mouseEvent) {
        persistanceChoice = CtrlPersistance.persistanceChoisie;
        if(persistanceChoice == Persistance.MYSQL) title = " - MY SQL";
        else title = "- LISTE MÉMOIRE";

        fenetreObjet("/test_interface/GestionClient/FenetreClient.fxml", title);
    }

    public void onPeriodiciteClick(MouseEvent mouseEvent) {
        persistanceChoice = CtrlPersistance.persistanceChoisie;
        if(persistanceChoice == Persistance.MYSQL) title = " - MY SQL";
        else title = "- LISTE MÉMOIRE";

        fenetreObjet("/test_interface/GestionPeriodicite/FenetrePeriodicite.fxml", title);
    }

    public void onRevueClick(MouseEvent mouseEvent) {
        persistanceChoice = CtrlPersistance.persistanceChoisie;
        if(persistanceChoice == Persistance.MYSQL) title = " - MY SQL";
        else title = "- LISTE MÉMOIRE";

        fenetreObjet("/test_interface/GestionRevue/FenetreRevue.fxml", title);
    }

    public void onRetourClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) btRetour.getScene().getWindow();
        stage.close();

        fenetreChoixPersistance();
    }

    public void onQuitterClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) btRetour.getScene().getWindow();
        stage.close();
    }

    public void fenetreObjet(String fxml,String text){
        try{
            URL fxmlURL=getClass().getResource(fxml);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            Scene scene = new Scene(fxmlLoader.load(), 600, 750);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setResizable(false);
            stage.setTitle("RevueOnline" + title);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fenetreChoixPersistance(){
        try{
            URL fxmlURL=getClass().getResource("ChoixPersistance.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage primaryStage = new Stage();

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