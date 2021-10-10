package process.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modele.Adresse;
import process.ProcessAdresse;

public class ProcessAdresseTest {

	private ProcessAdresse process;
	
	@Before
	public void setUp() {
		this.process = new ProcessAdresse();
	}
	
	@Test
	public void testNormalizePaysCasSimple() {
		Adresse adresse = new Adresse(0, null, null, null, null);
		adresse.setPays("letzebuerg");
		assertEquals("Luxembourg", adresse.getPays());
	}

	@Test
	public void testNormalizePaysVide() {
		try {
			Adresse adresse = new Adresse(0, null, null, null, null);
			adresse.setPays("");
			fail("Exception non lancée !");
		} catch (IllegalArgumentException iae) {}
	}
	
	@Test
	public void testNormalizeVilleCasSimple() {
		Adresse adresse = new Adresse(0, null, null, null, null);
		adresse.setVille("montigny lés metz");
		assertEquals("Montigny-lès-Metz", adresse.getVille());
		adresse.setVille("saint julien lès metz");
		assertEquals("Saint-Julien-lès-Metz", adresse.getVille());
		adresse.setVille("saint-Julien-lès-Metz");
		assertEquals("Saint-Julien-lès-Metz", adresse.getVille());
	}
	
	@Test
	public void testVilleVide() {
		try {
			Adresse adresse = new Adresse(0, null, null, null, null);
			adresse.setVille("");
			fail("Exception non lancée !");
		} catch (IllegalArgumentException iae) {}
	}
	
	@Test
	public void testNormalizeVoie() {
		Adresse adresse = new Adresse(0, null, null, null, null);
		adresse.setVoie("boul. Beaumarchais");
		assertEquals("boulevard Beaumarchais", adresse.getVoie());
		adresse.setVoie("av. André Malraux");
		assertEquals("avenue André Malraux", adresse.getVoie());
		adresse.setVoie("FG Saint-Antoine");
		assertEquals("faubourg Saint-Antoine", adresse.getVoie());
	}
	
	@Test
	public void testVoieVide() {
		try {
			Adresse adresse = new Adresse(0, null, null, null, null);
			adresse.setVoie("");
			fail("Exception non lancée !");
		} catch (IllegalArgumentException iae) {}
	}
	
	@Test
	public void testNormalizeCode_Postal() {
		Adresse adresse = new Adresse(0, null, null, null, null);
		adresse.setCode_postal("L-2270");
		assertEquals("02270", adresse.getCode_postal());
		
		assertEquals(5, adresse.getCode_postal().length());
	}
	
	@Test
	public void testCodePostalVide() {
		try {
			Adresse adresse = new Adresse(0, null, null, null, null);
			adresse.setVoie("");
			fail("Exception non lancée !");
		} catch (IllegalArgumentException iae) {}
	}
	
	@Test
	public void testToString() {
		Adresse adresse = new Adresse(0, null, null, null, null);
		adresse.setNo_rue(3);
		adresse.setVoie("av. André Malraux");
		assertEquals("3, avenue André Malraux" , adresse.toString());
		adresse.setNo_rue(3);
		adresse.setVoie("rue des alouettes");
		assertEquals("3, rue des alouettes" , adresse.toString());
	}
	
}
