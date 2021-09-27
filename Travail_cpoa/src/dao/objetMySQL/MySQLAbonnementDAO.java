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
import dao.objet.AbonnementDAO;
import modele.Abonnement;

public class MySQLAbonnementDAO implements AbonnementDAO {

	Connexion co = new Connexion();

	public static MySQLAbonnementDAO instance;

	public static MySQLAbonnementDAO getInstance() {
		if (instance == null) {
			instance = new MySQLAbonnementDAO();
		}
		return instance;
	}

	@Override
	public Abonnement getById(int id) {

		Connection laConnexion = co.creeConnexion();
		try {
			/* REQUETE SIMPLE */
			PreparedStatement requete = laConnexion.prepareStatement(
					"select id_abonnement, date_debut, date_fin, id_client, id_revue from Abonnement where id_abonnement=?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery(); // execute ONLY

			while (res.next()) {
				int ident = res.getInt("id_abonnement");
				Date date_debut = res.getDate("date_debut");
				Date date_fin = res.getDate("date_fin");
				int id_client = res.getInt("id_client");
				int id_revue = res.getInt("id_revue");

				Abonnement p = new Abonnement(ident, date_debut, date_fin, id_client, id_revue);

				return p;
			}
		} catch (SQLException sqle) {
			System.out.println("Pb select : " + sqle.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public boolean create(Abonnement objet) {
		Connection laConnexion = co.creeConnexion();
		try {
			PreparedStatement req = laConnexion.prepareStatement(
					"insert into Abonnement (id_abonnement,date_debut,date_fin,id_client,id_revue) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			int ident = objet.getId_abonnement();
			Date date_debut = objet.getdate_debut(); //dates java.sql
			Date date_fin = objet.getdate_fin(); //dates java.sql
			int id_client = objet.getid_client();
			int id_revue = objet.getid_revue();

			req.setInt(1, ident);
			req.setDate(2, date_debut);
			req.setDate(3, date_fin);
			req.setInt(4, id_client);
			req.setInt(5, id_revue);

			int nbLigne = req.executeUpdate();

			ResultSet res0 = req.getGeneratedKeys();/* Permet de savoir quelle cl� a �t� g�ner�e */
			if (res0.next()) {
				int cle = res0.getInt(1);
			}
			return nbLigne == 1;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Abonnement objet) {
		Connection laConnexion = co.creeConnexion();
		try {
			int id = objet.getId_abonnement();
			PreparedStatement requete = laConnexion.prepareStatement(
					"update Abonnement set date_debut=?,date_fin=?,id_client=?,id_revue=? where id_abonnement=? ");

			Date date_debut = objet.getdate_debut();
			Date date_fin = objet.getdate_fin();
			int id_client = objet.getid_client();
			int id_revue = objet.getid_revue();

			requete.setDate(1, date_debut);
			requete.setDate(2, date_fin);
			requete.setInt(3, id_client);
			requete.setInt(4, id_revue);
			requete.setInt(5, id);

			int nbLignes = requete.executeUpdate();
			return nbLignes == 1;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Abonnement objet) {
		Connection laConnexion = co.creeConnexion();
		try {
			/* SUPRESSION */
			int id = objet.getId_abonnement();
			PreparedStatement requete0 = laConnexion.prepareStatement("delete from Abonnement where id_abonnement=?");
			requete0.setInt(1, id);
			int nbLignes = requete0.executeUpdate(); // execute & update
			return nbLignes == 1;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}
	}

	@Override
	public List<Abonnement> getAll() {
		Connection laConnexion = co.creeConnexion();
		try {
			List<Abonnement> L = new ArrayList<>();
			/* REQUETE SIMPLE */
			PreparedStatement requete = laConnexion
					.prepareStatement("select id_abonnement,date_debut,date_fin,id_client,id_revue from Abonnement");
			ResultSet res = requete.executeQuery(); // execute ONLY

			/* AFFICHAGE */
			while (res.next()) {
				int ident = res.getInt("id_abonnement");
				Date date_debut = res.getDate("date_debut");
				Date date_fin = res.getDate("date_fin");
				int id_client = res.getInt("id_client");
				int id_revue = res.getInt("id_revue");

				Abonnement P = new Abonnement(ident, date_debut, date_fin, id_client, id_revue);
				L.add(P);
			}

			if (res != null)
				res.close();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
			return L;
		}

		catch (SQLException sqle) {
			System.out.println("Pb select " + sqle.getMessage());
		}
		return null;
	}

	@Override
	public List<Abonnement> getByDateDeb(Date date) {
		Connection laConnexion = co.creeConnexion();
		try {
			List<Abonnement> result = new ArrayList<>();
			/*REQUETE SIMPLE*/
			PreparedStatement requete = laConnexion.prepareStatement("select id_abonnement,date_debut,date_fin,id_client,id_revue from Abonnement where id_abonnement = ?");	
			requete.setDate(1, date);
			ResultSet res = requete.executeQuery(); // execute ONLY

			/*AFFICHAGE*/		
				while (res.next()) {
					int ident = res.getInt("id_abonnement");
					Date date_debut = res.getDate("date_debut");
				    Date date_fin = res.getDate("date_fin");
				    int id_client = res.getInt("id_client");
				    int id_revue = res.getInt("id_revue");
					
					Abonnement a = new Abonnement(ident,date_debut,date_fin,id_client,id_revue);
					result.add(a);}
				
				if (res != null)
				       res.close();
				if (requete != null) 
						requete.close();
				if (laConnexion != null) 
						laConnexion.close();
				return result;}
		
		catch (SQLException sqle) {
			System.out.println("Pb select " + sqle.getMessage());
		}
		return null;
	}
}
