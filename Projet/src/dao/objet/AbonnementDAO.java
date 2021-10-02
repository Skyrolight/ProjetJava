package dao.objet;

import java.awt.List;

import dao.DAO;
import modele.Abonnement;

public interface AbonnementDAO extends DAO<Abonnement>{

	public default List getByPrix(int prix){
		return null;}
	}
