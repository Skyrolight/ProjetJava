package test_interface.GestionRevue;

import dao.Persistance;
import dao.factory.DAOFactory;
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
import modele.Periodicite;
import modele.Revue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import test_interface.GestionLancement.CtrlObjet;

import static java.util.Objects.isNull;

public class CtrlRevue extends Stage implements Initializable, ChangeListener<Revue> {
    @FXML
    public Button btRetour;
    @FXML
    public Button btCSV;
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
    private ChoiceBox<Periodicite> cb_periodicite;
    @FXML
    private TableView<Revue> tableRevue;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnVisualiser;
    @FXML
    private Button btnModifier;
    public static Revue revueOnFocus;

    public static Persistance persistance = CtrlObjet.persistanceChoice;


    public void creerModele(ActionEvent actionEvent) {
        label_info.setText("Welcome to JavaFX Application!");
        String titre = tf_titre.getText();
        float tarif = 0;
        String visuel = tf_visuel.getText();
        String description = tf_description.getText();

        int id_period = -1;
        if(cb_periodicite.getValue() != null) id_period = cb_periodicite.getValue().getId_periodicite();

        boolean allGood = true;

        tf_titre.requestFocus();

        try{
            tarif = Float.parseFloat( tf_tarif.getText());

            String erreur = "";
            if(titre.length()==0) {erreur = erreur + "Titre vide ! ";allGood = false ;tf_titre.requestFocus();}
            if(description.length()==0) {erreur = erreur + "Description vide ! ";allGood = false ;tf_description.requestFocus();}
            if(description.length()>400) {erreur = erreur + "Description trop longue ! ";allGood = false ;tf_description.requestFocus();}
            if(visuel.length()==0) {erreur = erreur + "Visuel vide ! ";allGood = false ;tf_visuel.requestFocus();}
            if(id_period == -1) {erreur = erreur + "Periodicité non sélectionnée ! ";allGood = false ;}
            if(erreur.length()!=0) alerter(erreur);

            if(allGood==true) {
                DAOFactory daof1 = DAOFactory.getDAOFactory(persistance);
                RevueDAO dao1 = daof1.getRevueDAO();
                Revue revue = new Revue(23,titre, description, tarif, visuel, id_period);

                Boolean h = dao1.create(revue);
                if (h) label_info.setText("Revue Créée !");

                tableRevue.getItems().clear();
                this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persistance).getRevueDAO().getAll());

                allFieldsClear();
            }
        }
        catch(Exception e){
            String erreur = "";
            if(titre.length()==0) {erreur = erreur + "Titre vide ! ";allGood = false ;tf_titre.requestFocus();}
            if(description.length()==0) {erreur = erreur + "Description vide ! ";allGood = false ;tf_description.requestFocus();}
            if(description.length()>400) {erreur = erreur + "Description trop longue ! ";allGood = false ;tf_description.requestFocus();}
            if(visuel.length()==0) {erreur = erreur + "Visuel vide ! ";allGood = false ;tf_visuel.requestFocus();}
            if(id_period == -1) {erreur = erreur + "Periodicité non sélectionnée ! ";allGood = false ;}
            erreur = erreur + "Veuillez entrer un tarif valide !";

            alerter(erreur);

            tf_tarif.clear();
            tf_tarif.requestFocus();
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
        tf_titre.clear();
        tf_description.clear();
        tf_tarif.clear();
        tf_visuel.clear();

    }

    public void initTableView(){
        TableColumn<Revue, String> colIdRevue = new TableColumn<>("Id");
        TableColumn<Revue, String> colTitre = new TableColumn<>("Titre");
        TableColumn<Revue, String> colDescription = new TableColumn<>("Description");
        TableColumn<Revue, Integer> colTarif = new TableColumn<>("Tarif");
        TableColumn<Revue, String> colVisuel = new TableColumn<>("Visuel");
        TableColumn<Revue, Integer> colIdPeriodicite = new TableColumn<>("Periodicite");

        colIdRevue.setCellValueFactory(new PropertyValueFactory<Revue, String>("id_revue"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Revue, String>("titre"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Revue, String>("description"));
        colTarif.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("tarif_numero"));
        colVisuel.setCellValueFactory(new PropertyValueFactory<Revue, String>("visuel"));
        colIdPeriodicite.setCellValueFactory(new PropertyValueFactory<Revue, Integer>("id_periodicite"));

        this.tableRevue.getColumns().setAll(colIdRevue,colTitre,colDescription,colTarif,colVisuel,colIdPeriodicite);
        this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persistance).getRevueDAO().getAll());
    }


    public void onModifier() {
        this.btnModifier.setDisable(true);


        btnModifier.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ModifierRevue.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 600, 330);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
                stage.setTitle("Modifier la revue");
                stage.setScene(scene);
                //stage.show();
                stage.showAndWait();

                tableRevue.getItems().clear();
                this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persistance).getRevueDAO().getAll());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        }

    public void onSupprimer(ActionEvent actionEvent) {
        this.btnSupprimer.setDisable(true);

        DAOFactory daof = DAOFactory.getDAOFactory(persistance);
        RevueDAO dao = daof.getRevueDAO();
        Boolean h = dao.delete(revueOnFocus);
        if (!h) System.out.println("Erreur de suppression");

        tableRevue.getItems().clear();
        this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persistance).getRevueDAO().getAll());

    }

    public void testNull(Revue select){
        if (!isNull(select)){revueOnFocus = select;
            this.btnSupprimer.setDisable(false);
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persistance = CtrlObjet.persistanceChoice;
        label_info.setText("Welcome to JavaFX Application!");
        cb_periodicite.setFocusTraversable(false);
        this.btnSupprimer.setDisable(true);
        this.btnModifier.setDisable(true);
        this.btnVisualiser.setDisable(true);

        DAOFactory daou = DAOFactory.getDAOFactory(persistance);
        this.cb_periodicite.setItems(FXCollections.observableArrayList(daou.getPeriodiciteDAO().getAll()));

        initTableView();

        this.tableRevue.getSelectionModel(). selectedItemProperty().addListener(this);
        tableRevue.getSelectionModel().selectedItemProperty().addListener(observable -> testNull(tableRevue.getSelectionModel().getSelectedItem()));

    }



    @Override
    public void changed(ObservableValue<? extends Revue> observable, Revue oldValue, Revue newValue) {

        this.btnSupprimer.setDisable(newValue == null);
        this.btnModifier.setDisable(newValue == null);
        this.btnVisualiser.setDisable(newValue == null);

        this.btnAjouter.setDisable(newValue != null);

    }


    public void onVisualiser(ActionEvent actionEvent) {
        this.btnVisualiser.setDisable(false);

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
        });
    }

    public void onRetour(ActionEvent actionEvent) {
        Stage stage = (Stage) btRetour.getScene().getWindow();
        stage.close();
    }

    public void activeBtn(MouseEvent mouseEvent) {
        btnAjouter.setDisable(false);
    }

    public void onCSV(ActionEvent actionEvent) {
        btCSV.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ImportCSV.fxml"));

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
                stage.setTitle("Importer CSV");
                stage.setScene(scene);
                stage.showAndWait();

                tableRevue.getItems().clear();
                this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persistance).getRevueDAO().getAll());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
