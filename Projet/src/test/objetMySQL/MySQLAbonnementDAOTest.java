package test.objetMySQL;

import static dao.Persistance.MYSQL;
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import dao.factory.DAOFactory;
import dao.objet.AbonnementDAO;
import modele.Abonnement;


public class MySQLAbonnementDAOTest {
	
	@Test
	public void testCreateAbonnement() {
		DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
		int nb1 = daof.getAbonnementDAO().getAll().size();
		System.out.print(nb1);
		AbonnementDAO dao = daof.getAbonnementDAO();		
		Abonnement ab = new Abonnement(8, Date.valueOf("2012-04-01"), Date.valueOf("2014-02-01"), 2, 2);
		dao.create(ab);
		int nb2 = daof.getAbonnementDAO().getAll().size();
		System.out.print("\n" +nb2);
		
		assertEquals(nb1+1, nb2);
	}
	
	@Test
	public void testRemoveAbonnement() {
		DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
		int nb1 = daof.getAbonnementDAO().getAll().size();
		System.out.print(nb1);
		AbonnementDAO dao = daof.getAbonnementDAO();		
		Abonnement ab = new Abonnement(8, Date.valueOf("2012-04-01"), Date.valueOf("2014-02-01"), 2, 2);
		dao.create(ab);
		int nb2 = daof.getAbonnementDAO().getAll().size();
		System.out.print("\n" +nb2);
		
		assertEquals(nb1+1, nb2);
	}
}
