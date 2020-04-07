package tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.Bookmaker;
import model.BookmakerManager;

public class FileLoaderTests {
	
	public final ExpectedException exception = ExpectedException.none();

	List<Bookmaker> bookmakerList = new ArrayList <>();
	List<Bookmaker> expectedBookmakerList = new ArrayList <>();
	
	BookmakerManager bookmakerManager = new BookmakerManager();
	
	Bookmaker book1 = new Bookmaker(1, 1, "AAA", "BBB", 2.95, 2.95, 2.95);
	Bookmaker book2 = new Bookmaker(2, 2, "CCC", "DDD", 3.44, 3.44, 3.44);
	
	@Test
	public void test_readJsonFile_validData_successful() {
		
		expectedBookmakerList.add(book1);
		expectedBookmakerList.add(book2);
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/jsonTest.json");
		bookmakerList = bookmakerManager.getBookmakerList();
		
		assertThat(bookmakerList, is(expectedBookmakerList));
	}
	
	@Test
	public void test_readJsonFile_validData_failed() {
		
		expectedBookmakerList.add(book1);
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/jsonTestFail.json");
		bookmakerList = bookmakerManager.getBookmakerList();
		
		assertThat(bookmakerList, is(not(expectedBookmakerList)));
	}
	
	@Test
	public void test_ReadXmlFile_validData_successful() {
		
		expectedBookmakerList.add(book1);
		expectedBookmakerList.add(book2);
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/xmlTest.xml");
		bookmakerList = bookmakerManager.getBookmakerList();
		
		assertThat(bookmakerList, is(expectedBookmakerList));
	}
	
	@Test
	public void test_ReadXmlFile_validData_failed() {
		
		expectedBookmakerList.add(book1);
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/xmlTestFail.xml");
		bookmakerList = bookmakerManager.getBookmakerList();
		
		assertThat(bookmakerList, is(not(expectedBookmakerList)));
	}
	
	@Test
	public void test_readCsvFile_validData_successful() {
		
		expectedBookmakerList.add(book1);
		expectedBookmakerList.add(book2);
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/csvTest.csv");
		bookmakerList = bookmakerManager.getBookmakerList();
		
		assertThat(bookmakerList, is(expectedBookmakerList));
	}
	
	@Test
	public void test_readCsvFile_validData_failed() {
		
		expectedBookmakerList.add(book1);
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/csvTestFail.csv");
		bookmakerList = bookmakerManager.getBookmakerList();
		
		assertThat(bookmakerList, is(not(expectedBookmakerList)));
	}
	
	@Test
	public void test_readCsvFile_invalidFile() {
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/badCSV.csv");
		bookmakerList = bookmakerManager.getBookmakerList();
		assertEquals(0, bookmakerList.size());
	}
	
	@Test
	public void test_readXmlFile_invalidFile() {
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/badXMLfile.xml");
		bookmakerList = bookmakerManager.getBookmakerList();
		assertEquals(0, bookmakerList.size());
	}
	
	@Test
	public void test_readJsonFile_invalidFile() {
		
		bookmakerManager.readFromFile("/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/badJSONfile.json");
		bookmakerList = bookmakerManager.getBookmakerList();
		assertEquals(0, bookmakerList.size());
	}
	
	@Test
	 public void test_readFile_invalidType() {
	
	    String filePath = "/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/src/test/resources/invalidFile.txt";
	    String destinationPath = "/Users/ljubisamarkovic/eclipse-workspace/Bookmaker/invalidFiles/invalidFile.txt";

	    bookmakerManager.readFromFile(filePath);
	    
	    File fe = new File(destinationPath);

	    assertThat(fe.exists(), equalTo(true));
	}
}
