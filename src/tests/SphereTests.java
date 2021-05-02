/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class to test Sphere's functions
 *
 */
public class SphereTests {

	Sphere sp = new Sphere(4, new Point3D(0, 0, 0));

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// get normal to point on the sphere
		assertEquals(new Vector(1, 0, 0), sp.getNormal(new Point3D(4, 0, 0)));
	}

	/**
	 * Test method for
	 * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
	 */
	@Test
	public void testIntersectionPoints() {
		
		Point3D p1 = new Point3D(-5, 1, 0);
		Vector v1 = new Vector(1, 0, 0);
		Vector v_1 = new Vector(-1, 0, 0);
		// 2 intersections
		Ray ray = new Ray(p1, v1);
		ArrayList<GeoPoint> list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(Math.sqrt(15) * -1, 1, 0), list.get(0).getPoint());
		assertEquals(new Point3D(Math.sqrt(15), 1, 0), list.get(1).getPoint());

		// no intersections
		ray = new Ray(p1, new Vector(0, 1, 0));
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// ray at the opposite direction
		ray = new Ray(p1, v_1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// ray starts on the diameter (but not at the center-point)
		ray = new Ray(new Point3D(1, 0, 0), v1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(4, 0, 0), list.get(0).getPoint());

		// on the edge, vector forward the opposite direction
		ray = new Ray(new Point3D(-Math.sqrt(15), 1, 0), v_1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// on the edge, vector goes into the sphere
		ray = new Ray(new Point3D(-Math.sqrt(15), 1, 0), v1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(Math.sqrt(15), 1, 0), list.get(0).getPoint());

		// p0 is 000
		ray = new Ray(new Point3D(0, 0, 0), v1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(4, 0, 0), list.get(0).getPoint());
		assertEquals(1, list.size());

		// p0 on the edge, vector is opposite to sphere
		ray = new Ray(new Point3D(-4, 0, 0), v_1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// p0 on the edge, vector goes into the sphere
		ray = new Ray(new Point3D(-4, 0, 0), v1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(4, 0, 0), list.get(0).getPoint());

		// p0 in -5,0,0 vector on the opposite direct
		ray = new Ray(new Point3D(-5, 0, 0), v_1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// p0 in 5,0,0 vector goes to sphere
		ray = new Ray(new Point3D(5, 0, 0), v_1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(4, 0, 0), list.get(0).getPoint());
		assertEquals(new Point3D(-4, 0, 0), list.get(1).getPoint());

		// orthogonal ray to vector from center of sphere;
		ray = new Ray(new Point3D(5, 0, 0), new Vector(0, 1, 0));
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// tangent from p0
		ray = new Ray(new Point3D(-1, 4, 0), v1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(new Point3D(0, 4, 0), list.get(0).getPoint());

		// the same but vector opposite
		ray = new Ray(new Point3D(-1, 4, 0), v_1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());

		// p0 on sphere, vec tangent to sphere
		ray = new Ray(new Point3D(0, 4, 0), v1);
		list = new ArrayList<GeoPoint>(sp.findIntersections(ray));
		assertEquals(0, list.size());
	}
}
