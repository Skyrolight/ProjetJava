package test_interface.GestionRevue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modele.Revue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static test_interface.GestionRevue.CtrlRevue.revueOnFocus;


public class CtrlVisualiserRevue implements Initializable {

    @FXML
    private ImageView img_view;
    @FXML
    private TextField tf_mod_titre;
    @FXML
    private TextField tf_mod_description;
    @FXML
    private TextField tf_mod_tarif;
    @FXML
    private TextField tf_mod_visuel;
    @FXML
    private Button btnRetour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Class<?> clazz = this.getClass();

        String imgURL = tf_mod_visuel.getText();
        InputStream stream = null;
        try {
            stream = new FileInputStream(imgURL);
            Image image = new Image(stream);
            img_view.setImage(image);
            img_view.setX(10);
            img_view.setY(10);
            img_view.setFitWidth(575);
            img_view.setPreserveRatio(true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Revue revueSelect = revueOnFocus;
        tf_mod_titre.setText(revueSelect.getTitre());
        tf_mod_titre.setDisable(true);
        tf_mod_description.setText(revueSelect.getDescription());
        tf_mod_description.setDisable(true);
        tf_mod_tarif.setText(String.valueOf(revueSelect.getTarif_numero()));
        tf_mod_tarif.setDisable(true);
        tf_mod_visuel.setText(revueSelect.getVisuel());
        tf_mod_visuel.setDisable(true);
    }

    public void onRetour(ActionEvent actionEvent) {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

}
