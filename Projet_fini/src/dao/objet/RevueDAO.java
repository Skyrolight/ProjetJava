package dao.objet;

import java.util.List;

import dao.DAO;
import modele.Revue;

public interface RevueDAO extends DAO<Revue> {

    public List<Revue> getByTarif(int tarif);

}
