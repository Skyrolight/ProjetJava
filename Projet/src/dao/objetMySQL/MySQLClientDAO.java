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
import dao.objet.ClientDAO;
import modele.Abonnement;
import modele.Client;
import modele.Periodicite;

public class MySQLClientDAO implements ClientDAO {

	Connexion co = new Connexion();
	
	public static MySQLClientDAO instance;
	
	public static MySQLClientDAO getInstance() {
		if(instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}
	
	
	@Override
	public Client getById(int id) {

		Connection laConnexion = co.creeConnexion();
		try {
		/*REQUETE SIMPLE*/	
			PreparedStatement requete = laConnexion.prepareStatement("select id_client, nom, prenom, no_rue, voie, code_postal, ville, pays from Client where id_client=?");	
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery(); // execute ONLY

			while (res.next()) {
			    int id_client = res.getInt("id_client");
			    String nom = res.getString("nom");
			    String prenom = res.getString("prenom");
			    int no_rue = res.getInt("no_rue");
			    String voie = res.getString("voie");
			    int code_postal = res.getInt("code_postal");
			    String ville = res.getString("ville");
			    String pays = res.getString("pays");

			Client p = new Client(id_client, nom, prenom, no_rue, voie, code_postal, ville, pays);
			
			return p;}
		}
		catch (SQLException sqle){
			System.out.println("Pb select : " + sqle.getMessage());
			return null;
			}
		return null;	
	}

	@Override
	public boolean create(Client objet) {
		
		Connection laConnexion = co.creeConnexion();
		try {
		PreparedStatement req = laConnexion.prepareStatement("insert into Client (id_client, nom, prenom, no_rue, voie, code_postal, ville, pays) values(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		
		int id_client = objet.getId_client();
	    String nom = objet.getNom();
	    String prenom = objet.getPrenom();
	    int no_rue = objet.getNo_rue();
	    String voie = objet.getVoie();
	    int code_postal = objet.getCode_postal();
	    String ville = objet.getVille();
	    String pays = objet.getPays();
		
		req.setInt(1, id_client);
		req.setString(2, nom);
		req.setString(3, prenom);
		req.setInt(4, no_rue);
		req.setString(5, voie);
		req.setInt(6, code_postal);
		req.setString(7, ville);
		req.setString(8, pays);
		
		int nbLigne = req.executeUpdate();
		
		ResultSet res0 = req.getGeneratedKeys();/*Permet de savoir quelle cl� a �t� g�ner�e*/
		if (res0.next()) {
		int cle = res0.getInt(1);}
		return nbLigne==1;
		}
		catch (SQLException sqle){
			System.out.println("Pb select : " + sqle.getMessage());
			return false;}
		
		
	}

	@Override
	public boolean update(Client objet) {
		Connection laConnexion = co.creeConnexion();
	    try {
	    	int id = objet.getId_client();
	    	PreparedStatement req = laConnexion.prepareStatement("update Client set nom=?, prenom=?, no_rue=?, voie=?, code_postal=?, ville=?, pays=? where id_client=? ");
	    	
		    String nom = objet.getNom();
		    String prenom = objet.getPrenom();
		    int no_rue = objet.getNo_rue();
		    String voie = objet.getVoie();
		    int code_postal = objet.getCode_postal();
		    String ville = objet.getVille();
		    String pays = objet.getPays();
			
			req.setString(1, nom);
			req.setString(2, prenom);
			req.setInt(3, no_rue);
			req.setString(4, voie);
			req.setInt(5, code_postal);
			req.setString(6, ville);
			req.setString(7, pays);
			req.setInt(8, id);
	    	
	        int nbLignes = req.executeUpdate();
	        return nbLignes==1;
	        }
	     catch (SQLException sqle) {
	        System.out.println("Pb select" + sqle.getMessage());
	        return false;
	        }
	}

	@Override
	public boolean delete(Client objet) {
		Connection laConnexion = co.creeConnexion();
		try {
			/*SUPRESSION*/
			int id = objet.getId_client();
			PreparedStatement requete0 = laConnexion.prepareStatement("delete from Client where id_client=?");
			requete0.setInt(1, id);
			int nbLignes = requete0.executeUpdate(); // execute & update
			return nbLignes==1;
		}
		catch (SQLException sqle){
			System.out.println("Pb select" + sqle.getMessage());
			return false;}
	}


	@Override
	public List<Client> getAll() {
		Connection laConnexion = co.creeConnexion();
		try {
			List<Client> L = new ArrayList<>();
			/*REQUETE SIMPLE*/
			PreparedStatement requete = laConnexion.prepareStatement("select id_client, nom, prenom, no_rue, voie, code_postal, ville, pays from Client");	
			ResultSet res = requete.executeQuery(); // execute ONLY

			/*AFFICHAGE*/		
				while (res.next()) {
					int id_client = res.getInt("id_client");
				    String nom = res.getString("nom");
				    String prenom = res.getString("prenom");
				    int no_rue = res.getInt("no_rue");
				    String voie = res.getString("voie");
				    int code_postal = res.getInt("code_postal");
				    String ville = res.getString("ville");
				    String pays = res.getString("pays");					
					
					Client P = new Client(id_client, nom, prenom, no_rue, voie, code_postal, ville, pays);
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
