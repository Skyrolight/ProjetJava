package modele;

public class Revue {

    private int id_revue;
    private String titre;
    private String description;
    private float tarif_numero;
    private String visuel;
    private int id_periodicite;

    public Revue(String titre, String description, float tarif_numero, String visuel, int id_periodicite) {
        this(-1, titre, description, tarif_numero, visuel, id_periodicite);
    }

    public Revue(int id_revue, String titre, String description, float tarif_numero, String visuel, int id_periodicite) {
        this.id_revue = id_revue;
        this.titre = titre;
        this.description = description;
        this.tarif_numero = tarif_numero;
        this.visuel = visuel;
        this.id_periodicite = id_periodicite;
    }

    public int getId_revue() {
        return id_revue;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public float getTarif_numero() {
        return tarif_numero;
    }

    public String getVisuel() {
        return visuel;
    }

    public int getId_periodicite() {
        return id_periodicite;
    }

    public void setId_revue(int id_revue) {
        this.id_revue = id_revue;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.trim().length() == 0) {
            throw new IllegalArgumentException("Titre vide !");
        }
        this.titre = titre;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().length() == 0) {
            throw new IllegalArgumentException("Description vide !");
        }
        this.description = description;
    }

    public void setTarif_numero(float tarif_numero) {
        this.tarif_numero = tarif_numero;
    }

    public void setVisuel(String visuel) {
        if (visuel == null || visuel.trim().length() == 0) {
            throw new IllegalArgumentException("Visuel vide !");
        }
        this.visuel = visuel;
    }

    public void setId_periodicite(int id_periodicite) {
        if ((Integer) id_periodicite == null) {
            throw new IllegalArgumentException("Identifiant de p�riodict� vide !");
        }
        this.id_periodicite = id_periodicite;
    }

    public void TOString() {
        System.out.println(this.getId_revue() + " - " + this.getTitre() + " - " + this.getDescription() + " - " + this.getTarif_numero() + " - " + this.getVisuel() + " - " + this.getId_periodicite());
    }

    public String toString() {
        return this.titre;
    }

/*
    @Override
    public boolean equals(Object object)
    {
        if(this.getId_revue() == ((Revue) object).getId_revue())
            return true;
        else return false;
    }*/
}

