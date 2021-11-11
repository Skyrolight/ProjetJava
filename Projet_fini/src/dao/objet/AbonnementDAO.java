package dao.objet;

import java.sql.Date;
import java.util.List;

import dao.DAO;
import modele.Abonnement;

public interface AbonnementDAO extends DAO<Abonnement> {

    public abstract List<Abonnement> getByDateDeb(Date date);

}
