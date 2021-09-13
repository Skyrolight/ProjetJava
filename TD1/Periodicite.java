package TD1;

import java.sql.*;

public class Periodicite {

    private Connection maConnection;
    private Connexion Connection;

    public Periodicite() {
        Connection = new Connexion();
		maConnection = Connection.creeConnexion();
    }

    public void AddPerio(String nom){

        PreparedStatement requete;
        try {
            requete = maConnection.prepareStatement("insert into Periodicite (libelle) values(?)", Statement.RETURN_GENERATED_KEYS);
            requete.setString(1, nom);
            int nbLignes = requete.executeUpdate();

            ResultSet res = requete.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }
            
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            }
    }

    public void SuppPerio(int Id){

        PreparedStatement requete;
        try {
            requete = maConnection.prepareStatement("delete from Periodicite where id_periodicite=?");
            requete.setInt(1, Id);
            int nbLignes = requete.executeUpdate();
            
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            }
    }

    public void ModPerio(int Id, String nom){

        PreparedStatement requete;
        try {
            requete = maConnection.prepareStatement("update Periodicite set libelle=? where id_periodicite=? ", Statement.RETURN_GENERATED_KEYS);
            requete.setString(1, nom);
            requete.setInt(2, Id);
            int nbLignes = requete.executeUpdate();

            ResultSet res = requete.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }
            
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            }
    }
}
