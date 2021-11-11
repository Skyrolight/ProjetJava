package test.objetMySQL;

import static dao.Persistance.MYSQL;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.factory.DAOFactory;
import dao.objet.PeriodiciteDAO;
import modele.Periodicite;

public class MySQLPeriodiciteDAOTest {

    @Test
    public void testCreatePeriodicite() {

        DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
        PeriodiciteDAO dao = daof.getPeriodiciteDAO();

        int nb1 = dao.getAll().size();

        Periodicite o = new Periodicite(3, "Decenial");
        dao.create(o);

        int nb2 = dao.getAll().size();

        assertEquals(nb1 + 1, nb2);
    }

    @Test
    public void testDeletePeriodicite() {

        DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
        PeriodiciteDAO dao = daof.getPeriodiciteDAO();

        int nb1 = dao.getAll().size();

        Periodicite o = new Periodicite(3, "Decenial");
        dao.delete(o);

        int nb2 = dao.getAll().size();

        assertEquals(nb1, nb2 + 1);
    }


}