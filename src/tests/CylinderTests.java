/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Cylinder;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class to test Cylinder's functions
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Cylinder cyl = new Cylinder(1, new Ray(new Point3D(0, 0, 0),new Vector(1, 0, 0) ), 2);
		//point on the bottom disc of cyl.
		assertEquals(new Vector(-1, 0, 0), cyl.getNormal(new Point3D(0, 0.5, 0)));
		//point on the top disc of cyl
		assertEquals(new Vector(1,0,0), cyl.getNormal(new Point3D(2, -0.5, 0)));
		//point on the roll
		assertEquals(new Vector(0, 1, 0) , cyl.getNormal(new Point3D(1, 1, 0)));
	}

}
