package modele;

import java.sql.Date;

public class Abonnement {
    private int id_abonnement;
    private Date date_debut;
    private Date date_fin;
    private int id_client;
    private int id_revue;

    public Abonnement(int id_abonnement, Date date_debut, Date date_fin, int id_client, int id_revue) {
        this.id_abonnement = id_abonnement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_client = id_client;
        this.id_revue = id_revue;
    }

    public Abonnement(Date date_debut, Date date_fin, int id_client, int id_revue) {
        this(-1, date_debut, date_fin, id_client, id_revue);
    }

    public int getId_abonnement() {
        return this.id_abonnement;
    }

    public Date getdate_debut() {
        return this.date_debut;
    }

    public Date getdate_fin() {
        return this.date_fin;
    }

    public int getid_client() {
        return this.id_client;
    }

    public int getid_revue() {
        return this.id_revue;
    }

    public void setDate_debut(Date date_debut) {
        if ((Date)date_debut == null) {
            throw new IllegalArgumentException("Date vide");
        }
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        if ((Date)date_fin == null) {
            throw new IllegalArgumentException("Date vide");
        }
        this.date_fin = date_fin;
    }

    public void setId_abonnement(int id_abonnement) {
        if ((Integer)id_abonnement == null) {
            throw new IllegalArgumentException("Id abonnement vide");
        }
        this.id_abonnement = id_abonnement;
    }

    public void setId_client(int id_client) {
        if ((Integer)id_client == null) {
            throw new IllegalArgumentException("Id client vide");
        }
        this.id_client = id_client;
    }

    public void setId_revue(int id_revue) {
        if ((Integer)id_revue == null) {
            throw new IllegalArgumentException("Id revue vide");
        }
        this.id_revue = id_revue;
    }

    public String toString(){
        return "(" + (this.id_client>=0?this.id_client:"nouveau") + ")" + this.date_debut + " - " + this.date_fin + " - " +this.id_abonnement + " - " + this.id_client + " - " + this.id_revue;
    }
    
    public void TOString() {
    	System.out.println(this.getId_abonnement()+" - "+this.getdate_debut()+" - "+this.getdate_fin()+" - "+this.getid_client()+" - "+this.getid_revue());}

    }
