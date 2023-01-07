package Polygoni.polygoni;

import org.junit.Test;


/**
 * @author Henri
 *Testaa piste luokan toiminnan.
 */
public class TestPiste {
	
	Piste piste;
	
	@Test
	public void testPiste() {
		piste = new Piste(2,5);
	}
	
	@Test
	public void testPisteLiuku() {
		piste = new Piste(2.5,-5.2);
	}
}
