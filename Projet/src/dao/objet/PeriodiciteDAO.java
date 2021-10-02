package dao.objet;

import java.awt.List;

import dao.DAO;
import modele.Periodicite;

public interface PeriodiciteDAO extends DAO<Periodicite>{

	public default List getByLibelle(String lib){
		return null;
	}
		
	
}
