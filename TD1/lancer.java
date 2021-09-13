package TD1;

public class lancer {
	
	public static void main(String[] args) {
		
		Periodicite p = new Periodicite();
		Abonnement ab = new Abonnement();
		
		p.AddPerio("truc");
		//p.SuppPerio(11);
		//p.ModPerio(21, "bidule");

		ab.AddAbo("2002-04-01", "2002-04-02", 1, 1);
		ab.SuppAbo(1);
	}
}
