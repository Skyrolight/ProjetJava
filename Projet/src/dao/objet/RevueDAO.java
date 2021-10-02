package dao.objet;

import java.awt.List;

import dao.DAO;
import modele.Revue;

public interface RevueDAO extends DAO<Revue>{

	public default List getByTarif(int tarif){
		return null;
	}
	
}
