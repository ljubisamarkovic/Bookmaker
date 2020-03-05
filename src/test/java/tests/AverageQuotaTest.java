package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.AverageQuota;
import model.Bookmaker;


public class AverageQuotaTest {
	
	AverageQuota expectedAverageQuota = new AverageQuota(2.0, 2.0, 2.0);
	AverageQuota averageQuota = new AverageQuota();
	
	Bookmaker book1 = new Bookmaker(1,1,"","",3.0,3.0,3.0);
	Bookmaker book2 = new Bookmaker(1,1,"","",1.0,1.0,1.0);
	
	List<Bookmaker> bookmakerList = new ArrayList <>();

	@Test
	public void calculateAverageTestSuccess () {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		averageQuota = averageQuota.calculateAverages(bookmakerList);
		
		assertEquals(expectedAverageQuota, averageQuota);
	}
	
	@Test
	public void calculateAverageTestFail () {
		
		bookmakerList.add(book1);
		
		averageQuota = averageQuota.calculateAverages(bookmakerList);
		
		assertNotEquals(expectedAverageQuota, averageQuota);
	}
}
