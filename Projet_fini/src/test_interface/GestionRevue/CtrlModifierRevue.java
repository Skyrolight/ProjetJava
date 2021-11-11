package test_interface.GestionRevue;
import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.RevueDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Periodicite;
import modele.Revue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import test_interface.GestionLancement.CtrlObjet;

import java.net.URL;
import java.util.ResourceBundle;

import static test_interface.GestionRevue.CtrlRevue.revueOnFocus;

public class CtrlModifierRevue implements Initializable{
    @FXML
    private TextField tf_mod_titre;
    @FXML
    private TextField tf_mod_description;
    @FXML
    private TextField tf_mod_tarif;
    @FXML
    private TextField tf_mod_visuel;
    @FXML
    private ChoiceBox<Periodicite> cb_mod_periodicite;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnRetour;

    public Persistance persistance = CtrlRevue.persistance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = CtrlRevue.persistance;
        Revue revueSelect = revueOnFocus;
        tf_mod_titre.setText(revueSelect.getTitre());
        tf_mod_description.setText(revueSelect.getDescription());
        tf_mod_tarif.setText(String.valueOf(revueSelect.getTarif_numero()));
        tf_mod_visuel.setText(revueSelect.getVisuel());

        cb_mod_periodicite.setFocusTraversable(false);
        DAOFactory dao = DAOFactory.getDAOFactory(persistance);
        this.cb_mod_periodicite.setItems(FXCollections.observableArrayList(dao.getPeriodiciteDAO().getAll()));

    }

    public void onRetour(ActionEvent actionEvent) {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

    public void onValider(ActionEvent actionEvent) {


        String titre = tf_mod_titre.getText();
        String description = tf_mod_description.getText();
        float tarif;

        String visuel = tf_mod_visuel.getText();
        int id_period = -1;
        if(cb_mod_periodicite.getValue() != null) id_period = cb_mod_periodicite.getValue().getId_periodicite();

        boolean allGood = true;

        tf_mod_titre.requestFocus();

        try{
            tarif = Float.parseFloat(tf_mod_tarif.getText());

            String erreur = "";
            if(titre.length()==0) {erreur = erreur + "Titre vide ! ";allGood = false ;tf_mod_titre.requestFocus();}
            if(description.length()==0) {erreur = erreur + "Description vide ! ";allGood = false ;tf_mod_description.requestFocus();}
            if(description.length()>400) {erreur = erreur + "Description trop longue ! ";allGood = false ;tf_mod_description.requestFocus();}
            if(visuel.length()==0) {erreur = erreur + "Visuel vide ! ";allGood = false ;tf_mod_visuel.requestFocus();}
            if(id_period == -1) {erreur = erreur + "Periodicité non sélectionnée ! ";allGood = false ;}
            if(erreur.length()!=0) alerter(erreur);

            if(allGood==true) {
                Revue newRevue = revueOnFocus;
                newRevue.setTitre(titre);
                newRevue.setDescription(description);
                newRevue.setTarif_numero(tarif);
                newRevue.setVisuel(visuel);
                newRevue.setId_periodicite(id_period);

                DAOFactory daof = DAOFactory.getDAOFactory(persistance);
                RevueDAO dao = daof.getRevueDAO();

                Boolean h = dao.update(newRevue);
                if (!h) System.out.println("Erreur de modification");
                else {

                    Stage stage = (Stage) btnValider.getScene().getWindow();
                    stage.close();
                }

            }
        }
        catch(Exception e){
            String erreur = "";
            if(titre.length()==0) {erreur = erreur + "Titre vide ! ";allGood = false ;tf_mod_titre.requestFocus();}
            if(description.length()==0) {erreur = erreur + "Description vide ! ";allGood = false ;tf_mod_description.requestFocus();}
            if(description.length()>400) {erreur = erreur + "Description trop longue ! ";allGood = false ;tf_mod_description.requestFocus();}
            if(visuel.length()==0) {erreur = erreur + "Visuel vide ! ";allGood = false ;tf_mod_visuel.requestFocus();}
            if(id_period == -1) {erreur = erreur + "Periodicité non sélectionnée ! ";allGood = false ;}
            erreur = erreur + "Veuillez entrer un tarif valide !";

            alerter(erreur);

            tf_mod_tarif.clear();
            tf_mod_tarif.requestFocus();
        }

    }
    public void alerter(String message) {


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");

        VBox errorList = new VBox();
        Label label = new Label(message);
        label.setStyle("-fx-text-fill: red; ");
        errorList.getChildren().add(label);

        alert.getDialogPane().setContent(errorList);

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


}
