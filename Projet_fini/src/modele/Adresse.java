package modele;


import process.ProcessAdresse;

public class Adresse {

    private int no_rue;
    private String voie;
    private String code_postal;
    private String ville;
    private String pays;

    private ProcessAdresse process;

    public Adresse(int no_rue, String voie, String code_postal, String ville, String pays) {
        this.process = new ProcessAdresse();
        this.no_rue = no_rue;
        this.voie = voie;
        this.code_postal = code_postal;
        this.ville = ville;
        this.pays = pays;
    }

    public int getNo_rue() {
        return no_rue;
    }

    public void setNo_rue(int no_rue) {
        this.no_rue = process.normalizeNo_rue(no_rue);
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = process.normalizeVoie(voie);
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = process.normalizeCode_postal(code_postal);
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = process.normalizeVille(ville);
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = process.normalizePays(pays);
    }

    public String toString() {
        return no_rue + ", " + voie +" "+ ville +" "+ code_postal +" "+ pays;
    }
}
