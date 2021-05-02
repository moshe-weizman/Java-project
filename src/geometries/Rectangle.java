/**
 * 
 */
package geometries;

/**
 * @author Home
 *
 */

import java.util.List;
import primitives.Color;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * class represents rectangle
 */
public class Rectangle extends Plane {

	Point3D _a;
	Point3D _b;
	Point3D _c;

	/****** Constructors *********/

	/**
	 * constructor
	 * 
	 * @param a
	 *            point a
	 * @param b
	 *            point b
	 * @param c
	 *            point c
	 * @param color
	 *            emission color of the plane
	 * @param material
	 *            material of the plane
	 */
	public Rectangle(Point3D a, Point3D b, Point3D c, Color color, Material material) {
		super( material,color,a, b, c);
		this._a = a;
		this._b = b;
		this._c = c;
		Vector ab = _b.subtract(_a);
		Vector ac = _c.subtract(_a);
		if (!Util.isZero(ab.dotProduct(ac))) {
			throw new IllegalArgumentException("ab is not orthogonal to ac");
		}
	}

	/****** Operations *********/

	/*
	 * (non-Javadoc)
	 * 
	 * @see geometries.Plane#findIntersections(primitives.Ray)
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// get plane intersections
		List<GeoPoint> planeIntersection = super.findIntersections(ray);

		if (planeIntersection.isEmpty())
			return EMPTY_LIST;

		List<GeoPoint> intersectionList = planeIntersection;
		// the point is inside the rectangle if (0 < PA * AB < AB * AB) AND (0 < PA * AC
		// < AC * AC)
		Vector AB = _b.subtract(_a);
		Vector ab= AB.normalize();
		Vector AC = _c.subtract(_a);
		Vector ac= AC.normalize();

		if (intersectionList.get(0).equals(_a))
			//return planeIntersection;
			return EMPTY_LIST;

		Vector PA = intersectionList.get(0).point.subtract(_a);
		Vector pa=PA.normalize(); 
		double u = PA.dotProduct(AB);
		double v = PA.dotProduct(AC);
		if (!(u >= 0.0 && u <= AB.dotProduct(AB) && v >= 0.0 && v <= pa.dotProduct(ac))||pa.equals(ab)
				||Util.isZero(PA.dotProduct(AB)))
			planeIntersection.clear();

		return planeIntersection;
	}
}
