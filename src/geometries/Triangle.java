
package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.isZero;

/**
 * class to represent a triangle
 */
public class Triangle extends Plane {
	private Point3D _p2, _p3;

	// ***************** Constructors ********************** //
	/**
	 * ctor for Triangle, gets 3 Point3D; calcuates also the normal vector of the
	 * Triangle. intialize emmission in constructor of super
	 * 
	 * @param Point3D p1
	 * @param Point3D p2
	 * @param Point3D p3
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
		this._p2 = p2;
		this._p3 = p3;
	}
	
	/**
	 * ctor for Triangle, gets 3 Point3D; calcuates also the normal vector of the
	 * Triangle. get also color for emmission light
	 * 
	 * @param Point3D p1
	 * @param Point3D p2
	 * @param Point3D p3
	 * @param - emmission color
	 */
	public Triangle( Color emmission, Point3D p1, Point3D p2, Point3D p3) {
		super(emmission,p1, p2, p3);
		this._p2 = p2;
		this._p3 = p3;
	}
	
	/**
	 * ctor for Triangle, gets 3 Point3D; calcuates also the normal vector of the
	 * Triangle. get also color for emmission light
	 * 
	 * @param Point3D p1
	 * @param Point3D p2
	 * @param Point3D p3
	 * @param emmission - color
	 * @param mat - material
	 */
	public Triangle(Material mat, Color emmission, Point3D p1, Point3D p2, Point3D p3) {
		super(mat,emmission,p1, p2, p3);
		this._p2 = p2;
		this._p3 = p3;
	}

	// ***************** Getters ********************** //
	/**
	 * getter for p2
	 * 
	 * @return Point3D p2
	 */
	public Point3D getP2() {
		return _p2;
	}

	/**
	 * getter for p3
	 * 
	 * @return Point3D p3
	 */
	public Point3D getP3() {
		return _p3;
	}

	// ***************** Administration ******************** //
	@Override
	public String toString() {
		return super.toString() + ", " + this._p2.toString() + ", " + this._p3.toString();
	}

	// ***************** Operations ******************** //
	@Override
	public List<GeoPoint> findIntersections(Ray myRay) {
		List<GeoPoint> list = super.findIntersections(myRay);
		
		if (list.size() != 0) // there are intersections with plane
			try {
				Point3D p0 = myRay.getPoint();
				Vector v1 = this._p1.subtract(p0);// Vi = Pi-P0
				Vector v2 = this._p2.subtract(p0);
				Vector v3 = this._p3.subtract(p0);
				Vector n1 = v1.crossProduct(v2).normalize();// Ni= Vi*Vi+1
				Vector n2 = v2.crossProduct(v3).normalize();
				Vector n3 = v3.crossProduct(v1).normalize();
				Vector deltaP = list.get(0).point.subtract(p0); // P-P0
				double num1 = deltaP.dotProduct(n1); // Ni*(P-P0)
				double num2 = deltaP.dotProduct(n2);
				double num3 = deltaP.dotProduct(n3);

				if (((num1 > 0 && num2 > 0 && num3 > 0) || (num1 < 0 && num2 < 0 && num3 < 0))
						&& !(isZero(num1) || isZero(num2) || isZero(num3)))
					return list;
			} catch (IllegalArgumentException e) {}

		return EMPTY_LIST;
	}
}
