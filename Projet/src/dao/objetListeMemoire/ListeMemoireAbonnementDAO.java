package dao.objetListeMemoire;

import java.util.ArrayList;
import java.util.List;
import dao.objet.AbonnementDAO;
import modele.Abonnement;


public class ListeMemoireAbonnementDAO implements AbonnementDAO{

	private static ListeMemoireAbonnementDAO instance;

	private List<Abonnement> donnees;


	public static ListeMemoireAbonnementDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireAbonnementDAO();
		}

		return instance;
	}

	private ListeMemoireAbonnementDAO() {

		this.donnees = new ArrayList<Abonnement>();
	}
	
	@Override
	public Abonnement getById(int id) {
		int idx = this.donnees.indexOf(new Abonnement(id,null,null,10,10));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public boolean create(Abonnement objet) {
		objet.setId_abonnement(1);
		
		while (this.donnees.contains(objet)) {
			objet.setId_abonnement(objet.getId_abonnement() + 1);
		}
		
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Abonnement objet) {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Abonnement objet) {
		Abonnement supprime;
		
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public List<Abonnement> getAll() {
		return (ArrayList<Abonnement>) this.donnees;
	}
}
