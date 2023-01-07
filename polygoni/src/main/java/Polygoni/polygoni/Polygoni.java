package Polygoni.polygoni;

import java.util.ArrayList;

/**
 * @author Henri
 *
 */
public class Polygoni {

	ArrayList<Piste> koordinaatit;
	float[][] taulukko;
	
	/**
	 * @param koordinaatit
	 */
	public Polygoni(ArrayList<Piste> koordinaatit) {
		this.koordinaatit = koordinaatit;
		luoKoordinaattiTaulukko();
	}		
	
	/** Luo koordinaateista taulukon jossa [i][0] on i:n pisteen X arvo 
	 ja[i][1] on i:n pisteen Y arvo. */
	private void luoKoordinaattiTaulukko() {
		int pisteita = koordinaatit.size();
		this.taulukko = new float[pisteita][2];
		
		int i = 0;
		for (Piste koordinaatti : koordinaatit) {
			taulukko[i][0] = koordinaatti.getX();
			taulukko[i][1] = koordinaatti.getY();
			i++;
		}
	}
	
	
	public ArrayList<Piste> getKoordinaatit() {return koordinaatit;}
		
	
	/**
	 * @return
	 */
	public boolean tyhja() {
		if (koordinaatit == null) {
			return true;
		}else return false;
	}
	
	/**
	 *
	 */
	public String toString() {
		 
		String koordinaatti_str = ""; 
		int i = 0;
		for (Piste piste : koordinaatit) {
			if (i > 0) {
				koordinaatti_str += ", ";
			}
			koordinaatti_str = koordinaatti_str + "(" + piste.getX() + ", " + piste.getY() + ")";
			i++;
		}
		return "Polygoni: " + koordinaatti_str;
	}
	

	/**
	 * Tarkistaa, onko kolmas piste kahden ensimmäisen pisteen välisellä viivalla. Palauttaa <0
	 * jos vasemmalla puolella, 0 jos viivalla ja > 0 jos viivan oikealla puolella. Ei käytössä tällä hetkellä.
	 * @param piste1
	 * @param piste2
	 * @param piste3
	 * @return
	 */
	private float onkoViivalla(Piste piste1, Piste piste2, Piste piste3) {
		return ( (piste2.getX() - piste1.getX()) * (piste3.getY() - piste1.getY())
			      - (piste3.getX() -  piste1.getX()) * (piste2.getY() - piste1.getY()) );
	}

    /**
     * Tarkistaa Ray casting algoritmilla, onko piste polygonin sisällä. Palauttaa true, 
	 * jos piste on polygonin sisällä ja false muuten. Ei käytössä tällä hetkellä.
     * @param piste
     * @return
     */
    public boolean onkoPistePolygonissaRayCasting(Piste piste) {
    	
        //Jos viiva pisteestä äärettömyyten ylittää polygonin parittoman määrän, on piste polygonin sisällä.
        boolean pariton = false;
        float[] pisteKoordinaatit = piste.getKoordinaatti();
        
        //int ylitykset = 0; //debuggausta varten
        //Ray casting algoritmi
        for (int i = 0, j = taulukko.length - 1; i < taulukko.length; i++) {
            if (((taulukko[i][1] > pisteKoordinaatit[1]) != (taulukko[j][1] > pisteKoordinaatit[1])) 
                    && (pisteKoordinaatit[0] < (taulukko[j][0] - taulukko[i][0]) *
                    	(pisteKoordinaatit[1] - taulukko[i][1]) / (taulukko[j][1] - taulukko[i][1]) + taulukko[i][0])) {
                //ylitykset++;
                pariton = !pariton;
            }
            j = i;
        }
        //System.out.println("Ylitysten määrä: " + ylitykset);
        return pariton;
    }
    
    
    /**
     * Tarkistaa onko piste, polygonin ulkoupulella, sisäpuolella vai viivalla. Hyödyntää Haon
     * algoritmia. Lähde: 
     * https://www.researchgate.net/publication/328261365_Optimal_Reliable_Point-in-Polygon_Test_and_Differential_Coding_Boolean_Operations_on_Polygons
     * @param piste
     * @return int
     */
    public int onkoPistePolygonissa(Piste piste) {
    
    	float x = piste.getX();
    	float y = piste.getY();
    	
    	float u1;
    	float u2;
    	float v1;
    	float v2;
    	float f = 0;
    	float k = 0;
    	float[] nykyinenPiste;
    	float[] seuraavaPiste;
    	
    	float haoTaulukko[][] = teeTaulukkoHaoAlgoritmiin();
    	
  
    	int aariviivoja = 1;
    	int koko = haoTaulukko.length;
    	
    	
    	for (int i=0; i < aariviivoja; i++) {
    		
    		nykyinenPiste = haoTaulukko[0];
    		
    		u1 = nykyinenPiste[0] - x;
    		v1 = nykyinenPiste[1] - y;
    		
	    	for (int j=0; j < koko-1; j++) {
	    		seuraavaPiste = haoTaulukko[j+1];
	    		v2 = seuraavaPiste[1] - y;
	    		
	    		if ((v1 < 0 && v2 < 0) || (v1 > 0 && v2 > 0)) {
	                nykyinenPiste = seuraavaPiste;
	                v1 = v2;
	                u1 = nykyinenPiste[0] - x;
	                continue;
	            }
	    		u2 = seuraavaPiste[0] - x;
	    		
	    		if (v2 > 0 && v1 <= 0) {
	                f = (u1 * v2) - (u2 * v1);
	                if (f > 0) k = k + 1;
	                else if (f == 0) {return 0;}
	            } else if (v1 > 0 && v2 <= 0) {
	                f = (u1 * v2) - (u2 * v1);
	                if (f < 0) k = k + 1;
	                else if (f == 0) return 0;
	            } else if (v2 == 0 && v1 < 0) {
	                f = (u1 * v2) - (u2 * v1);
	                if (f == 0) return 0;
	            } else if (v1 == 0 && v2 < 0) {
	                f = u1 * v2 - u2 * v1;
	                if (f == 0) return 0;
	            } else if (v1 == 0 && v2 == 0) {
	                if (u2 <= 0 && u1 >= 0) {
	                    return 0;
	                } else if (u1 <= 0 && u2 >= 0) {
	                    return 0;
	                }
	            }
	    		nykyinenPiste = seuraavaPiste;
	            v1 = v2;
	            u1 = u2;
	    	}
    	}   	
    	if (k % 2 == 0) return -1;
    	return 1;
    }
    
    
    /**
 	 * Kopioi alkuperäisen pistetaulukon ja lisää viimeiseksi pisteeksi ensimmäisen pisteen.
     * @return haoTaulukko
     */
    private float[][] teeTaulukkoHaoAlgoritmiin() {
    	
    	int pisteita = taulukko.length;
		float haoTaulukko[][] = new float[pisteita+1][2];
    	
    	for (int i = 0; i < pisteita; i++) {
    		haoTaulukko[i] = taulukko[i];
    	}
    	haoTaulukko[pisteita] = taulukko[0];
    	return haoTaulukko;
    }
    
    
    
    
    	
}
