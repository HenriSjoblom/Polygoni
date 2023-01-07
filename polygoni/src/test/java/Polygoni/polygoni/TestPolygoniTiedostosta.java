package Polygoni.polygoni;

import java.util.ArrayList;

/**
 * @author Henri
 *Testaa polygoni-luokan toiminnan tallentamalla polygonin pisteet ja testipisteet tiedostoon ja
 *sen jälkeen lukemalla ne tiedostosta testaa polygonin toiminnan. Tallentaa myös selvityksen tiedostoon.
 */
public class TestPolygoniTiedostosta extends AbstractPolygoniTest {

	
	/**
	 * @param pisteet
	 * @return
	 */
	@Override
	public Polygoni getPolygoni(Piste... pisteet) {
		
		Polygoni polygoni = super.getPolygoni(pisteet);
		String polygoniTiedosto = "polygoni.txt";
		TiedostonKasittelija.teePisteetTiedosto(polygoni.getKoordinaatit(), polygoniTiedosto);
		polygoni = TiedostonKasittelija.luePolygoni(polygoniTiedosto);
		return polygoni;
	}
	
	/**
	 * @param pisteet
	 * @return
	 */
	@Override
	public ArrayList<Piste> getTestPisteet(Piste... pisteet) {
		
		ArrayList<Piste> testPisteet = super.getTestPisteet(pisteet);
		String pisteetTiedosto = "pisteet.txt";
		TiedostonKasittelija.teePisteetTiedosto(testPisteet, pisteetTiedosto);
		testPisteet = TiedostonKasittelija.luePisteet(pisteetTiedosto);
		return testPisteet;
	}
	
	
	/**
	 * Tekee tulokset taulukon pisteiden sijainnista polygoniin, joka talletetaan tiedostoon
	 * ja sen jälkeen luetaan. Palauttaa loppuksi tulokset.
	 * @return int[] tulokset
	 */
	@Override
	public int[] getTulokset(Polygoni polygoni, ArrayList<Piste> testPisteet) {
		int[] tulokset = super.getTulokset(polygoni, testPisteet);
		String tiedostonNimi = "testSelvitys.txt";
		TiedostonKasittelija.teeRaportti(polygoni, testPisteet, tulokset, tiedostonNimi);
		tulokset = TiedostonKasittelija.lueRaportti(tiedostonNimi);
		return tulokset;
	}
	
}