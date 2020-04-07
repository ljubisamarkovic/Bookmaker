package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;


import model.Bookmaker;
import model.BookmakerArrayFilter;


public class BookmakerArrayFilterTests {
	
	BookmakerArrayFilter bookmakerArrayFilter = new BookmakerArrayFilter();
	List<Bookmaker> filteredList = new ArrayList <> ();
	List<Bookmaker> bookmakerList = new ArrayList <>();
	List<Bookmaker> expectedBookmakerList = new ArrayList <>();
	
	Bookmaker book1 = new Bookmaker(1, 1, "AAA", "BBB", 2.95, 2.95, 2.95);
	Bookmaker book2 = new Bookmaker(2, 2, "CCC", "DDD", 3.44, 3.44, 3.44);

	@Test
	public void testFilter_noParameters_successful() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		expectedBookmakerList.add(book2);
	
		filteredList = bookmakerArrayFilter.filter(bookmakerList);
		
		assertThat(filteredList, is(expectedBookmakerList));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFilter_noParameters_failed() {
		
		filteredList = bookmakerArrayFilter.filter(bookmakerList);
	}
	
	@Test
	public void testFilter_roundParameter_successful() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		expectedBookmakerList.add(book2);
	
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "2");
		
		assertThat(filteredList, is(expectedBookmakerList));
	}
	
	@Test
	public void testFilter_roundParameter_emptyList_failed() {
		
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "2");
		assertEquals(0, filteredList.size());
	}
	
	@Test
	public void testFilter_roundParameter_notExisting_failed() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "3");
		assertEquals(0, filteredList.size());
	}
	
	@Test
	public void testFilter_hostAndGuestParametars_successful() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		expectedBookmakerList.add(book2);
	
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "CCC", "DDD");
		
		assertThat(filteredList, is(expectedBookmakerList));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFilter_hostAndGuestParametars_emptyList_failed() {

		filteredList = bookmakerArrayFilter.filter(bookmakerList, "CCC", "DDD");
		assertEquals(0, filteredList.size());
	}
	
	@Test
	public void testFilter_hostAndGuestParametars_noExistingElement_failed() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		expectedBookmakerList.add(book2);
	
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "XXX", "ZZZ");
		assertEquals(0, filteredList.size());
	}
	
	@Test
	public void testFilter_roundAndhostAndGuestParametars_successful() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		expectedBookmakerList.add(book2);
	
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "2", "CCC", "DDD");
		
		assertThat(filteredList, is(expectedBookmakerList));
	}
	
	@Test
	public void testFilter_roundAndhostAndGuestParametars_noExistingElement_failed() {
		
		bookmakerList.add(book1);
		bookmakerList.add(book2);
		
		expectedBookmakerList.add(book2);
	
		filteredList = bookmakerArrayFilter.filter(bookmakerList, "5", "FFF", "MMM");
		assertEquals(0, filteredList.size());
	}
	
	@Test
	public void testFilter_roundAndhostAndGuestParametars_emptyList_failed() {

		filteredList = bookmakerArrayFilter.filter(bookmakerList, "5", "FFF", "MMM");
		assertEquals(0, filteredList.size());
	}
}
