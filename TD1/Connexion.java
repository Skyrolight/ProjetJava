package TD1;

import java.sql.*;

public class Connexion {
	public Connection creeConnexion() {
		String url ="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/welfring7u_CPOA";
		String login = "welfring7u_appli";
		String pwd = "welfringer";
		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
			System.out.println("Connexion établie");
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;
	}	
}