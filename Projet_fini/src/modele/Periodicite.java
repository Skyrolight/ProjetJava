package modele;

public class Periodicite {

    private int id_periodicite;
    private String libelle;

    public Periodicite(String libelle) {
        this(-1, libelle);
    }

    public Periodicite(int id_periodicite, String libelle) {
        this.setIdPeriod(id_periodicite);
        this.setLibelle(libelle);
    }

    public int getId_periodicite() {
        return this.id_periodicite;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setIdPeriod(int id) {
        this.id_periodicite = id;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.trim().length() == 0) {
            throw new IllegalArgumentException("Libelle de la p�riodicit� vide !");
        }
        this.libelle = libelle;
    }

    public void TOString() {
        System.out.println(this.getId_periodicite() + " - " + this.getLibelle());
    }

    public String toString() {
        return this.libelle;
    }
/*
    @Override
    public boolean equals(Object object)
    {
        if(this.getIdPeriod() == ((Periodicite) object).getIdPeriod())
            return true;
        else return false;
    }
*/
}

