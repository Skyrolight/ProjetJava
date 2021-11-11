package dao.factory;

import dao.Persistance;
import dao.objet.AbonnementDAO;
import dao.objet.ClientDAO;
import dao.objet.PeriodiciteDAO;
import dao.objet.RevueDAO;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(Persistance cible) {

        DAOFactory daoF = null;

        switch (cible) {
            case MYSQL:
                daoF = new MySQLDAOFactory();
                break;
            case LISTE_MEMOIRE:
                daoF = new ListeMemoireDAOFactory();
                break;
            default:
                return null;
        }

        return daoF;
    }

    public abstract AbonnementDAO getAbonnementDAO();

    public abstract ClientDAO getClientDAO();

    public abstract PeriodiciteDAO getPeriodiciteDAO();

    public abstract RevueDAO getRevueDAO();
}
