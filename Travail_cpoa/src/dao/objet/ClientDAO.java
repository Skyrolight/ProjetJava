package dao.objet;

import java.util.List;

import dao.DAO;
import modele.Client;

public interface ClientDAO extends DAO<Client>{

	public List<Client> getByNomPenom(String nom,String prenom);

	}
	
