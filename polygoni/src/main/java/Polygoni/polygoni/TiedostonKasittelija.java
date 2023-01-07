package Polygoni.polygoni;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Henri
 *Lukee ja kirjoittaa tiedostoja. Osaa lukea ja kirjoittaa polygonin ja pisteet tiedostot sekä
 *tehdä selvitys tiedoston.
 */
public class TiedostonKasittelija {
	
	/**
	 * Lukee tiedoston temp kansiosta. Käytetään luePisteet ja luePolygoni metodeissa.
	 * @param nimi
	 * @return
	 */
	private static ArrayList<Piste> lueTiedosto(String nimi) {
		
		ArrayList<Piste> pisteet = new ArrayList<Piste>();
		
		String tempDir = getTempDir();
		
		try (Scanner tiedostonLukija = new Scanner(Paths.get(tempDir + nimi))) {
			
		    while (tiedostonLukija.hasNextLine()) {
		    	
		        String rivi = tiedostonLukija.nextLine();
		        if (rivi.isEmpty()) {
		            continue;
		        }
		        
		        String[] koordinaatit = rivi.split(",");
		        
		        if (koordinaatit.length != 2) {
		        	System.out.println("Virheellinen piste tiedostossa. Virhe: Väärä määrä koordinaatteja");
		        }
		        else {        	
		        	try {
		        		float x = Float.parseFloat(koordinaatit[0]);
		        		float y = Float.parseFloat(koordinaatit[1]);
		        		Piste uusiPiste = new Piste(x, y);
		        		pisteet.add(uusiPiste);
		        		
		        	}	
	        		catch (Exception e2) {
	        			throw new RuntimeException(e2); 
	        		}	        	
		        }
		    }
		} catch (Exception e) {
			throw new RuntimeException(e); 
		}	
	return pisteet;	
	}
	

	/**
	 * @param tiedosto
	 * @return
	 */
	public static ArrayList<Piste> luePisteet(String tiedosto) {
		ArrayList<Piste> pisteet = lueTiedosto(tiedosto);
		return pisteet;
	}
		
	/**Lukee polygonin tiedostosta. Tiedostossa oletetaan olevan polygonin pisteet, yhden pisteen X, Y koordinaatit yhdellä rivillä.
	 * @param nimi
	 * @return
	 */
	public static Polygoni luePolygoni(String nimi) {
		
		Polygoni polygoni;
		ArrayList<Piste> pisteet = lueTiedosto(nimi);
		
		//Polygonissa täytyy olla vähintään kolme kulmaa
		assert(pisteet.size() > 2);

		polygoni = new Polygoni(pisteet);
		return polygoni;
	}
	
	/**
	 * Kirjoittaa selvityksen temp-kansioon annetulla nimellä.
	 * @param polygoni
	 * @param pisteet
	 * @param tulos
	 */
	public static void teeRaportti(Polygoni polygoni, ArrayList<Piste> pisteet, int[] tulos, String nimi) {
      
		
		String tempDir = getTempDir();
		
		try {
			BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter(tempDir + nimi));
			kirjoitaPolygonRivi(kirjoittaja, polygoni);
			for (int i=0; i < pisteet.size(); i++) {
				kirjoittaja.newLine();
				kirjoitaPisteRivi(kirjoittaja, pisteet.get(i), tulos[i]);			
			}
	        kirjoittaja.close();
	    } 
		catch (IOException e) {
			throw new RuntimeException(e); 
		}	
		System.out.println(tempDir + nimi + " luotu");
	}
	
	/**
	 * @param kirjoittaja
	 * @param polygoni
	 */
	private static void kirjoitaPolygonRivi(BufferedWriter kirjoittaja, Polygoni polygoni) {
		try {
			kirjoittaja.write(polygoni.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Kirjoittaa pisteen tiedot raporttiin.
	 * @param kirjoittaja
	 * @param piste
	 * @param tulos
	 */
	private static void kirjoitaPisteRivi(BufferedWriter kirjoittaja, Piste piste, int tulos) {
		
		String tulos_str;
		if (tulos > 0) {
			tulos_str = "sisäpuolella";
		}	
		else if (tulos < 0) {
			tulos_str = "ulkopuolella";
		}
		 else  tulos_str = "viivalla";
		
		try {
			kirjoittaja.write(piste + " tulos: " + tulos_str);
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
			
	/**
	 * Tekeen pisteet tiedoston annetuista pisteistä ja tallentaa sen annetulla nimellä
	 * system temp directoryyn.
	 * @param pisteet
	 * @param nimi
	 */
	public static void teePisteetTiedosto(ArrayList<Piste> pisteet, String nimi) {
		
		String tempDir = getTempDir();
		try {
			BufferedWriter kirjoittaja = new BufferedWriter(new FileWriter(tempDir + nimi));
			for (int i=0; i < pisteet.size(); i++) {
				kirjoitaPisteRivi(kirjoittaja, pisteet.get(i));
				kirjoittaja.newLine();
			}
	        kirjoittaja.close();
	    } 
		catch (IOException e) {
			throw new RuntimeException(e); 
		}	
		System.out.println(tempDir + nimi + " luotu");
	}

	/**
	 * Kirjoittaa annetun pisteen koordinaatit
	 * @param kirjoittaja
	 * @param piste
	 */
	private static void kirjoitaPisteRivi(BufferedWriter kirjoittaja, Piste piste) {
		
		try {
			kirjoittaja.write(piste.getX() + ", " + piste.getY());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	}
	
	/**
	 * Etsii ja palauttaa system temp directoryn.
	 * @return tempDir
	 */
	private static String getTempDir() {
		
		String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        //System.out.println("OS temporary directory is " + tempDir); // Debuggausta varten
        return tempDir;
	}
	
	
	/**
	 * Lukee raportin ja palauttaa tuloksen int taulukossa.
	 * @return int[] tulokset
	 */
	public static int[] lueRaportti(String nimi) {
		ArrayList<Integer> tulokset = new ArrayList<Integer>();
		
		String tempDir = getTempDir();
		
		try (Scanner tiedostonLukija = new Scanner(Paths.get(tempDir + nimi))) {
			
			//Hypätään polygonirivin yli
			tiedostonLukija.nextLine();
			
		    while (tiedostonLukija.hasNextLine()) {
		    	
		        String rivi = tiedostonLukija.nextLine();
		        if (rivi.isEmpty()) {
		            continue;
		        }	        
				if (rivi.contains("sisäpuolella")) {
					tulokset.add(1);
				}	
				else if (rivi.contains("ulkopuolella")) {
					tulokset.add(-1);
				}
				else if (rivi.contains("viivalla")) {
					tulokset.add(0);
				}
				else {
					throw new RuntimeException();
				}
		    }
		} catch (Exception e) {
			throw new RuntimeException(e); 
		}	
		
		int[] tuloksetArray = new int[tulokset.size()];
		
		for (int i=0; i < tulokset.size(); i++) {
			tuloksetArray[i] = tulokset.get(i);
		}
		System.out.println(tempDir + nimi + " luettu");
		return tuloksetArray;
	}	
}
