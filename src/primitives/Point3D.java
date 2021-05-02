package primitives;

/**
 * class to represent a 3D-point
 */
public class Point3D {
		
	 private Coordinate _x, _y, _z;

	// ***************** Constructors ********************** //
	 /**
	  * ctor for Point3D, gets 3 Coordinates
	  * @param Coordinate_x
	  * @param Coordinate_y
	  * @param Coordinate_z
	  */
	public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
		this._x = _x;
		this._y = _y;
		this._z = _z;
	}

	//--------------------------------------------------------
	/**
	 * ctor for Point3D, gets 3 doubles
	 * @param double x
	 * @param double y
	 * @param double z
	 */
	public Point3D(double x, double y, double z) {
		this._x = new Coordinate(x);
		this._y = new Coordinate(y);
		this._z = new Coordinate(z);
	}
	
	//----------------------------------------------
	/**
	 * ctor copy
	 * @param Point3D other
	 */
	public Point3D(Point3D other) {
		this._x = other._x;
		this._y = other._y;
		this._z = other._z;
	}

	// ***************** Getters ********************** //
	/**
	 * getter for _x
	 * @return Coordinate_x
	 */
	public Coordinate getX() {
		return _x;
	}

	/**
	 *  getter for _y
	 * @return Coordinate _y
	 */
	public Coordinate getY() {
		return _y;
	}

	/**
	 *  getter for _z
	 * @return Coordinate_z
	 */
	public Coordinate getZ() {
		return _z;
	}

	// ***************** Administration ******************** //	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return this._x.equals(other._x) && this._y.equals(other._y) && this._z.equals(other._z);
	}
	
    //--------------------------------------------------------
	@Override
	public String toString() {
		return "(" + this._x.toString() + ", " + this._y.toString() + ", " + this._z.toString() + ")";
	}

	// ***************** Operations ******************** //
	/**
	 * func to add two points
	 * 
	 * @param other Point3D
	 * @return new Point3D
	 */
	public Point3D add(Point3D other) {
		return new Point3D(this._x.add(other._x), this._y.add(other._y), this._z.add(other._z));
	}

	// -------------------------------------------------------
	/**
	 * func to add vector to Point3D
	 * 
	 * @param vec
	 * @return new Point3D
	 */
	public Point3D add(Vector vec) {
		return this.add(vec.getHead());
	}

	// ------------------------------------------------
	/**
	 * func to subtract 2 Point3D
	 * 
	 * @param other Point3D
	 * @return new Vector
	 */
	public Vector subtract(Point3D other) {
		return new Vector(
			  this._x.subtract(other._x), this._y.subtract(other._y), this._z.subtract(other._z));
	}

	// --------------------------------------------------------
	/**
	 * func to calculate the square of distance between 2 Point3D
	 * 
	 * @param other Point3D
	 * @return double - the squre of distance
	 */
	public double distance2(Point3D other) {
		double square= (this._x.subtract(other._x)).multiply(this._x.subtract(other._x)).get()
				+ (this._y.subtract(other._y)).multiply(this._y.subtract(other._y)).get()
				+ (this._z.subtract(other._z)).multiply(this._z.subtract(other._z)).get();
		if (Util.isOne(square))
			return 1;
		return square;
	}

	// -----------------------------------------------------
	/**
	 * func to find distance between 2 Point3D
	 * 
	 * @param other Point3D
	 * @return double - the distance
	 */
	public double distance(Point3D other) {
		return Math.sqrt(this.distance2(other));
	}
}
