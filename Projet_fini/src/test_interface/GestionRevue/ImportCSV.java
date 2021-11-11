package test_interface.GestionRevue;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.RevueDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modele.Revue;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportCSV extends Stage  implements Initializable, ChangeListener<Revue> {

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnValider;

    @FXML
    private TableView<Revue> tableRevue;

    private Persistance persitance = CtrlRevue.persistance;

    private File filePath;

    public void onImporter(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir Fichier CSV");

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);
        this.filePath = fileChooser.showOpenDialog(stage);
        String fileText = filePath.toString();

            try {
                String line = "";
                String splitBy = ",";
                BufferedReader br = null;
            System.out.println("Chemin " + fileText);
            File file = new File(fileText);
            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null)   //returns a boolean value
            {
                String[] RevueCSV = line.split(splitBy);

                DAOFactory daof1 = DAOFactory.getDAOFactory(persitance);
                RevueDAO dao1 = daof1.getRevueDAO();

                String titre = RevueCSV[0];
                String description = RevueCSV[1];
                float tarif = Float.parseFloat(RevueCSV[2]);
                String visuel = RevueCSV[3];

                Revue revue = new Revue(1, titre, description, tarif, visuel, 1);
                Boolean h = dao1.create(revue);
                if (h) System.out.println("Revue créée");

                tableRevue.getItems().clear();
                this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persitance).getRevueDAO().getAll());
                this.tableRevue.getItems().setAll(DAOFactory.getDAOFactory(persitance).getRevueDAO().getAll());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.tableRevue.getItems().addAll( DAOFactory.getDAOFactory(persitance).getRevueDAO().getAll());
    }

    public void onRetour(ActionEvent event) {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
    }

    public void onValider(ActionEvent event) {
        Stage stage = (Stage) btnValider.getScene().getWindow();
        stage.close();
    }

    @Override
    public void changed(ObservableValue<? extends Revue> observableValue, Revue revue, Revue t1) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persitance = CtrlRevue.persistance;
        DAOFactory dao = DAOFactory.getDAOFactory(persitance);
        initTableView();
        this.tableRevue.getSelectionModel(). selectedItemProperty().addListener(this);
    }

}

