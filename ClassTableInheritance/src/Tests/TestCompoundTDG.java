package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Classes.CompoundDTO;
import Classes.CompoundTDG;

public class TestCompoundTDG {

	/**
	 * Test that a singleton instance of CompoundTDG is made
	 */
	@Test
	public static void testGetSingleton() {
		CompoundTDG comp = CompoundTDG.getSingleton();
		assertNotEquals(comp, null);
	}
	
	/**
	 * Test that you can return all the compounds that a given element is in
	 */
	@Test
	public static void testGetCompoundsByElement() {
		CompoundTDG comp = CompoundTDG.getSingleton();
		ArrayList<CompoundDTO> list = comp.getCompoundsByElement(4);
		assertEquals(list.size(), 2); 
		assertEquals(list.get(0).getCompoundID(), 0); 
		assertEquals(list.get(1).getCompoundID(), 1); 

	}
	
	/**
	 * runner used by entire Concrete project to run all tests at once
	 */
	@Test
	public static void runAllTheTests() {
		testGetSingleton();
		testGetCompoundsByElement();
	}

}