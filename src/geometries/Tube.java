package geometries;

import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class to represent a tube
 */
public class Tube extends RadialGeometry {
	protected Ray _ray;

	// ***************** Constructors ********************** //
	/**
	 * ctor for Tube, gets ray and radius.
	 * 
	 * @param _radius
	 * @param _ray
	 */
	public Tube(double _radius, Ray _ray) {
		super(_radius);
		this._ray = _ray;
		this.emmission = Color.BLACK;
		this.material= new Material(0, 0, 0);
	}
//------------------------------------------------
	/**
	 * ctor for Tube, gets ray and radius; gets also emmission for color
	 * 
	 * @param _radius
	 * @param _ray
	 * @param emmission
	 */
	public Tube(Color emmission, double _radius, Ray _ray) {
		super(_radius);
		this._ray = _ray;
		this.emmission = emmission;
		this.material= new Material(0, 0, 0);
	}
//--------------------------------------------------
	/**
	 * ctor for Tube, gets ray and radius; gets also emmission for color
	 * 
	 * @param _radius
	 * @param _ray
	 * @param emmission
	 * @param           - mat
	 */
	public Tube(Material mat, Color emmission, double _radius, Ray _ray) {
		super(_radius);
		this._ray = _ray;
		this.emmission = emmission;
		this.material = new Material(mat);
	}

	// ***************** Getters ********************** //
	/**
	 * getter for _ray
	 * 
	 * @return Ray _ray
	 */
	public Ray getRay() {
		return _ray;
	}

	// ***************** Administration ******************** //
	@Override
	public String toString() {
		return this._ray.toString() + " radius: " + this._radius + ",";
	}

	// ***************** Operations ******************** //
	@Override
	public Vector getNormal(Point3D p1) {
		double t = this._ray.getNormalVec().dotProduct(p1.subtract(this._ray.getPoint()));
		Point3D o = this._ray.getPoint().add(this._ray.getNormalVec().scale(t));
		Vector norm = p1.subtract(o);
		// if (norm.length() != this._radius || t<0) {
		// throw new IllegalArgumentException("this point isn't on this tube");
		// }
		return norm.normalize();
	}

	@Override
	public List<GeoPoint> findIntersections(Ray myRay) {
		// TODO Auto-generated method stub
		return null;
	}

}
