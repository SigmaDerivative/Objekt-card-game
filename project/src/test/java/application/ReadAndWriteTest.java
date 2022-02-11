package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class ReadAndWriteTest {
	
	//TODO sjekke at read fungerer, og at read fungerer etter write og
	@Test
	public void testRead() {
		try {
			assertEquals("test", ReadAndWrite.read("test.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadNonExistingFile() {
		try {
			ReadAndWrite.read("nonExisting.txt");
			fail("Exception was expected for non existing file");
		}
		catch (FileNotFoundException e) {}
	}

	//TODO sjekke at write fungerer, (altså at man skriver det man skal og til riktig fil
	@Test
	public void testWrite() {
		ReadAndWrite.write("test2.txt", "Testing some stuff");
	}
	
	@Test
	public void testWriteRead() {
		String test = "Testing some stuff again";
		ReadAndWrite.write("test2.txt", test);
		try {
			assertEquals(test, ReadAndWrite.read("test2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//TODO sjekke at write overskriver det som allerede ligger i filen (det skal den gjøre)
	@Test
	public void testOverwrite() {
		String first = "Testing 123";
		String second = "Testing some stuff again 2.0";
		ReadAndWrite.write("test2.txt", first);
		ReadAndWrite.write("test2.txt", second);
		try {
			assertEquals(second, ReadAndWrite.read("test2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	
}
