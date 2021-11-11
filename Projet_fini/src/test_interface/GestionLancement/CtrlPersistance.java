package test_interface.GestionLancement;

import dao.Persistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class CtrlPersistance {

    @FXML
    public Button btQuitter;

    public static Persistance persistanceChoisie = null;

    public void onQuitter(ActionEvent actionEvent) {
        Stage stage = (Stage) btQuitter.getScene().getWindow();
        stage.close();
    }

    public void onMouseclickMS(MouseEvent mouseEvent) {
        persistanceChoisie = Persistance.MYSQL;
        fenetreChoixObjet(" - MY SQL");

        Stage stage = (Stage) btQuitter.getScene().getWindow();
        stage.close();
    }

    public void onMouseClickLM(MouseEvent mouseEvent) {
        persistanceChoisie = Persistance.LISTE_MEMOIRE;
        fenetreChoixObjet(" - LISTE MÉMOIRE");

        Stage stage = (Stage) btQuitter.getScene().getWindow();
        stage.close();
    }

    public void fenetreChoixObjet(String text){
        try{
            URL fxmlURL=getClass().getResource("ChoixObjet.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setResizable(false);
            stage.setTitle("RevueOnline" + text);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
