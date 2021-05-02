package geometries;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import primitives.Point3D;
import primitives.Ray;

/**
 * class to represent collection of geometry shapes.
 */
public class Geometries implements Intersectable {

	List<Intersectable> _geometries;
	
	/**
	 * ctor for Geometries; gets a Intersectable shape
	 * @param shape
	 */
	public Geometries(Intersectable... shapes) {
		this._geometries=new ArrayList<Intersectable>(); 
		for (Intersectable shape : shapes)
			this._geometries.add(shape);
	}
	
	//-------------------------------------------------------------
	/**
	 * func to add a geometry to the list of geometries
	 * @param shape
	 */
	public void add(Intersectable shape) {
		this._geometries.add(shape);
	}
	
	//----------------------------------------------------------------
	@Override
	public List<GeoPoint> findIntersections(Ray myRay) {
		ArrayList<GeoPoint> listOfPoints = new ArrayList<GeoPoint>();
		for (Intersectable geom : _geometries) {
			listOfPoints.addAll(geom.findIntersections(myRay));
		}
		return listOfPoints;
	}
}
