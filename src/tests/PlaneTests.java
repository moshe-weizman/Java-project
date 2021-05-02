/**
 * 
 */
package tests;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Plane;
import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class to test Plane's functions
 *
 */
public class PlaneTests {
	// plane xy
	Plane plane = new Plane(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(1, 1, 0));

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// point on the plane
		assertEquals(new Vector(0, 0, 1), plane.getNormal(plane.getP1()));

		plane = new Plane(new Point3D(2, 3.2, 5), new Point3D(1.4, 0.9, -0.1), new Point3D(1, 1, 0));
		// more interesting plane and point...
		assertEquals(1, plane.getNormal(plane.getP1()).length(), 1e-10);
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#findIntersections(primitives.Point3D)}.
	 */
	@Test
	public void testIntersectionPoints() {
		Point3D p0 = new Point3D(0, 0, 0);
		Point3D p1 = new Point3D(0, 0, 1);

		// normal case, EP case
		Ray ray = new Ray(new Point3D(0, 1, 1), new Vector(0, -1 / Math.sqrt(2), -1 / Math.sqrt(2)));
		ArrayList<GeoPoint> list = new ArrayList<GeoPoint>();
		list.add(new GeoPoint(plane, p0));
		assertEquals(list, plane.findIntersections(ray));

		// vec includes in the plane
		ray = new Ray(p0, new Vector(1, 0, 0));
		assertEquals(0, plane.findIntersections(ray).size());

		// vec is parallel but not included
		ray = new Ray(p1, new Vector(1, 0, 0));
		assertEquals(0, plane.findIntersections(ray).size());

		// vec is orthogonal && intersect the plane from above
		ray = new Ray(p1, new Vector(0, 0, -1)); // list includes only p0 - such as test before
		assertEquals(list, plane.findIntersections(ray));

		// vec is orthogonal && intersect the plane from below
		ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1)); // list includes only p0 - such as test before
		assertEquals(list, plane.findIntersections(ray));

		// vec is orthogonal but start in the plane
		ray = new Ray(p0, new Vector(0, 0, -1)); // list includes only p0 - such as test before
		list.clear();
		assertEquals(list, plane.findIntersections(ray));

		// vec starts in the plane and go outside
		ray = new Ray(p0, new Vector(1, 1, 1)); // list includes only p0 - such as test before
		assertEquals(list, plane.findIntersections(ray));

		// ray start above plane and go in opposite direction
		ray = new Ray(p1, new Vector(1, 1, 1));
		assertEquals(list, plane.findIntersections(ray));
	}
}
