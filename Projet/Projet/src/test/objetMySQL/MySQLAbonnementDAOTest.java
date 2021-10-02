package test.objetMySQL;

import static dao.Persistance.MYSQL;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.factory.DAOFactory;
import dao.objet.PeriodiciteDAO;
import modele.Periodicite;

public class MySQLAbonnementDAOTest {

	@Test
	public void testCreateAbonnement() {

		DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
		PeriodiciteDAO dao = daof.getPeriodiciteDAO();

		int nb1 = dao.getAll().size();

		Periodicite o = new Periodicite(184, "Décenial");
		dao.create(o);

		int nb2 = dao.getAll().size();
		
		assertEquals(nb1+1, nb2);
	}
	
	@Test
	public void testDeletePeriodicite() {

		DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
		PeriodiciteDAO dao = daof.getPeriodiciteDAO();

		int nb1 = dao.getAll().size();

		Periodicite o = new Periodicite(22555, "Décenial");
		dao.delete(o);

		int nb2 = dao.getAll().size();
		
		assertEquals(nb1,nb2+1);
	}
}
