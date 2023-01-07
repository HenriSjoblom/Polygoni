package Polygoni.polygoni;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;


/**Abstrakti luokka, jota käyttää TestPolygoniIlmanTiedostoa ja TestPolygoniTiedostosta
 * @author Henri
 *
 */
public abstract class AbstractPolygoniTest {
	
	
	/**
	 *
	 */
	@Test
	public void testNelio() {
		
		Polygoni polygoni = getPolygoni(new Piste(0, 0), new Piste(4, 0), new Piste(4, 4),
					new Piste(0, 4));
		ArrayList<Piste> testPisteet = getTestPisteet(new Piste(2, 2), new Piste(3, 2), new Piste(3, 3), new Piste(-1, 2),
						new Piste(2, 5), new Piste(6, 0), new Piste(0, 4), new Piste(4, 2), new Piste(2, 0.01),
						new Piste(2, -0.01), new Piste(2, 0));
		
		int tulos[] = getTulokset(polygoni, testPisteet);
		int odotettuTulos[] = {1, 1, 1, -1, -1, -1, 0, 0, 1, -1, 0};
		Assert.assertArrayEquals(tulos, odotettuTulos);
	}
	
	/**
	 *
	 */
	@Test
	public void testKolmio() {
		
		Polygoni polygoni = getPolygoni(new Piste(-1, -1), new Piste(1, 0), new Piste(-1, 2));
		ArrayList<Piste> testPisteet = getTestPisteet(new Piste(-1, -1), new Piste(-1.01, -1), new Piste(-1, -0.99), new Piste(-0.99, -0.99),
						new Piste(2, 0), new Piste(0, 0), new Piste(-0.5, 1), new Piste(-0.5, -0.5));
		
		int tulos[] = getTulokset(polygoni, testPisteet);
		int odotettuTulos[] = {0, -1, 0, 1, -1, 1, 1, 1}; 
		Assert.assertArrayEquals(tulos, odotettuTulos);
	}
	
	/**
	 * 
	 */
	@Test
	public void testSuorakulmio() {
		
		Polygoni polygoni = getPolygoni(new Piste(3, -2), new Piste(7, -2), new Piste(7, 6), new Piste(3, 6));
		ArrayList<Piste> testPisteet = getTestPisteet(new Piste(3, -2), new Piste(9, -2), new Piste(9, 6),
						new Piste(4, 6), new Piste(5, 2), new Piste(4, 1.5), new Piste(3.5, -1));
		
		int tulos[] = getTulokset(polygoni, testPisteet);
		int odotettuTulos[] = {0, -1, -1, 0, 1, 1, 1};
		Assert.assertArrayEquals(tulos, odotettuTulos);
	}
	
	/**
	 * Oman yhdeksän pisteisen polygonin testi. Monikulmio on vähän bumerangin muotoinen ja siinä on myös kuperuutta.
	 */
	@Test
	public void testBumerang() {
		
		Polygoni polygoni = getPolygoni(new Piste(1, 2), new Piste(2, 3), new Piste(3, 6), new Piste(2, 4), new Piste(0, 3),
				new Piste(-2, 2), new Piste(-3, -4), new Piste(-2 ,-2), new Piste(-1 ,0));
		
		ArrayList<Piste> testPisteet = getTestPisteet(new Piste(-1, -1), new Piste(0, 1), new Piste(0, 0),
						new Piste(0, 0.99), new Piste(0, 1.01), new Piste(1, 4), new Piste(-2, -1));
		
		int tulos[] = getTulokset(polygoni, testPisteet);
		int odotettuTulos[] = {-1, 0, -1, -1, 1, -1, 1};
		Assert.assertArrayEquals(tulos, odotettuTulos);
	}
	
	/**
	 * M -kirjaimen muotoinen monikulmio
	 */
	@Test
	public void testM() {
		
		Polygoni polygoni = getPolygoni(new Piste(-4, -4), new Piste(-2, 1), new Piste(0, -1), new Piste(2, 1), new Piste(4, -4),
				new Piste(3, 3), new Piste(0, 0), new Piste(-3 ,3));
		
		ArrayList<Piste> testPisteet = getTestPisteet(new Piste(-3, -0), new Piste(0, 0), new Piste(3.5, -2),
				new Piste(1, 2), new Piste(2, 2), new Piste(2.01, 2), new Piste(2, 2.01));
		
		int tulos[] = getTulokset(polygoni, testPisteet);
		int odotettuTulos[] = {1, 0, 1, -1, 0, 1, -1};
		Assert.assertArrayEquals(tulos, odotettuTulos);		
	}	
		
	/**
	 * Palauttaa testipisteet listassa.
	 * @param pisteet
	 * @return arrayList
	 */
	public ArrayList<Piste> getTestPisteet(Piste... pisteet) {
		return getPisteetList(pisteet);
	}
	
	/**
	 * Palauttaa annetut pisteet listassa.
	 * @param pisteet
	 * @return arrayList pisteet
	 */
	public ArrayList<Piste> getPisteetList(Piste... pisteet) {
	
		ArrayList<Piste> pisteetLista = new ArrayList<Piste>();			
			for (Piste piste : pisteet) {
				pisteetLista.add(piste);		
			}
		return pisteetLista;
	}
	
	/**
	 * Palauttaa polygonin annetuilla pisteillä
	 * @param nimi
	 * @return polygoni
	 */
	public Polygoni getPolygoni(Piste... pisteet) {
		
		ArrayList<Piste> polygoniPisteet = getPisteetList(pisteet);
		return new Polygoni(polygoniPisteet);
	}
	
	/**
	 * Palauttaa tulokset pisteiden sijainnista suhteessa polygoniin.
	 * @return int[] tulokset
	 */
	public int[] getTulokset(Polygoni polygoni, ArrayList<Piste> testPisteet) {
		return Main.onkoPisteetPolygonissa(polygoni, testPisteet);
	}
}