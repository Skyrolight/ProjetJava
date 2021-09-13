package TD1;

import java.sql.*;

public class Abonnement {

    private Connection maConnection;
    private Connexion Connection;

    public Abonnement(){
        Connection = new Connexion();
		maConnection = Connection.creeConnexion();
    }

    public void AddAbo(String datedeb, String datefin, int IdClient, int IdRevue){

        PreparedStatement requete;
        try {
            requete = maConnection.prepareStatement("insert into Abonnement (date_debut, date_fin, id_client, id_revue) values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            requete.setString(1, datedeb);
            requete.setString(2, datefin);
            requete.setInt(3, IdClient);
            requete.setInt(4, IdRevue);
            int nbLignes = requete.executeUpdate();

            ResultSet res = requete.getGeneratedKeys();
            if (res.next()) {
                int cle = res.getInt(1);
            }
            
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            }
    }

    public void SuppAbo(int Id){

        PreparedStatement requete;
        try {
            requete = maConnection.prepareStatement("delete from Abonnement where id_abonnement=?");
            requete.setInt(1, Id);
            int nbLignes = requete.executeUpdate();
            
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            }
    }

    public void ModAbo(int Id, String nom){

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


