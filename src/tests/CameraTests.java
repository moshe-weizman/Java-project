/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import geometries.*;
import org.junit.Test;
import primitives.*;
import elements.*;

///**
// * class to test camera functions
// *
// */
//public class CameraTests {
//	Point3D p0 = new Point3D(0, 0, 0);
//	Camera camera = new Camera(p0, new Vector(0, -1, 0), new Vector(0, 0, -1));
//
//	/**
//	 * Test method for
//	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
//	 */
//	@Test
//	public void testConstructRayThroughPixel() {
//		// tests for view plane of 3*3
//		Ray ray00 = new Ray(p0, new Vector(2, -2, -2));
//		Ray ray01 = new Ray(p0, new Vector(0, -2, -2));
//		Ray ray10 = new Ray(p0, new Vector(2, 0, -2));
//		Ray ray11 = new Ray(p0, new Vector(0, 0, -2));
//
////		assertEquals(ray00, camera.constructRayThroughPixel(3, 3, 0, 0, 2, 6, 6));
////		assertEquals(ray01, camera.OLDconstructRayThroughPixel(3, 3, 1, 0, 2, 6, 6));
////		assertEquals(ray10, camera.OLDconstructRayThroughPixel(3, 3, 0, 1, 2, 6, 6));
////		assertEquals(ray11, camera.OLDconstructRayThroughPixel(3, 3, 1, 1, 2, 6, 6));
////----------------------------------------------------------------------
//		// tests for view plane of 4*4
//		ray00 = new Ray(p0, new Vector(3, -3, -2));
//		ray01 = new Ray(p0, new Vector(1, -3, -2));
//		ray11 = new Ray(p0, new Vector(1, -1, -2));
//
//		assertEquals(ray00, camera.OLDconstructRayThroughPixel(4, 4, 0, 0, 2, 8, 8));
//		assertEquals(ray01, camera.OLDconstructRayThroughPixel(4, 4, 1, 0, 2, 8, 8));
//		assertEquals(ray11, camera.OLDconstructRayThroughPixel(4, 4, 1, 1, 2, 8, 8));
//		
////*********************************  Intersections ***********************************		
////-------------------------------------------------------------------------
//		// tests for intersections with shapes. first of all - little sphere.
//		Sphere sphere = new Sphere(1, new Point3D(0, 0, -3));
//		int counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				//ArrayList<Ray>rays=camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += sphere.findIntersections(ray).size();
//				//assertEquals(15, rays.size());
//			}
//		assertEquals(2, counter);
////----------------------------------------------------------------
//		// second test for sphere - big sphere
//		sphere = new Sphere(2.5, new Point3D(0, 0, -3));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += sphere.findIntersections(ray).size();
//			}
//		assertEquals(18, counter);
////----------------------------------------------------------
//		// third test for sphere - medium sphere
//		sphere = new Sphere(2, new Point3D(0, 0, -2.5));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += sphere.findIntersections(ray).size();
//			}
//		assertEquals(10, counter);
////----------------------------------------------------------------		
//		// fourth test for sphere - medium sphere
//		sphere = new Sphere(10, new Point3D(0, 0, -2.5));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += sphere.findIntersections(ray).size();
//			}
//		assertEquals(9, counter);
////---------------------------------------------------------------
//		// fifth test for sphere - sphere beyond camera
//		sphere = new Sphere(1, new Point3D(0, 0, 1));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += sphere.findIntersections(ray).size();
//			}
//		assertEquals(0, counter);
////------------------------------------------------------------------
//		// first test for plane - parallel to camera
//		Plane plane = new Plane(new Point3D(0, 0, -4), new Vector(0, 0, -1));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += plane.findIntersections(ray).size();
//			}
//		assertEquals(9, counter);
////-------------------------------------------------------------------
//		// second test for camera
//		plane = new Plane(new Point3D(0, 0, -4), new Vector(0, 0.5, -1));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += plane.findIntersections(ray).size();
//			}
//		assertEquals(9, counter);
////------------------------------------------------------------------
//		// third test for plane - parallel to rays of camera
//		plane = new Plane(new Point3D(0, 0, -4), new Vector(1, -1, -1));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += plane.findIntersections(ray).size();
//			}
//		assertEquals(6, counter);
////-------------------------------------------------------------------
//		// first test for triangle
//		Triangle triangle = new Triangle(new Point3D(0, -1, -2), new Point3D(1, 1, -2), new Point3D(-1, 1, -2));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += triangle.findIntersections(ray).size();
//			}
//		assertEquals(1, counter);
////--------------------------------------------------------------
//		// second test for triangle
//		triangle = new Triangle(new Point3D(0, -20, -2), new Point3D(1, 1, -2), new Point3D(-1, 1, -2));
//		counter = 0;
//		for (int i = 0; i < 3; ++i)
//			for (int j = 0; j < 3; ++j) {
//				Ray ray = camera.OLDconstructRayThroughPixel(3, 3, i, j, 1, 3, 3);
//				counter += triangle.findIntersections(ray).size();
//			}
//		assertEquals(2, counter);
//	}

//}