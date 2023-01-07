package Polygoni.polygoni;

import org.junit.Test;


/**
 * @author Henri
 *Testaa mainin tarkistaPolygoni metodin.
 */
public class TestMain {
		
	@Test
	public void testTarkistaPolygoni() {
		String pisteetTiedosto = "pisteet.txt";
		String polygoniTiedosto = "polygoni.txt";
		String selvitysTiedosto = "selvitys.txt";
		Main.tarkistaPolygoni(polygoniTiedosto, pisteetTiedosto, selvitysTiedosto);	
	}

}
