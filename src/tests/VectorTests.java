/**

 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;

/**
 * class to test Vector's functions
 *
 */
public class VectorTests {
	Vector v1 = new Vector(1, 0, 1);
	Vector v2 = new Vector(1, 0, -1);
	Vector v3 = new Vector(-1, 0, 1);
	Vector v4 = new Vector(1, 2, 3);
	Vector vector;
	//---------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#getHead()}.
	 */
	@Test
	public void testGetHead() {
		assertEquals(new Point3D(1, 0, 1), v1.getHead());
	}
    //--------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		//not equal
		assertFalse(v1.equals(v2));
		//equal
		assertTrue(v3.equals(v3));
	}
    //----------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		assertEquals(new Vector(2, 0, 0), v1.add(v2));

		try {
			vector = v2.add(v3);  //supposed to return 0-vector
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	//----------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		assertEquals(new Vector(0, 0, 2), v1.subtract(v2));

		try {
			vector = v2.subtract(v2);  //supposed to return 0-vector
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	//------------------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		assertEquals(new Vector(3, 0, 3), v1.scale(3));

		try {
			vector = v2.scale(0);  //supposed to return 0-vector
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	//-------------------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		assertEquals(Math.sqrt(2), v1.length(), 1e-10);
	}
	//------------------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#length2()}.
	 */
	@Test
	public void testLength2() {
		assertEquals(2, v1.length2(), 1e-10);
	}
	//-----------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		assertEquals(-2, v2.dotProduct(v3), 1e-10); //sharp angle
		assertEquals(4, v1.dotProduct(v4), 1e-10); //obtuse angle
		assertEquals(0, v1.dotProduct(v2), 1e-10); //orthogonal vectors
	}
	//---------------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		assertEquals(new Vector(0, 2, 0), v1.crossProduct(v2));
		
		try {
			vector = v2.crossProduct(v3); //supposed to return 0-vector - the same vector but in opposite direction
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		
		try {
			vector = v2.crossProduct(v2.scale(4)); //supposed to return 0-vector  - the same vector
			fail("Didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	//------------------------------------------------------------------------------
	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		assertEquals(1, v1.normalize().length(), 1e-10);	
		assertEquals(1, new Vector(1, 0, 0).normalize().length(), 1e-10);		
	}
}
