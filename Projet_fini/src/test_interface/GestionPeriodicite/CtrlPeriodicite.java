package test_interface.GestionPeriodicite;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.ClientDAO;
import dao.objet.PeriodiciteDAO;
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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import test_interface.GestionLancement.CtrlObjet;

import static java.util.Objects.isNull;

public class CtrlPeriodicite extends Stage implements Initializable, ChangeListener<Periodicite> {
    @FXML
    public Button btRetour;
    @FXML
    private Label label_info;
    @FXML
    private TextField tfTitre;
    @FXML
    private TableView<Periodicite> tablePeriodicite;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnVisualiser;

    public static Periodicite periodiciteOnFocus;

    public static Persistance persistance = CtrlObjet.persistanceChoice;


    public void creerModele(ActionEvent actionEvent) {
        label_info.setText("Welcome to JavaFX Application!");
        String titre = tfTitre.getText();

        boolean allGood = true;

        tfTitre.requestFocus();

        try{
            String erreur = "";
            if(titre.length()==0) {erreur = erreur + "Periodicité vide ! ";allGood = false ;tfTitre.requestFocus();}
            if(erreur.length()!=0) alerter(erreur);

            if(allGood==true) {
                DAOFactory daof1 = DAOFactory.getDAOFactory(persistance);
                PeriodiciteDAO dao1 = daof1.getPeriodiciteDAO();
                Periodicite periodicite = new Periodicite(titre);

                Boolean h = dao1.create(periodicite);
                if (h) label_info.setText("Périodicité Créée !");

                tablePeriodicite.getItems().clear();
                this.tablePeriodicite.getItems().addAll(DAOFactory.getDAOFactory(persistance).getPeriodiciteDAO().getAll());

                tfTitre.clear();
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
        TableColumn<Periodicite, Integer> colIdPeriodicite = new TableColumn<>("Id");
        TableColumn<Periodicite, String> colPeriodicite = new TableColumn<>("Périodicité");

        colIdPeriodicite.setCellValueFactory(new PropertyValueFactory<Periodicite, Integer>("id_periodicite"));
        colPeriodicite.setCellValueFactory(new PropertyValueFactory<Periodicite, String>("libelle"));

        this.tablePeriodicite.getColumns().setAll(colIdPeriodicite,colPeriodicite);
        this.tablePeriodicite.getItems().addAll( DAOFactory.getDAOFactory(persistance).getPeriodiciteDAO().getAll());
    }

    public void onSupprimer(ActionEvent actionEvent) {
        this.btnSupprimer.setDisable(true);

        DAOFactory daof = DAOFactory.getDAOFactory(persistance);
        PeriodiciteDAO dao = daof.getPeriodiciteDAO();
        Boolean h = dao.delete(periodiciteOnFocus);
        if (!h) System.out.println("Erreur de suppression");

        tablePeriodicite.getItems().clear();
        this.tablePeriodicite.getItems().addAll( DAOFactory.getDAOFactory(persistance).getPeriodiciteDAO().getAll());

    }

    public void testNull(Periodicite select){
        if (!isNull(select)){periodiciteOnFocus = select;
            this.btnSupprimer.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = CtrlObjet.persistanceChoice;
        label_info.setText("Welcome to JavaFX Application!");
        this.btnSupprimer.setDisable(true);

        initTableView();

        this.tablePeriodicite.getSelectionModel(). selectedItemProperty().addListener(this);
        tablePeriodicite.getSelectionModel().selectedItemProperty().addListener(observable -> testNull(tablePeriodicite.getSelectionModel().getSelectedItem()));

    }

    @Override
    public void changed(ObservableValue<? extends Periodicite> observableValue, Periodicite periodicite, Periodicite newValue) {
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
