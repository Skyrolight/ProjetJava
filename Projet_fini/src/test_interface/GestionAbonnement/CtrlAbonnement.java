package test_interface.GestionAbonnement;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.AbonnementDAO;
import dao.objet.RevueDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import test_interface.GestionLancement.CtrlObjet;

import static java.util.Objects.isNull;

public class CtrlAbonnement extends Stage implements Initializable, ChangeListener<Abonnement> {

    @FXML
    public DatePicker dpDateDebut;
    @FXML
    public DatePicker dpDateFin;
    @FXML
    public ChoiceBox<Client> cbClient;
    @FXML
    public ChoiceBox<Revue> cbRevue;
    @FXML
    public Button btRetour;
    @FXML
    private Label label_info;
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_tarif;
    @FXML
    private TextField tf_visuel;

    @FXML
    private TableView<Abonnement> tableAbonnement;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;

    public static Abonnement abonnementOnFocus;

    public static Persistance persistance = CtrlObjet.persistanceChoice;


    public void creerModele(ActionEvent actionEvent) {
        label_info.setText("Welcome to JavaFX Application!");

        int idClient = -1;
        if(cbClient.getValue() != null) idClient = cbClient.getValue().getId_client();

        int idRevue = -1;
        if(cbRevue.getValue() != null) idRevue = cbRevue.getValue().getId_revue();


        boolean allGood = true;
        try{
            String erreur = "";


            if(dpDateDebut.getValue()==null) {erreur = erreur + "Date de début vide ! ";allGood = false ;}
            if(dpDateFin.getValue()==null) {erreur = erreur + "Date de Fin vide ! ";allGood = false ;}
            if(idClient==-1) {erreur = erreur + "Client non sélectionné ! ";allGood = false ;}
            if(idRevue==-1) {erreur = erreur + "Revue non sélectionnée ! ";allGood = false ;}

            if(dpDateDebut.getValue()!=null && dpDateFin.getValue()!=null)
            {
                ZoneId defaultZoneId = ZoneId.systemDefault();
                LocalDate dateD = dpDateDebut.getValue();
                Date dateStart = Date.from(dateD.atStartOfDay(defaultZoneId).toInstant());

                LocalDate dateF = dpDateFin.getValue();
                Date dateEnd = Date.from(dateF.atStartOfDay(defaultZoneId).toInstant());

                if (dateStart.compareTo(dateEnd) > 0) {
                    erreur = erreur + "Date de fin supérieure à la date de début ! ";
                    allGood = false;
                }

            }
            if(erreur.length()!=0) alerter(erreur);

            if(allGood==true) {

                DAOFactory daof1 = DAOFactory.getDAOFactory(persistance);
                AbonnementDAO dao1 = daof1.getAbonnementDAO();

                Abonnement abonnement = new Abonnement(java.sql.Date.valueOf(dpDateDebut.getValue()),java.sql.Date.valueOf(dpDateFin.getValue()),idClient,idRevue);

                Boolean h = dao1.create(abonnement);
                if (h) label_info.setText("Abonnement Créé !");

                tableAbonnement.getItems().clear();
                this.tableAbonnement.getItems().addAll( DAOFactory.getDAOFactory(persistance).getAbonnementDAO().getAll());

            }
        }
        catch(Exception e){
            e.printStackTrace();
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

    public void initTableView(){

        TableColumn<Abonnement, Integer> colIdAbonnement = new TableColumn<>("Id");
        TableColumn<Abonnement, java.sql.Date> colDateD = new TableColumn<>("Date début");
        TableColumn<Abonnement, java.sql.Date> colDateF = new TableColumn<>("Date fin");
        TableColumn<Abonnement, Integer> colClient = new TableColumn<>("Id client");
        TableColumn<Abonnement, Integer> colRevue = new TableColumn<>("Id revue");

        colIdAbonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        colDateD.setCellValueFactory(new PropertyValueFactory<Abonnement, java.sql.Date>("date_debut"));
        colDateF.setCellValueFactory(new PropertyValueFactory<Abonnement, java.sql.Date>("date_fin"));
        colClient.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_client"));
        colRevue.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_revue"));

        this.tableAbonnement.getColumns().setAll(colIdAbonnement,colDateD,colDateF,colClient,colRevue);
        this.tableAbonnement.getItems().addAll( DAOFactory.getDAOFactory(persistance).getAbonnementDAO().getAll());
    }


    public void onSupprimer(ActionEvent actionEvent) {
        this.btnSupprimer.setDisable(true);

        DAOFactory daof = DAOFactory.getDAOFactory(persistance);
        AbonnementDAO dao = daof.getAbonnementDAO();
        Boolean h = dao.delete(abonnementOnFocus);
        if (!h) System.out.println("Erreur de suppression");

        tableAbonnement.getItems().clear();
        this.tableAbonnement.getItems().addAll( DAOFactory.getDAOFactory(persistance).getAbonnementDAO().getAll());

    }

    public void testNull(Abonnement select){
        if (!isNull(select)){abonnementOnFocus = select;
            this.btnSupprimer.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = CtrlObjet.persistanceChoice;
        label_info.setText("Welcome to JavaFX Application!");

        this.btnSupprimer.setDisable(true);


        DAOFactory dao = DAOFactory.getDAOFactory(persistance);
        this.cbClient.setItems(FXCollections.observableArrayList(dao.getClientDAO().getAll()));
        this.cbRevue.setItems(FXCollections.observableArrayList(dao.getRevueDAO().getAll()));

        initTableView();

        this.tableAbonnement.getSelectionModel(). selectedItemProperty().addListener(this);
        tableAbonnement.getSelectionModel().selectedItemProperty().addListener(observable -> testNull(tableAbonnement.getSelectionModel().getSelectedItem()));

    }



    @Override
    public void changed(ObservableValue<? extends Abonnement> observableValue, Abonnement abonnement, Abonnement newValue) {
        this.btnSupprimer.setDisable(newValue == null);


        this.btnAjouter.setDisable(newValue != null);
    }

    public void onRetour(ActionEvent actionEvent) {
        Stage stage = (Stage) btRetour.getScene().getWindow();
        stage.close();
    }

    public void activeBtn(MouseEvent mouseEvent) {
        btnAjouter.setDisable(false);
    }

}
