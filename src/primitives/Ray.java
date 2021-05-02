package primitives;

/**
 * class to represent a ray
 */
public class Ray {
	private Point3D _point;
	private Vector normalVec;

	// ***************** Constructors **********************//
	/**
	 * ctor for Ray, gets Point3D &Vector
	 * @param _point
	 * @param normalVec
	 */
	public Ray(Point3D _point, Vector normalVec) {
		this._point = _point;
		this.normalVec = normalVec.normalize();
	}

	/**
	 * copy ctor for Ray 
	 * @param other
	 */
	public Ray(Ray other) {
		this._point = other._point;
		this.normalVec = other.normalVec;
	}

	// ***************** Getters ********************** //
	/**
	 * getter for _point
	 * @return
	 */
	public Point3D getPoint() {
		return _point;
	}

	/**
	 * getter for normalVec
	 * @return
	 */
	public Vector getNormalVec() {
		return normalVec;
	}

	// ***************** Administration ******************** //
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return (this._point.equals(other._point) && this.normalVec.equals(other.normalVec));
	}
	
	// ------------------------------------------------------

	@Override
	public String toString() {
		return this._point.toString() + ", vec: " + this.normalVec.toString() + ",";
	}

	// ***************** Operations ******************** //
}
