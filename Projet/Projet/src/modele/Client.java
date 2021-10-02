package modele;

public class Client {

	private int id_client;
	private String nom;
	private String prenom;
	private int no_rue;
	private String voie;
	private int code_postal;
	private String ville;
	private String pays;
	
public Client(String nom,String prenom,int no_rue,String voie,int code_postal,String ville,String pays) {
	this(-1, nom,prenom,no_rue,voie,code_postal,ville,pays);
	}

public Client(int id_client,String nom,String prenom,int no_rue,String voie,int code_postal,String ville,String pays) {
	this.id_client=id_client;
	this.nom=nom;
	this.prenom=prenom;
	this.no_rue=no_rue;
	this.voie=voie;
	this.code_postal=code_postal;
	this.ville=ville;
	this.pays=pays;
	}
	
public int getId_client() {
	return id_client;
}
public String getNom() {
	return nom;
}
public String getPrenom() {
	return prenom;
}
public int getNo_rue() {
	return no_rue;
}
public String getVoie() {
	return voie;
}
public int getCode_postal() {
	return code_postal;
}
public String getVille() {
	return ville;
}
public String getPays() {
	return pays;
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
public void setNo_rue(int no_rue) {
	this.no_rue = no_rue;
}
public void setVoie(String voie) {
	if (voie==null || voie.trim().length()==0) {
		throw new IllegalArgumentException("Voie vide !");
		}
	this.voie = voie;
}
public void setCode_postal(int code_postal) {
	this.code_postal = code_postal;
}
public void setVille(String ville) {
	if (ville==null || ville.trim().length()==0) {
		throw new IllegalArgumentException("Ville vide !");
		}
	this.ville = ville;
}
public void setPays(String pays) {
	if (pays==null || pays.trim().length()==0) {
		throw new IllegalArgumentException("Pays vide !");
		}
	this.pays = pays;
}

public void TOString() {
	System.out.println(this.getId_client()+" - "+this.getNom() +" - "+this.getPrenom()+" - "+this.getNo_rue()+" - "+this.getVoie()+" - "+this.getCode_postal()+" - "+this.getVille()+" - "+this.getPays());}
}




