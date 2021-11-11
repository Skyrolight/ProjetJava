package dao.objetListeMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.objet.ClientDAO;
import modele.Adresse;
import modele.Client;
import modele.Revue;


public class ListeMemoireClientDAO implements ClientDAO {

    private static ListeMemoireClientDAO instance;

    private List<Client> donnees;

    public int i = 3;

    public static ListeMemoireClientDAO getInstance() {

        if (instance == null) {
            instance = new ListeMemoireClientDAO();
        }

        return instance;
    }

    private ListeMemoireClientDAO() {

        this.donnees = new ArrayList<Client>();
        this.donnees.add(new Client(1,"TEMPESTINI","Terry",new Adresse(14,"Grande","57570","Thionville","France")));
        this.donnees.add(new Client(2,"MULLER","Mickael",new Adresse(20,"Klein","34522","Berlin","Allemagne")));

    }

    @Override
    public Client getById(int id) {
        int idx = this.donnees.indexOf(new Client(id, "name", "family name", new Adresse(10, "rue", "57000", "Paris", "Fra")));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
        } else {
            return this.donnees.get(idx);
        }
    }

    @Override
    public boolean create(Client objet) {
        objet.setId_client(i);
        i=i+1;

        while (this.donnees.contains(objet)) {
            objet.setId_client(objet.getId_client() + 1);
        }

        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Client objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } else {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Client objet) {
        Client supprime;

        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
        } else {
            supprime = this.donnees.remove(idx);
        }

        return objet.equals(supprime);
    }

    @Override
    public List<Client> getAll() {
        return (ArrayList<Client>) this.donnees;
    }

    @Override
    public List<Client> getByNomPenom(String nom, String prenom) {
        // TODO Auto-generated method stub
        return null;
    }


}
