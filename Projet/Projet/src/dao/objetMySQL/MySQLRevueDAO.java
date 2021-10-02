package dao.objetMySQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cpoa.Connexion;
import dao.objet.RevueDAO;
import modele.Periodicite;
import modele.Revue;

public class MySQLRevueDAO implements RevueDAO{

	Connexion co = new Connexion();
	
	public static MySQLRevueDAO instance;
	
	public static MySQLRevueDAO getInstance() {
		if(instance == null) {
			instance = new MySQLRevueDAO();
		}
		return instance;
	}
	
	@Override
	public Revue getById(int id) {
		Connection laConnexion = co.creeConnexion();
		try {
		/*REQUETE SIMPLE*/	
			PreparedStatement requete = laConnexion.prepareStatement("select id_revue, titre, description, tarif_numero, visuel, id_periodicite from Revue where id_revue=?");	
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery(); // execute ONLY

			while (res.next()) {		
				int id_revue = res.getInt("id_revue");
				String titre = res.getString("titre");
				String description = res.getString("description");
				int tarif_numero = res.getInt("tarif_numero");
				String visuel = res.getString("visuel");
				int id_periodicite = res.getInt("id_periodicite");

			Revue p = new Revue(id_revue, titre, description, tarif_numero, visuel, id_periodicite);
			
			return p;}
		}
		catch (SQLException sqle){
			System.out.println("Pb select : " + sqle.getMessage());
			return null;
			}
		return null;
	}

	@Override
	public boolean create(Revue objet) {
		
		Connection laConnexion = co.creeConnexion();
		try {
		PreparedStatement req = laConnexion.prepareStatement("insert into Revue (id_revue, titre, description, tarif_numero, visuel, id_periodicite) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		
		int id_revue = objet.getId_revue();
		String titre = objet.getTitre();
		String description = objet.getDescription();
		int tarif_numero = objet.getTarif_numero();
		String visuel = objet.getVisuel();
		int id_periodicite = objet.getId_periodicite();
		
		req.setInt(1, id_revue);
		req.setString(2, titre);
		req.setString(3, description);
		req.setInt(4, tarif_numero);
		req.setString(5, visuel);
		req.setInt(6, id_periodicite);
		
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
	public boolean update(Revue objet) {
		Connection laConnexion = co.creeConnexion();
	    try {
	    	int id = objet.getId_revue();
	    	PreparedStatement req = laConnexion.prepareStatement("update Revue set titre=?, description=?, tarif_numero=?, visuel=?, id_periodicite=? where id_revue=? ");
	    	
			String titre = objet.getTitre();
			String description = objet.getDescription();
			int tarif_numero = objet.getTarif_numero();
			String visuel = objet.getVisuel();
			int id_periodicite = objet.getId_periodicite();
			
			req.setString(1, titre);
			req.setString(2, description);
			req.setInt(3, tarif_numero);
			req.setString(4, visuel);
			req.setInt(5, id_periodicite);
			req.setInt(6, id);
	    	
	        int nbLignes = req.executeUpdate();
	        return nbLignes==1;
	        }
	     catch (SQLException sqle) {
	        System.out.println("Pb select" + sqle.getMessage());
	        return false;
	        }
	}

	@Override
	public boolean delete(Revue objet) {
		Connection laConnexion = co.creeConnexion();
		try {
			/*SUPRESSION*/
			int id = objet.getId_revue();
			PreparedStatement requete0 = laConnexion.prepareStatement("delete from Revue where id_revue=?");
			requete0.setInt(1, id);
			int nbLignes = requete0.executeUpdate(); // execute & update
			return nbLignes==1;
		}
		catch (SQLException sqle){
			System.out.println("Pb select" + sqle.getMessage());
			return false;}
	}

	@Override
	public List<Revue> getAll() {
		Connection laConnexion = co.creeConnexion();
		try {
			List<Revue> L = new ArrayList<>();
			/*REQUETE SIMPLE*/
			PreparedStatement requete = laConnexion.prepareStatement("select id_revue, titre, description, tarif_numero, visuel, id_periodicite from Revue");	
			ResultSet res = requete.executeQuery(); // execute ONLY

			/*AFFICHAGE*/		
				while (res.next()) {
					int id_revue = res.getInt("id_revue");
					String titre = res.getString("titre");
					String description = res.getString("description");
					int tarif_numero = res.getInt("tarif_numero");
					String visuel = res.getString("visuel");
					int id_periodicite = res.getInt("id_periodicite");
					
					Revue P = new Revue(id_revue, titre, description, tarif_numero, visuel, id_periodicite);
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

	@Override
	public List<Revue> getByTarif(int tarif) {
		Connection laConnexion = co.creeConnexion();
		try {
			List<Revue> L = new ArrayList<>();
			/*REQUETE SIMPLE*/
			PreparedStatement requete = laConnexion.prepareStatement("select id_revue, titre, description, tarif_numero, visuel, id_periodicite from Revue where tarif_numero = ?");	
			requete.setInt(1, tarif);
			ResultSet res = requete.executeQuery(); // execute ONLY

			/*AFFICHAGE*/		
				while (res.next()) {
					int id_revue = res.getInt("id_revue");
					String titre = res.getString("titre");
					String description = res.getString("description");
					int tarif_numero = res.getInt("tarif_numero");
					String visuel = res.getString("visuel");
					int id_periodicite = res.getInt("id_periodicite");
					
					Revue P = new Revue(id_revue, titre, description, tarif_numero, visuel, id_periodicite);
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


