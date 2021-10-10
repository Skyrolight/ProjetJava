package start;

import static dao.Persistance.MYSQL;
import static org.junit.Assert.assertEquals;

import modele.Periodicite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.objet.AbonnementDAO;
import dao.objet.ClientDAO;
import dao.objet.PeriodiciteDAO;
import dao.objet.RevueDAO;
import modele.Abonnement;
import modele.Adresse;
import modele.Revue;
import modele.Client;

import java.util.Scanner;


public class ApplicationTest {

	public static void main(String[] args) {
		ArrayList<Abonnement> listAbonnement = new ArrayList<Abonnement>();
		ArrayList<Client> listClient = new ArrayList<Client>();
		ArrayList<Periodicite> listPeriodicite = new ArrayList<Periodicite>();
		ArrayList<Revue> listRevue = new ArrayList<Revue>();

		Persistance bdd = BDD();
		Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
	}

	public static Persistance BDD() {	
		System.out.println("Choisissez la BDD : ");
		System.out.println("1. MYSQL ");
		System.out.println("2. Liste memoire");
		Scanner sc = new Scanner(System.in);
		String bdd_choix = sc.nextLine();
		switch(bdd_choix) {
		  case "1":
			  System.out.println("Vous avez choisi MYSQL ! ");
			  return Persistance.MYSQL;
		  case "2":
			  System.out.println("Vous avez choisi Liste memoire ! ");
			  return Persistance.LISTE_MEMOIRE;
			  default:
				  System.out.println("Erreur: MYSQL choisi par defaut");
				  return Persistance.MYSQL;
			}
	}	

	public static void Choix(Persistance bdd, ArrayList<Abonnement> listAbonnement, ArrayList<Client> listClient, ArrayList<Periodicite> listPeriodicite, ArrayList<Revue> listRevue) {

		System.out.println("\n\nChoisissez une table : ");
		System.out.println("1. Abonnement");
		System.out.println("2. Client");
		System.out.println("3. Periodicite");
		System.out.println("4. Revue");
		Scanner sc = new Scanner(System.in);
		int table_choix = sc.nextInt();
		switch(table_choix) {
			case 1:
					AbonnementMYSQL(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case 2:
					ClientMYSQL(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case 3:
					PeriodiciteMYSQL(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case 4:
					RevueMYSQL(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
		}
	}
	
	public static void AbonnementMYSQL(Persistance bdd, ArrayList<Abonnement> listAbonnement, ArrayList<Client> listClient, ArrayList<Periodicite> listPeriodicite, ArrayList<Revue> listRevue) {
		DAOFactory daof = DAOFactory.getDAOFactory(bdd);
		AbonnementDAO aboDAO = daof.getAbonnementDAO();
		System.out.println("\n\nEntrer une action : ");
		System.out.println("1. Rechercher par ID");
		System.out.println("2. Creer");
		System.out.println("3. Modifier");
		System.out.println("4. Supprimer");
		System.out.println("5. Toute la liste");
		System.out.println("6. Retour");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		switch(choix) {
			case "1":
				System.out.println("Entrer un ID");
				int choixID = sc.nextInt();
				aboDAO.getById(choixID);
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "2":
				System.out.print("\nEntrer un ID: ");
					int id = sc.nextInt();
				System.out.print("\nEntrer une date de debut sous la forme <yyyy-mm-dd>: ");
					String dateDeb = sc.nextLine();
				System.out.print("\nEntrer une date de fin sous la forme <yyyy-mm-dd>");
					String dateF = sc.nextLine();
				System.out.print("\nEntrer un ID Client");
					int idClient = sc.nextInt();
				System.out.print("\nEntrer un ID Revue");
					int idRevue = sc.nextInt();

					DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy-mm-dd");
					LocalDate dateDebut = LocalDate.parse(dateDeb, formatage);
					LocalDate dateFin = LocalDate.parse(dateF, formatage);
					
				Abonnement abb = new Abonnement(id, java.sql.Date.valueOf(dateDebut), java.sql.Date.valueOf(dateFin), idClient, idRevue);
				aboDAO.create(abb);
				System.out.println("\n Abonnement cree !");
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "3":
				
			case "4":

			case "5":
				listAbonnement = (ArrayList) aboDAO.getAll();
				for(int i=0;i < listClient.size();i++) 
				{
				Client c = (Client) listClient.get(i);
				c.TOString();}

			case "6":
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
				
			default:
				System.out.println("\n !!! Erreur d'entrer !!!\nVeuillez recommencer");
				break;
		}
	}
	
	public static void ClientMYSQL(Persistance bdd, ArrayList<Abonnement> listAbonnement, ArrayList<Client> listClient, ArrayList<Periodicite> listPeriodicite, ArrayList<Revue> listRevue) {
		DAOFactory daof = DAOFactory.getDAOFactory(bdd);
		ClientDAO clDAO = daof.getClientDAO();
		System.out.println("\n\nEntrer une action : ");
		System.out.println("1. Selectionner");
		System.out.println("2. Creer");
		System.out.println("3. Modifier");
		System.out.println("4. Supprimer");
		System.out.println("5. Toute la liste");
		System.out.println("6. Retour");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		switch(choix) {
			case "1":
				System.out.println("Entrer un ID");
				int choixID = sc.nextInt();
				clDAO.getById(choixID);
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "2":
			System.out.println("\nEntrer un ID client");
				int idClient = sc.nextInt();
			System.out.print("\nEntrer un nom: ");
				String nom = sc.nextLine();
			System.out.print("\nEntrer un prenom: ");
				String prenom = sc.nextLine();
			System.out.print("\nEntrer un numero de rue: ");
				int noRue = sc.nextInt();
			System.out.print("\nEntrer un nom de voie: ");
				String voie = sc.nextLine();
			System.out.print("\nEntrer un code postal: ");
				String cp = sc.nextLine();
			System.out.print("\nEntrer un nom de ville");
				String ville = sc.nextLine();
			System.out.print("\nEntrer un pays");
				String pays = sc.nextLine();

				Adresse ad = new Adresse(noRue, voie, cp, ville, pays);

				Client cl = new Client(idClient, nom, prenom, ad);
				clDAO.create(cl);
				System.out.println("\n Client cree !");
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "3":
			case "4":
				/*System.out.println("Entrer un ID");
				Scanner sc2 = new Scanner(System.in);
				int nb = sc2.nextInt();
				clDAO.delete();
				System.out.println("Client :"+listClient.get(nb)+" à ete supprime !");
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);*/
			case "5":
				listClient = (ArrayList) clDAO.getAll();
				for(int i=0;i < listClient.size();i++) 
				{
				Client c = (Client) listClient.get(i);
				c.TOString();}

			case "6":
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
				
			default:
				System.out.println("\n !!! Erreur d'entrer !!!\nVeuillez recommencer");
				break;
		}
	}
	
	public static void PeriodiciteMYSQL(Persistance bdd, ArrayList<Abonnement> listAbonnement, ArrayList<Client> listClient, ArrayList<Periodicite> listPeriodicite, ArrayList<Revue> listRevue) {
		DAOFactory daof = DAOFactory.getDAOFactory(bdd);
		PeriodiciteDAO perDAO = daof.getPeriodiciteDAO();
		System.out.println("\n\nEntrer une action : ");
		System.out.println("1. Selectionner");
		System.out.println("2. Creer");
		System.out.println("3. Modifier");
		System.out.println("4. Supprimer");
		System.out.println("5. Toute la liste");
		System.out.println("6. Retour");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		switch(choix) {
			case "1":
				System.out.println("Entrer un ID");
				int choixID = sc.nextInt();
				perDAO.getById(choixID);
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "2":
				System.out.println("\nEntrer un ID Periodicite");
					int idPerio = sc.nextInt();
				System.out.print("\nEntrer un libelle: ");
					String libelle = sc.nextLine();

					Periodicite pe = new Periodicite(idPerio, libelle);
					perDAO.create(pe);
					System.out.println("\n Periodicite cree !");
					Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "3":
				
			case "4":

			case "5":
				listPeriodicite = (ArrayList) perDAO.getAll();
				for(int i=0;i < listPeriodicite.size();i++) 
				{
				Periodicite p = (Periodicite) listPeriodicite.get(i);
				p.TOString();}

			case "6":
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
				
			default:
				System.out.println("\n !!! Erreur d'entrer !!!\nVeuillez recommencer");
				break;
		}
	}

	public static void RevueMYSQL(Persistance bdd, ArrayList<Abonnement> listAbonnement, ArrayList<Client> listClient, ArrayList<Periodicite> listPeriodicite, ArrayList<Revue> listRevue) {
		DAOFactory daof = DAOFactory.getDAOFactory(bdd);
		RevueDAO reDAO = daof.getRevueDAO();
		System.out.println("\n\nEntrer une action : ");
		System.out.println("1. Selectionner");
		System.out.println("2. Creer");
		System.out.println("3. Modifier");
		System.out.println("4. Supprimer");
		System.out.println("5. Toute la liste");
		System.out.println("6. Retour");
		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		switch(choix) {
			case "1":
				System.out.println("Entrer un ID");
				int choixID = sc.nextInt();
				reDAO.getById(choixID);
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "2":
				System.out.println("\nEntrer un ID Revue");
					int idRevue = sc.nextInt();
				System.out.print("\nEntrer un titre: ");
					String titre = sc.nextLine();
				System.out.print("\nEntrer une description: ");
					String description = sc.nextLine();
				System.out.print("\nEntrer un tarif: ");
					int tarif = sc.nextInt();
				System.out.print("\nEntrer un visuel: ");
					String visuel = sc.nextLine();
				System.out.print("\nEntrer un ID Periodicite: ");
					int idPerio = sc.nextInt();

				Revue revue = new Revue(idRevue, titre, description, tarif, visuel, idPerio);
				reDAO.create(revue);
				System.out.println("\n Periodicite cree !");
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
			case "3":
				
			case "4":

			case "5":
				listRevue = (ArrayList) reDAO.getAll();
				for(int i=0;i < listRevue.size();i++) 
				{
				Revue r = (Revue) listRevue.get(i);
				r.TOString();}
			case "6":
				Choix(bdd, listAbonnement, listClient, listPeriodicite, listRevue);
				
			default:
				System.out.println("\n !!! Erreur d'entrer !!!\nVeuillez recommencer");
				break;
		}
	}
}

//PeriodiciteDAO dao = daof.getPeriodiciteDAO();





/*System.out.print(daof.getPeriodiciteDAO().getAll().size());
PeriodiciteDAO dao = daof.getPeriodiciteDAO();
Periodicite o = new Periodicite("Daily");
dao.create(o);
System.out.print(daof.getPeriodiciteDAO().getAll().size());*/

/*
//Test Liste Mémoire
DAOFactory daof = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
PeriodiciteDAO dao = daof.getPeriodiciteDAO();

//Test create
Periodicite o = new Periodicite(20,"Daily");
dao.create(o);

//Test getAll
ArrayList a = new ArrayList<Abonnement>();
a = (ArrayList) dao.getAll();

//Affichage ArrayList
for(int i=0;i < a.size();i++) 
	{
	Periodicite g = (Periodicite) a.get(i);
	g.TOString();}

//Test update
dao.update(o);

//Tests MySql
DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
RevueDAO dao1 = daof1.getRevueDAO();

//GetbyId
Revue h = dao1.getById(2);
h.TOString();

//Test delete
DAOFactory daofb = DAOFactory.getDAOFactory(Persistance.MYSQL);
ClientDAO daob = daofb.getClientDAO();

Client pp1 = new Client(77," "," ",1," ",5," "," ");

Boolean k = daob.delete(pp1);
if(k)System.out.println("ok");
*/









/*
DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
RevueDAO dao1 = daof1.getRevueDAO();
Revue h = dao1.getById(2);
h.TOString();*/
/*	
DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
ClientDAO dao1 = daof1.getClientDAO();

Client p1 = new Client(77," "," ",1," ",5," "," ");

Boolean h = dao1.delete(p1);
if(h)System.out.println("ok");*/

/*DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
RevueDAO dao1 = daof1.getRevueDAO();

Revue p1 = new Revue(123," "," ",1," ",5);

Boolean h = dao1.delete(p1);
if(h)System.out.println("ok");*/
/*
DAOFactory daof1 = DAOFactory.getDAOFactory(Persistance.MYSQL);
RevueDAO dao1 = daof1.getRevueDAO();

Revue p1 = new Revue(2,"KING","Good",100,"Beautiful",4);

Boolean h = dao1.update(p1);
if(h)System.out.println("ok");*/