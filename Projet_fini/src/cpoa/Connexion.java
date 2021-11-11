package cpoa;

import java.sql.*;

import modele.Periodicite;

public class Connexion {

    public Connection creeConnexion() {

        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/tempesti3u_td1_java";
        String login = "tempesti3u_appli";
        String pwd = "phpmyadmin";
        Connection maConnexion = null;
        try {
            maConnexion = DriverManager.getConnection(url, login, pwd);
            /*System.out.println("Connection enable");*/
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion" + sqle.getMessage());
        }
        return maConnexion;
    }

    public void uneRequete() {
        Connection laConnexion = creeConnexion();
        //insertPeriod(4,"Mois");
        //deletePeriod(4);
        //modifPeriod(4,"Jour");
        verifPeriod();
    }


    public boolean insertPeriod(int id, String lib) {
        Connection laConnexion = creeConnexion();
        try {
            PreparedStatement req = laConnexion.prepareStatement("insert into Periodicite (id_periodicite,libelle) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            req.setInt(1, 4);
            req.setString(2, "Ann�e");
            int nbLigne = req.executeUpdate();

            ResultSet res0 = req.getGeneratedKeys();/*Permet de savoir quelle cl� a �t� g�ner�e*/
            if (res0.next()) {
                int cle = res0.getInt(1);
            }
            return nbLigne == 1;
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            return false;
        }
    }

    public boolean deletePeriod(int id) {
        Connection laConnexion = creeConnexion();
        try {
            /*SUPRESSION*/
            PreparedStatement requete0 = laConnexion.prepareStatement("delete from Periodicite where id_periodicite=?");
            requete0.setInt(1, id);
            int nbLignes = requete0.executeUpdate(); // execute & update
            return nbLignes == 1;
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
            return false;
        }
    }

    public void modifPeriod(int id, String lib) {
        Connection laConnexion = creeConnexion();
        try {
            PreparedStatement requete = laConnexion.prepareStatement("update Periodicite set libelle=? where id_periodicite=? ");
            requete.setString(1, lib);
            requete.setInt(2, id);
            int nbLignes = requete.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

    public void verifPeriod() {
        Connection laConnexion = creeConnexion();
        try {
            /*REQUETE SIMPLE*/
            PreparedStatement requete = laConnexion.prepareStatement("select id_periodicite, libelle from Periodicite where id_periodicite = 3");
            ResultSet res = requete.executeQuery(); // execute ONLY

            /*AFFICHAGE*/
            while (res.next()) {
                int id = res.getInt("id_periodicite");
                String libelle = res.getString("libelle");
                System.out.println(id + " " + libelle);
            }

            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select " + sqle.getMessage());
        }
    }


}

