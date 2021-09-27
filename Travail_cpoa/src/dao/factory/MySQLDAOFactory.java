package dao.factory;

import dao.objet.AbonnementDAO;
import dao.objet.ClientDAO;
import dao.objet.PeriodiciteDAO;
import dao.objet.RevueDAO;
import dao.objetMySQL.MySQLAbonnementDAO;
import dao.objetMySQL.MySQLClientDAO;
import dao.objetMySQL.MySQLPeriodiciteDAO;
import dao.objetMySQL.MySQLRevueDAO;

public class MySQLDAOFactory extends DAOFactory {
	
	@Override
	public AbonnementDAO getAbonnementDAO() {
		return MySQLAbonnementDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return MySQLPeriodiciteDAO.getInstance();
	}

	@Override
	public RevueDAO getRevueDAO() {
		return MySQLRevueDAO.getInstance();
	}}
