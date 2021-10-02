package dao.objetMySQL;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cpoa.Connexion;
import dao.objet.PeriodiciteDAO;
import modele.Periodicite;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO{

	Connexion co = new Connexion();
	
	public static MySQLPeriodiciteDAO instance;
	
	public static MySQLPeriodiciteDAO getInstance() {
		if(instance == null) {
			instance = new MySQLPeriodiciteDAO();
		}
		return instance;
	}
	
	
	@Override
	public Periodicite getById(int id) {
		
		Connection laConnexion = co.creeConnexion();
		try {
		/*REQUETE SIMPLE*/	
			PreparedStatement requete = laConnexion.prepareStatement("select id_periodicite, libelle from Periodicite where id_periodicite=?");	
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery(); // execute ONLY

			while (res.next()) {
				int ident = res.getInt("id_periodicite");
				String libelle = res.getString("libelle");

			Periodicite p = new Periodicite(ident,libelle);
			
			return p;}
		}
		catch (SQLException sqle){
			System.out.println("Pb select : " + sqle.getMessage());
			return null;
			}
		return null;
	}

	@Override
	public boolean create(Periodicite objet) {
		
		Connection laConnexion = co.creeConnexion();
		try {
		PreparedStatement req = laConnexion.prepareStatement("insert into Periodicite (id_periodicite,libelle) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
		int i = objet.getIdPeriod();
		String j = objet.getLibelle();
		
		req.setInt(1, i);
		req.setString(2, j);
		
		int nbLigne = req.executeUpdate();
		
		ResultSet res0 = req.getGeneratedKeys();/*Permet de savoir quelle cl� a �t� g�ner�e*/
		if (res0.next()) {
		int cle = res0.getInt(1);}
		return nbLigne==1;
		}
		catch (SQLException sqle){
			System.out.println("Pb select" + sqle.getMessage());
			return false;}
		}
	
	@Override
	public boolean update(Periodicite objet) {
		Connection laConnexion = co.creeConnexion();
	    try {
	    	int id = objet.getIdPeriod();
	    	PreparedStatement requete = laConnexion.prepareStatement("update Periodicite set libelle=? where id_periodicite=? ");
	    	
	    	String libelle = objet.getLibelle();
	    	
	    	requete.setString(1, libelle);
	    	requete.setInt(2, id);
	    	
	        int nbLignes = requete.executeUpdate();
	        return nbLignes==1;
	        }
	     catch (SQLException sqle) {
	        System.out.println("Pb select" + sqle.getMessage());
	        return false;
	        }
	}

	@Override
	public boolean delete(Periodicite objet) {
		Connection laConnexion = co.creeConnexion();
		try {
			/*SUPRESSION*/
			int id = objet.getIdPeriod();
			PreparedStatement requete0 = laConnexion.prepareStatement("delete from Periodicite where id_periodicite=?");
			requete0.setInt(1, id);
			int nbLignes = requete0.executeUpdate(); // execute & update
			return nbLignes==1;
		}
		catch (SQLException sqle){
			System.out.println("Pb select" + sqle.getMessage());
			return false;}
	}

	@Override
	public List<Periodicite> getAll() {
		Connection laConnexion = co.creeConnexion();
		try {
			List<Periodicite> L = new ArrayList<>();
			/*REQUETE SIMPLE*/
			PreparedStatement requete = laConnexion.prepareStatement("select id_periodicite, libelle from Periodicite");	
			ResultSet res = requete.executeQuery(); // execute ONLY

			/*AFFICHAGE*/		
				while (res.next()) {
					int id = res.getInt("id_periodicite");
					String libelle = res.getString("libelle");
					Periodicite P = new Periodicite(id,libelle);
					L.add(P);}
				
				if (res != null)
				       res.close();
				if (requete != null) 
						requete.close();
				if (laConnexion != null) 
						laConnexion.close();
				return L;
			} 
		
		catch (SQLException sqle){
			System.out.println("Pb select " + sqle.getMessage());
			}
		return null;
	}

}