package test_interface.GestionClient;
import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.ClientDAO;
import dao.objet.RevueDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Adresse;
import modele.Client;
import modele.Periodicite;
import modele.Revue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import test_interface.GestionLancement.CtrlObjet;
import test_interface.GestionRevue.CtrlRevue;

import java.net.URL;
import java.util.ResourceBundle;

import static test_interface.GestionClient.CtrlClient.clientOnFocus;
import static test_interface.GestionRevue.CtrlRevue.revueOnFocus;

public class CtrlModifierClient implements Initializable{
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfNoRue;
    @FXML
    private TextField tfVoie;
    @FXML
    private TextField tfCodePostal;
    @FXML
    private TextField tfPays;
    @FXML
    private TextField tfVille;

    @FXML
    private Button btnValider;
    @FXML
    private Button btnRetour;

    public Persistance persistance = CtrlClient.persistance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = CtrlClient.persistance;
        Client clientSelect = clientOnFocus;
        tfNom.setText(clientSelect.getNom());
        tfPrenom.setText(clientSelect.getPrenom());
        tfNoRue.setText(String.valueOf(clientSelect.getNo_rue()));
        tfVoie.setText(clientSelect.getVoie());
        tfCodePostal.setText(clientSelect.getCode_postal());
        tfPays.setText(clientSelect.getPays());
        tfVille.setText(clientSelect.getVille());

    }

    public void onRetour(ActionEvent actionEvent) {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

    public void onValider(ActionEvent actionEvent) {

        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String pays = tfPays.getText();
        String ville = tfVille.getText();
        String codePostal = tfCodePostal.getText();
        String voie = tfVoie.getText();
        int noRue;

        boolean allGood = true;

        tfNom.requestFocus();

        try{
            noRue = Integer.parseInt(tfNoRue.getText());
            String erreur = "";
            if(nom.length()==0) {erreur = erreur + "Nom vide ! ";allGood = false ;tfNom.requestFocus();}
            if(prenom.length()==0) {erreur = erreur + "Prenom vide ! ";allGood = false ;tfPrenom.requestFocus();}
            if(pays.length()==0) {erreur = erreur + "Pays vide ! ";allGood = false ;tfPays.requestFocus();}
            if(ville.length()==0) {erreur = erreur + "Visuel vide ! ";allGood = false ;tfVille.requestFocus();}
            if(codePostal.length()==0) {erreur = erreur + "Code Postal vide ! ";allGood = false ;tfCodePostal.requestFocus();}
            if(codePostal.matches("-?\\d+")==false) {erreur = erreur + "Code Postal non valide ! ";allGood = false ;tfCodePostal.requestFocus();tfCodePostal.clear();}
            if(voie.length()==0) {erreur = erreur + "Voie vide ! ";allGood = false ;tfVoie.requestFocus();}

            if(erreur.length()!=0) alerter(erreur);

            if(allGood==true) {

                Adresse adress = new Adresse(0,null,null,null,null);
                adress.setVoie(voie);
                adress.setNo_rue(noRue);
                adress.setVoie(voie);
                adress.setPays(pays);
                adress.setCode_postal(codePostal);
                adress.setVille(ville);

                Client client = clientOnFocus;
                client.setNom(nom);
                client.setPrenom(prenom);
                client.setAdresse(adress);

                DAOFactory daoff = DAOFactory.getDAOFactory(persistance);
                ClientDAO daoo = daoff.getClientDAO();

                Boolean h = daoo.update(client);
                if (!h) System.out.println("Erreur de modification");
                else {

                    Stage stage = (Stage) btnValider.getScene().getWindow();
                    stage.close();
                }

            }
        }
        catch(Exception e){

            String erreur = "";
            if(nom.length()==0) {erreur = erreur + "Nom vide ! ";allGood = false ;tfNom.requestFocus();}
            if(prenom.length()==0) {erreur = erreur + "Prenom vide ! ";allGood = false ;tfPrenom.requestFocus();}
            if(pays.length()==0) {erreur = erreur + "Pays vide ! ";allGood = false ;tfPays.requestFocus();}
            if(ville.length()==0) {erreur = erreur + "Visuel vide ! ";allGood = false ;tfVille.requestFocus();}
            if(codePostal.length()==0) {erreur = erreur + "Code Postal vide ! ";allGood = false ;tfCodePostal.requestFocus();}
            if(codePostal.matches("-?\\d+")==false) {erreur = erreur + "Code Postal non valide ! ";allGood = false ;tfCodePostal.requestFocus();tfCodePostal.clear();}
            if(voie.length()==0) {erreur = erreur + "Voie vide ! ";allGood = false ;tfVoie.requestFocus();}
            erreur = erreur + "Veuillez entrer un n° de rue valide !";

            alerter(erreur);

            tfNoRue.clear();
            tfNoRue.requestFocus();
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
