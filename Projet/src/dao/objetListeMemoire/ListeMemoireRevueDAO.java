package dao.objetListeMemoire;

import java.util.ArrayList;
import java.util.List;


import dao.objet.RevueDAO;

import modele.Revue;

public class ListeMemoireRevueDAO implements RevueDAO{

	private static ListeMemoireRevueDAO instance;

	private List<Revue> donnees;


	public static ListeMemoireRevueDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireRevueDAO();
		}

		return instance;
	}

	private ListeMemoireRevueDAO() {

		this.donnees = new ArrayList<Revue>();

	}

	@Override
	public Revue getById(int id) {
			int idx = this.donnees.indexOf(new Revue(id,"aa","bb",10,"cc",10));
			if (idx == -1) {
				throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
			} else {
				return this.donnees.get(idx);
			}
	}

	@Override
	public boolean create(Revue objet) {
		objet.setId_revue(1);
		
		while (this.donnees.contains(objet)) {
			objet.setId_revue(objet.getId_revue() + 1);
		}
		
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Revue objet) {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Revue objet) {
		Revue supprime;
		
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public List<Revue> getAll() {
		return (ArrayList<Revue>) this.donnees;
	}

	@Override
	public List<Revue> getByTarif(int tarif) {
		// TODO Auto-generated method stub
		return null;
	}

}
