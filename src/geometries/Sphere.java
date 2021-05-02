package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * class to represent a sphere
 */
public class Sphere extends RadialGeometry {
	private Point3D _centerPoint;

	// ***************** Constructors ********************** //
	/**
	 * ctor for Sphere, gets radius and point3D
	 * 
	 * @param _radius
	 * @param centerPoint
	 */
	public Sphere(double _radius, Point3D centerPoint) {
		super(_radius);
		this._centerPoint = centerPoint;
		this.emmission = Color.BLACK;
		this.material = new Material(0,0,0);
	}

	/**
	 * ctor for Sphere, gets radius and point3D - and also color for emmission
	 * light!!
	 * 
	 * @param _radius
	 * @param centerPoint
	 * @param emmision    - color
	 */
	public Sphere(Color emmission, double _radius, Point3D centerPoint) {
		super(_radius);
		this._centerPoint = centerPoint;
		this.emmission = emmission;
		this.material = new Material(0,0,0);
	}

	/**
	 * ctor for Sphere, gets radius and point3D - and also color for emmission
	 * light!! and material!!!!
	 * 
	 * @param _radius
	 * @param centerPoint
	 * @param emmision    - color
	 * @param met - material
	 */
	public Sphere(Material mat, Color emmission, double _radius, Point3D centerPoint) {
		super(_radius);
		this._centerPoint = centerPoint;
		this.emmission = emmission;
		this.material= new Material(mat);
	}
	// ***************** Getters ********************** //
	/**
	 * getter fot centerPoint (getRadius is allready in super-class)
	 * 
	 * @return
	 */
	public Point3D getCenterPoint() {
		return _centerPoint;
	}

	// ***************** Administration ******************** //

	@Override
	public String toString() {
		return this._centerPoint.toString() + ", radius: " + _radius;
	}

	// ******************* Operations*************************//
	@Override
	public Vector getNormal(Point3D p1) {
		return p1.subtract(this._centerPoint).normalize();
	}

	// ----------------------------------------------------------
	@Override
	public List<GeoPoint> findIntersections(Ray myRay) {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		Vector u = null;

		try {
			u = this._centerPoint.subtract(myRay.getPoint()); // u=o-p0
		} catch (IllegalArgumentException e) { // if ray starts at the center of sphere
			list.add(new GeoPoint(this, myRay.getPoint().add(myRay.getNormalVec().scale(_radius))));
			return list;
		}

		double tm = u.dotProduct(myRay.getNormalVec()); // tm=u*v
		double d2 = u.length2() - tm * tm; // d^2
		double d = Math.sqrt(d2); // d=sqrt(u^2-tm^2
		if (d > this._radius)
			return EMPTY_LIST; // the ray doesn't intersect the sphere

		if (isZero(d - this._radius) && tm > 0 && !isZero(tm)) { // tangent to the sphere
			list.add(new GeoPoint(this, myRay.getPoint().add(myRay.getNormalVec().scale(tm))));// p0+tm*v
		} else {
			double th = Math.sqrt(this._radius * this._radius - d2); // th=sqrt(r^2-d^2)

			double t1 = tm - th;
			if (t1 > 0 && !isZero(t1))
				list.add(new GeoPoint(this, myRay.getPoint().add(myRay.getNormalVec().scale(t1))));// p0+t1*v

			double t2 = tm + th;
			if (t2 > 0 && !isZero(t2)) // check ray doesn't launch to sphere (so that th==0 => t1==t2 )
				list.add(new GeoPoint(this, myRay.getPoint().add(myRay.getNormalVec().scale(t2))));// p0+t2*v
		}
		return list;
	}
}
