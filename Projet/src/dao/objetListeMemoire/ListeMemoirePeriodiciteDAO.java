package dao.objetListeMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.objet.PeriodiciteDAO;
import modele.Periodicite;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteDAO {

	private static ListeMemoirePeriodiciteDAO instance;

	private List<Periodicite> donnees;


	public static ListeMemoirePeriodiciteDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoirePeriodiciteDAO();
		}

		return instance;
	}

	private ListeMemoirePeriodiciteDAO() {

		this.donnees = new ArrayList<Periodicite>();

		this.donnees.add(new Periodicite(1, "Mensuel"));
		this.donnees.add(new Periodicite(2, "Quotidien"));
	}


	@Override
	public boolean create(Periodicite objet) {
		
		objet.setIdPeriod(1);
		
		while (this.donnees.contains(objet)) {
			objet.setIdPeriod(objet.getIdPeriod() + 1);
		}
		
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Periodicite objet) {
		
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Periodicite objet) {

		Periodicite supprime;
		
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Periodicite getById(int id) {
		int idx = this.donnees.indexOf(new Periodicite(id, "bonjour"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Periodicite> getAll() {
		return (ArrayList<Periodicite>) this.donnees;
	}
}
