package start;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.ClientDAO;
import modele.Adresse;
import modele.Client;

import java.text.ParseException;

public class ApplicationTest {

    public static void main(String[] args) throws ParseException {

        DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
        ClientDAO dao1 = daof1.getClientDAO();
        Client p1 = new Client(55, "dupont", "yves", new Adresse(15, "boul Malraux", "57570", "paris", "france"));

        Boolean h = dao1.create(p1);
        if (h) System.out.println("ok");

    }

}
















































/*
 * //Test Liste Mémoire DAOFactory daof =
 * DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE); PeriodiciteDAO dao =
 * daof.getPeriodiciteDAO();
 *
 * //Test create Periodicite o = new Periodicite(20,"Daily"); dao.create(o); /*
 * //Test getAll ArrayList a = new ArrayList<Abonnement>(); a = (ArrayList)
 * dao.getAll();
 *
 * //Affichage ArrayList for(int i=0;i < a.size();i++) { Periodicite g =
 * (Periodicite) a.get(i); g.TOString();}
 *
 * //Test update dao.update(o);
 *
 * //Tests MySql DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
 * RevueDAO dao1 = daof1.getRevueDAO();
 *
 * //GetbyId Revue h = dao1.getById(3); h.TOString();
 *
 * //Test delete DAOFactory daofb = DAOFactory.getDAOFactory(Persistance.MYSQL);
 * ClientDAO daob = daofb.getClientDAO();
 *
 * Client pp1 = new Client(155,"rehth "," ",1," ",5," "," ");
 *
 * Boolean k = daob.delete(pp1); if(k)System.out.println("ok");
 */

/*
 * DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL); RevueDAO dao1
 * = daof1.getRevueDAO(); Revue h = dao1.getById(2); h.TOString();
 */
/*
 * DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL); ClientDAO
 * dao1 = daof1.getClientDAO();
 *
 * Client p1 = new Client(77," "," ",1," ",5," "," ");
 *
 * Boolean h = dao1.delete(p1); if(h)System.out.println("ok");
 */

/*
 * DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL); RevueDAO dao1
 * = daof1.getRevueDAO();
 *
 * Revue p1 = new Revue(123," "," ",1," ",5);
 *
 * Boolean h = dao1.delete(p1); if(h)System.out.println("ok");
 */
/*
 * DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL); RevueDAO dao1
 * = daof1.getRevueDAO();
 * 
 * Revue p1 = new Revue(2,"KING","Good",100,"Beautiful",4);
 * 
 * Boolean h = dao1.update(p1); if(h)System.out.println("ok");
 DAOFactory daof = DAOFactory.getDAOFactory(MYSQL);
	AbonnementDAO dao = daof.getAbonnementDAO();
		
	String date1 = "2021-01-01";
	String date2 = "2022-02-02";
		
	Abonnement a = new Abonnement(12,Date.valueOf(date1),Date.valueOf(date2),2,20);
	dao.update(a);
 *
 */