package Polygoni.polygoni;

import java.util.ArrayList;


/**
 * @author Henri
 *Lukee mainissa olevat pisteet ja polygon tiedostot ja tarkistaa,
 *  onko pisteet polygonin sisä- vai ulkopuolella. Lopuksi tekee selvitys raportin.
 */
public class Main {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		String pisteetTiedosto = "pisteet.txt";
		String polygoniTiedosto = "polygoni.txt";
		String selvitysTiedosto = "selvitys.txt";
		tarkistaPolygoni(polygoniTiedosto, pisteetTiedosto, selvitysTiedosto);	
	}	

	/**
	 * Luo tulos taulukon pisteiden sijainnista
	 * @param polygoni
	 * @param pisteet
	 * @return
	 */
	public static void tarkistaPolygoni(String polygoniTiedosto, String pisteetTiedosto, String selvitysTiedosto) {
		
		ArrayList<Piste> pisteet = TiedostonKasittelija.luePisteet(pisteetTiedosto);
		Polygoni polygoni = TiedostonKasittelija.luePolygoni(polygoniTiedosto);
		
		//Jos polygoni oli virheellinen, niin lopetetaan ohjelma.
		if (polygoni.tyhja()) {
			return;
		}
		
		int tulos[] = onkoPisteetPolygonissa(polygoni, pisteet);
		TiedostonKasittelija.teeRaportti(polygoni, pisteet, tulos, selvitysTiedosto);	
	}
	
	public static int[] onkoPisteetPolygonissa(Polygoni polygoni, ArrayList<Piste> pisteet) {
		int tulos[] = new int[pisteet.size()];
		for (int i = 0; i < pisteet.size(); ++i) {
			int sijainti = polygoni.onkoPistePolygonissa(pisteet.get(i));
			tulos[i] = sijainti;
		}
		return tulos;
	}
	
}
