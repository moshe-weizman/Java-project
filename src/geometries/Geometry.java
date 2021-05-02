package geometries;

import primitives.*;

/**
 * interface includes func getNormal
 *
 */
public abstract class Geometry implements Intersectable{
	Color emmission;
	Material material;
	
//------------------------------------------------------------
	/**
	 * func to get normal-vector to a point on a geometric shape
	 * @param Point3D p1
	 * @return
	 */
	public abstract Vector getNormal(Point3D p1);
	
//--------------------------------------------------------------
	/**
	 * getter for emmision
	 * @return
	 */
	public  Color getEmmission() {
		return this.emmission;
	};

//-------------------------------------------------------------
	/**
	 * getter for material
	 */
	public Material getMaterial(){
		return this.material;
	}
	
}
