package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

/**
 * interface includes func to find intersections between a ray and geometry
 * shape.
 */
public interface Intersectable {
	/**
	 * func to find intersections between a ray and geometry shape.
	 * 
	 * @param myRay
	 * @return list of the intersections points.
	 */
	public static final List<GeoPoint> EMPTY_LIST = new ArrayList<GeoPoint>();

	public List<GeoPoint> findIntersections(Ray myRay);

	static class GeoPoint {
		public Geometry geometry;
		public Point3D point;

		/**
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}

		/**
		 * @return the geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}

		/**
		 * @return the point
		 */
		public Point3D getPoint() {
			return point;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return (this.point.equals(other.point) && this.geometry.equals(other.geometry));
		}
		
		
	}
}