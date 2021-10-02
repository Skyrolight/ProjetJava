package dao.objet;

import java.awt.List;

import dao.DAO;
import modele.Client;

public interface ClientDAO extends DAO<Client>{

	public default List getByNomPenom(String nom,String prenom){
		return null;}
	}
