package Polygoni.polygoni;

/**
 * @author Henri
 * Talletaa pisteen koordinaatit. Järjestyy ensisijaisesti X-akselin arvon mukaan ja toissijaisesti Y-akselin mukaan.
 */
public class Piste implements Comparable<Piste>{

	private float[] koordinaatit = new float[2];
	
	/**
	 * @param X
	 * @param Y
	 */
	
	public Piste(int X, int Y) {
		this.koordinaatit[0] = (float)X;
		this.koordinaatit[1] = (float)Y;
	}
	
	public Piste(double X, double Y) {
		this.koordinaatit[0] = (float)X;
		this.koordinaatit[1] = (float)Y;
	}
	
	public Piste(float X, float Y) {
		this.koordinaatit[0] = X;
		this.koordinaatit[1] = Y;
	}
	
	/**
	 * @return
	 */
	public float[] getKoordinaatti() {return this.koordinaatit;}
	
	/**
	 * @return
	 */
	public float getX() {return this.koordinaatit[0];}
	
	/**
	 * @return
	 */
	public float getY() {return this.koordinaatit[1];}
	

	/**
	 *
	 */
	public String toString() {
		return "Piste: (" + koordinaatit[0] + ", " + koordinaatit[1] + ")";
	}
	
	/**
	 *Järjestää X koordinaatin mukaan pienimmästä suurimpaan.
	 *Jos X koordinaatti on sama verrataan Y koordinaattia.
	 */
	public int compareTo(Piste piste) {
		if (this.koordinaatit[0] < piste.getX()) {
			return -1;
		}
		else if (this.koordinaatit[0] > piste.getX()) {
			return 1;
		}
		else {
			if (this.koordinaatit[1] < piste.getY()) {
				return -1;
			} 
			else if (this.koordinaatit[1] < piste.getY()) {
				return 1;
			}
			else return 0;
		}
	}	
}
