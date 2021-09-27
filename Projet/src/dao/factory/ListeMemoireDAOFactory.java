package dao.factory;

import dao.objet.AbonnementDAO;
import dao.objet.ClientDAO;
import dao.objet.PeriodiciteDAO;
import dao.objet.RevueDAO;
import dao.objetListeMemoire.ListeMemoireAbonnementDAO;
import dao.objetListeMemoire.ListeMemoireClientDAO;
import dao.objetListeMemoire.ListeMemoirePeriodiciteDAO;
import dao.objetListeMemoire.ListeMemoireRevueDAO;
import dao.objetXML.XMLAbonnementDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return ListeMemoireAbonnementDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return ListeMemoireClientDAO.getInstance();
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {	
		return ListeMemoirePeriodiciteDAO.getInstance();
	}

	@Override
	public RevueDAO getRevueDAO() {
		return ListeMemoireRevueDAO.getInstance();
	}

}
