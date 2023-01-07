package Polygoni.polygoni;

import java.util.ArrayList;
import org.junit.Test;



/**
 * @author Henri
 *Testaa polygonin ja pisteiden luvun tiedostosta sekä kirjoituksen tiedostoon.
 */
public class TestTiedostonKasittelija {
	
	
	@Test
	public void testPisteidenLuku() {
		String tiedosto = "pisteet.txt";
		TiedostonKasittelija.luePisteet(tiedosto);		
	}
	
	@Test
	public void testPolygoninLuku() {
		String tiedosto = "polygoni.txt";
		TiedostonKasittelija.luePolygoni(tiedosto);
	}

	@Test
	public void testPisteidenKirjoitus() {
		
		String pisteetTiedosto = "pisteet.txt";
		ArrayList<Piste> pisteet = TiedostonKasittelija.luePisteet(pisteetTiedosto);
		
		String kohdeTiedosto = "testPisteet.txt";
		TiedostonKasittelija.teePisteetTiedosto(pisteet, kohdeTiedosto);
	}
	
	@Test
	public void testLueRaportti() {
		
		String RaporttiTiedosto = "selvitys.txt";
		int[] tulokset = TiedostonKasittelija.lueRaportti(RaporttiTiedosto);
		
	}
	
}
