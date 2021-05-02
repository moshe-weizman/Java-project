package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class to represent a cylinder
 */
public class Cylinder extends Tube {
	private double _high;

	// ***************** Constructors ********************** //
	/**
	 * ctor for cylinder
	 * 
	 * @param     double _radius
	 * @param Ray _ray
	 * @param     double _high
	 */
	public Cylinder(double _radius, Ray _ray, double _high) {
		super(_radius, _ray);
		this._high = _high;
	}
//----------------------------------------------------------
	/**
	 * ctor for cylinder
	 * 
	 * @param     double _radius
	 * @param Ray _ray
	 * @param     double _high
	 * @param Color emmission
	 */
	public Cylinder(Color emmission,double _radius, Ray _ray, double _high) {
		super(emmission,_radius, _ray);
		this._high = _high;
	}
//--------------------------------------------------	
	/**
	 * ctor for cylinder
	 * 
	 * @param     double _radius
	 * @param Ray _ray
	 * @param     double _high
	 * @param Color emmission
	 * @param mat - material
	 */
	public Cylinder(Material mat, Color emmission,double _radius, Ray _ray, double _high) {
		super(mat, emmission,_radius, _ray);
		this._high = _high;
	}
	// ***************** Getters ********************** //
	/**
	 * getter for _high
	 * 
	 * @return double _high (getters for the rest - in Tube class)
	 */
	public double getHigh() {
		return _high;
	}

	// ***************** Administration ******************** //

	@Override
	public String toString() {
		return super.toString() + " high: " + _high;
	}

	// ***************** Operations ******************** //
	@Override
	public Vector getNormal(Point3D p1) {
		double t=p1.subtract(this._ray.getPoint()).dotProduct(this._ray.getNormalVec());
		//if the point is on the "bottom disc"
		if (t == 0)
			return this._ray.getNormalVec().scale(-1);		
		//if the point is on the "top disc"
		else if (t==this._high)
			return this._ray.getNormalVec();
		else
			return super.getNormal(p1);
	}
}
