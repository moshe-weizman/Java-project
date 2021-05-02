package geometries;

import java.util.ArrayList;

import java.util.List;
import static primitives.Util.isZero;
import primitives.*;

/**
 * class to represent a plane in 3D
 */
public class Plane extends Geometry {
	protected Point3D _p1;
	protected Vector _normal;

	// ***************** Constructors ********************** //
	/**
	 * ctor for Plane, gets Point3D and (non-normalized?) Vector
	 * 
	 * @param p1
	 * @param normal
	 */
	public Plane(Point3D p1, Vector normal) {
		this._p1 = p1;
		this._normal = normal.normalize();
		this.emmission= Color.BLACK;
		this.material= new Material(0, 0, 0);
	}
//---------------------------------------------------
	/**
	 * ctor for Plane; gets 3 Point3D, calcuates the normal to the plane. (ctor will
	 * throw exception if 2 of the points are the same)
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		//if all points are on the same vector - the normal will be vector 0 and exception will be thrown
		this._normal = (p2.subtract(p1)).crossProduct(p3.subtract(p1)).normalize();
		this._p1 = p1;
		this.emmission= Color.BLACK;
		this.material= new Material(0, 0, 0);
	}
//--------------------------------------------------	
	/**
	 * ctor for Plane, gets Point3D and (non-normalized?) Vector and emission light!!
	 * 
	 * @param p1
	 * @param normal
	 * @param emission - for emmission light
	 */
	public Plane(Color emmision,Point3D p1, Vector normal) {
		this._p1 = p1;
		this._normal = normal.normalize();
		this.emmission= emmision;
		this.material= new Material(0, 0, 0);
	}
//----------------------------------------------------
	/**
	 * ctor for Plane; gets 3 Point3D, calcuates the normal to the plane. (ctor will
	 * throw exception if 2 of the points are the same). get also color for emmission light
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param emmission - color
	 */
	public Plane( Color emmission, Point3D p1, Point3D p2, Point3D p3) {
		//if all points are on the same vector - the normal will be vector 0 and exception will be thrown
		this._normal = (p2.subtract(p1)).crossProduct(p3.subtract(p1)).normalize();
		this._p1 = p1;
		this.emmission= emmission;
		this.material= new Material(0, 0, 0);
	}
	//--------------------------------------------------	
		/**
		 * ctor for Plane, gets Point3D and (non-normalized?) Vector and emission light!!
		 * and material!!!!!
		 * @param p1
		 * @param normal
		 * @param emission - for emmission light
		 * @param mat - material
		 */
		public Plane(Material mat, Color emmision,Point3D p1, Vector normal) {
			this._p1 = p1;
			this._normal = normal.normalize();
			this.emmission= emmision;
			this.material= new Material(mat);
		}
	//----------------------------------------------------
		/**
		 * ctor for Plane; gets 3 Point3D, calcuates the normal to the plane. (ctor will
		 * throw exception if 2 of the points are the same). get also color for emmission light
		 * 
		 * @param p1
		 * @param p2
		 * @param p3
		 * @param emmission - color
		 * @param mat - material
		 */
		public Plane( Material mat, Color emmission, Point3D p1, Point3D p2, Point3D p3) {
			//if all points are on the same vector - the normal will be vector 0 and exception will be thrown
			this._normal = (p2.subtract(p1)).crossProduct(p3.subtract(p1)).normalize();
			this._p1 = p1;
			this.emmission= emmission;
			this.material= new Material(mat);
		}

	// ***************** Getters ********************** //
	/**
	 * getter for Ppoint3D_p1
	 * 
	 * @return point3d p1
	 */
	public Point3D getP1() {
		return _p1;
	}

	/**
	 * getter for the (normalized) normal of the plane
	 * 
	 * @return
	 */
	public Vector getNormal() {
		return _normal;
	}
	
	// ***************** Administration ******************** //
	@Override
	public String toString() {
		return this._p1.toString() + " " + this._normal.toString();
	}

	// ***************** Operations *************************//
	@Override
	public Vector getNormal(Point3D p1) {
		return this._normal;
	}

	// -------------------------------------------------------
	@Override
	public List<GeoPoint> findIntersections(Ray myRay) {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		try {
			double denominator = this._normal.dotProduct(myRay.getNormalVec());// N*v
			if (isZero(denominator)) // ray is included into the plane or parallel to plane
				return EMPTY_LIST;
			double numerator = this._normal.dotProduct(this._p1.subtract(myRay.getPoint()));// N*(q0-p0)
			double t = numerator / denominator;
			if (t > 0 && !(isZero(t))) //ray intersect the plane
				list.add(new GeoPoint(this, myRay.getPoint().add(myRay.getNormalVec().scale(t))));
			return list;
		} catch (IllegalArgumentException e) { // if q0==p0 || N*(q0-p0)==0 (orthogonal ray,
			// or ray that starts at the plane
			//list.add(myRay.getPoint());
			return list;
		}
	}

	
}
