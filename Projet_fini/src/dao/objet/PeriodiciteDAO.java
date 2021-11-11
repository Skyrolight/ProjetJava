package dao.objet;

import java.util.List;

import dao.DAO;
import modele.Periodicite;

public interface PeriodiciteDAO extends DAO<Periodicite> {

    public List<Periodicite> getByLibelle(String lib);
}
