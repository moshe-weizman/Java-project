/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import geometries.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;

/**
 * class to test Triangle's functions
 *
 */
public class TriangleTests {

	Triangle tr = new Triangle(new Point3D(0, 0, 0), new Point3D(2.5, 0, 0), new Point3D(0, 2.5, 0));
	Vector v1 = new Vector(0, 0, 1);

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// get normal to point on the triangle
		assertEquals(v1, tr.getNormal(tr.getP2()));
	}

	/**
	 * Test method for
	 * {@link geometries.Plane#findIntersections(primitives.Point3D)}.
	 */
	@Test
	public void testIntersectionPoints() {

		// ray intersects the triangle
		Ray ray = new Ray(new Point3D(1, 1, -1), v1);
		ArrayList<GeoPoint> list = new ArrayList<GeoPoint>();
		list.add(new GeoPoint(tr, new Point3D(1, 1, 0)));
		assertEquals(list, tr.findIntersections(ray));

		// "intersection" is in 1,-1,0 == no intersection
		ray = new Ray(new Point3D(1, -1, -1), v1);
		list.clear();
		assertEquals(list, tr.findIntersections(ray));
		assertEquals(list.size(), tr.findIntersections(ray).size());

		// "intersection is in 4,-1,0 == no intersection (between triangle's vectors -
		// but outside)
		ray = new Ray(new Point3D(4, -1, -1), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!

		// intersection is in p1 (0,0,0) - vertex of triangle. (ray is orthogonal
		// to one of the normals n1 n2 n3)
		ray = new Ray(new Point3D(0, 0, -1), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!

		// the same case but ray start from the vertex
		ray = new Ray(new Point3D(0, 0, 0), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!

		// intersection is in 0,1,0 - on the edge of triangle
		ray = new Ray(new Point3D(0, 1, -1), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!

		// the same case but ray start from the "intersection point"
		ray = new Ray(new Point3D(0, 1, 0), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!

		// intersection is in 3,0,0 - later on the vector of triangle's edge
		ray = new Ray(new Point3D(3, 0, -1), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!

		// the same case but ray start from the "intersection point"
		ray = new Ray(new Point3D(3, 0, 0), v1);
		assertEquals(list, tr.findIntersections(ray)); // list allready empty!
	}
}
