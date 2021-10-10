package modele;

import process.ProcessAdresse;

public class Client {

	private int id_client;
	private String nom;
	private String prenom;
	private Adresse adresse;
	
public Client(String nom,String prenom,Adresse adresse) {
	this(-1, nom,prenom,adresse);
	}

public Client(int id_client,String nom,String prenom,Adresse adresse) {
	this.id_client=id_client;
	this.nom=nom;
	this.prenom=prenom;
	this.adresse=adresse;
	}
	
public int getId_client() {
	return id_client; 
}
public String getNom() {
	
	return nom;
}

public Adresse getAdresse() {
	
	return adresse;
}

public String getPrenom() {
	return prenom;
}
public int getNo_rue() {
	return adresse.getNo_rue();
}
public String getVoie() {
	return adresse.getVoie();
}
public String getCode_postal() {
	return adresse.getCode_postal();
}
public String getVille() {
	return adresse.getVille();
}
public String getPays() {
	return adresse.getPays();
}

public void setId_client(int id_client) {
	this.id_client = id_client;
}
public void setNom(String nom) {
	if (nom==null || nom.trim().length()==0) {
		throw new IllegalArgumentException("Nom vide !");
		}
	this.nom = nom;
}
public void setPrenom(String prenom) {
	if (prenom==null || prenom.trim().length()==0) {
		throw new IllegalArgumentException("Prenom vide !");
		}
	this.prenom = prenom;
}

public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
}

public void TOString() {
	System.out.println(this.getId_client()+" - "+this.getNom() +" - "+this.getPrenom()+" - "+this.getNo_rue()+" - "+this.getVoie()+" - "+this.getCode_postal()+" - "+this.getVille()+" - "+this.getPays());}
}




