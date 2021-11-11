package test_interface.GestionClient;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.ClientDAO;
import dao.objet.RevueDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Adresse;
import modele.Client;
import modele.Periodicite;
import modele.Revue;
import test_interface.GestionLancement.CtrlObjet;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;

public class CtrlClient extends Stage implements Initializable, ChangeListener<Client> {
    @FXML
    public Button btRetour;
    @FXML
    private Label label_info;
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
    private TableView<Client> tableClient;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnVisualiser;
    @FXML
    private Button btnModifier;
    public static Client clientOnFocus;

    public static Persistance persistance = CtrlObjet.persistanceChoice;

    public void creerModele(ActionEvent actionEvent) {

        label_info.setText("Welcome to JavaFX Application!");
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

                Adresse adresse = new Adresse(0,null,null,null,null);
                adresse.setVoie(voie);
                adresse.setNo_rue(noRue);
                adresse.setVoie(voie);
                adresse.setPays(pays);
                adresse.setCode_postal(codePostal);
                adresse.setVille(ville);

                Client client = new Client(null,null,null);
                client.setNom(nom);
                client.setPrenom(prenom);
                client.setAdresse(adresse);

                DAOFactory daof1 = DAOFactory.getDAOFactory(persistance);
                ClientDAO dao1 = daof1.getClientDAO();

                Boolean h = dao1.create(client);
                if (h) label_info.setText("Client Créé !");

                tableClient.getItems().clear();
                this.tableClient.getItems().addAll( DAOFactory.getDAOFactory(persistance).getClientDAO().getAll());

                allFieldsClear();
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


    private void allFieldsClear() {
        tfNom.clear();
        tfPrenom.clear();
        tfPays.clear();
        tfVille.clear();
        tfCodePostal.clear();
        tfVoie.clear();
        tfNoRue.clear();
    }

    public void initTableView(){

        TableColumn<Client, String> colIdClient = new TableColumn<>("Id");
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
        TableColumn<Client, String> colAdresse = new TableColumn<>("Adresse");

        colIdClient.setCellValueFactory(new PropertyValueFactory<Client, String>("id_client"));
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));

        this.tableClient.getColumns().setAll(colIdClient,colNom,colPrenom,colAdresse);
        this.tableClient.getItems().addAll( DAOFactory.getDAOFactory(persistance).getClientDAO().getAll());
    }


    public void onModifier() {
        this.btnModifier.setDisable(true);


        btnModifier.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ModifierClient.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
                stage.setTitle("Modifier le Client");
                stage.setScene(scene);

                stage.showAndWait();

                tableClient.getItems().clear();
                this.tableClient.getItems().addAll( DAOFactory.getDAOFactory(persistance).getClientDAO().getAll());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void onSupprimer(ActionEvent actionEvent) {

        this.btnSupprimer.setDisable(true);

        DAOFactory daof = DAOFactory.getDAOFactory(persistance);
        ClientDAO dao = daof.getClientDAO();
        Boolean h = dao.delete(clientOnFocus);
        if (!h) System.out.println("Erreur de suppression");

        tableClient.getItems().clear();
        this.tableClient.getItems().addAll( DAOFactory.getDAOFactory(persistance).getClientDAO().getAll());

    }

    public void testNull(Client select){

        if (!isNull(select)){clientOnFocus = select;
            this.btnSupprimer.setDisable(false);
            this.btnModifier.setDisable(false);
            this.btnVisualiser.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = CtrlObjet.persistanceChoice;
        tfPrenom.requestFocus();
/*
        if(persistance==Persistance.LISTE_MEMOIRE)System.out.println("listememoire client");
        else if(persistance==Persistance.MYSQL)System.out.println("mysql client");
*/
        label_info.setText("Welcome to JavaFX Application!");
        this.btnSupprimer.setDisable(true);
        this.btnModifier.setDisable(true);
        this.btnVisualiser.setDisable(true);

        initTableView();

        this.tableClient.getSelectionModel(). selectedItemProperty().addListener(this);
        tableClient.getSelectionModel().selectedItemProperty().addListener(observable -> testNull(tableClient.getSelectionModel().getSelectedItem()));


    }



    @Override
    public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {

        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);
        this.btnVisualiser.setDisable(newValue == null);

        this.btnAjouter.setDisable(newValue != null);

    }


    public void onVisualiser(ActionEvent actionEvent) {
        /*this.btnVisualiser.setDisable(false);

        btnVisualiser.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("VisualiserRevue.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 600, 330);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
                stage.setTitle("Visualiser la revue");
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    public void onRetour(ActionEvent actionEvent) {
        Stage stage = (Stage) btRetour.getScene().getWindow();
        stage.close();
    }

    public void activeBtn(MouseEvent mouseEvent) {
        btnAjouter.setDisable(false);
    }
}

