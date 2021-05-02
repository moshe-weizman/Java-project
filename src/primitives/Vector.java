package primitives;

/**
 * class to present a vector in 3D. using linear math.
 */
public class Vector {
	private Point3D _head;
	public static Point3D ZERO = new Point3D(0,0,0);

	// ***************** Constructors ********************** //
	/**
	 * ctor of Vector, gets Point3D
	 * @param Point3D_head
	 */
	public Vector(Point3D _head) {
		//if (Util.isZero(_head.getX().get()) && Util.isZero(_head.getY().get()) && Util.isZero(_head.getZ().get()))
			//throw new IllegalArgumentException("vector 0 is illegal");
		if (_head.equals(ZERO))
			throw new IllegalArgumentException("vector 0 is illegal");
		this._head = _head;
	}
	//---------------------------------------------
	/**
	 * ctor of Vector; gets 3 coordinates
	 * @param Coordinate_x
	 * @param Coordinate_y
	 * @param Coordinate_z
	 */
	public Vector(Coordinate _x, Coordinate _y, Coordinate _z) {
		this._head = new Point3D(_x, _y, _z);
		if (_head.equals(ZERO))
			throw new IllegalArgumentException("vector 0 is illegal");
	}
	//-----------------------------------------------------
	/**
	 * ctor of Vector; gets 3 double numbers
	 * @param double_x
	 * @param double_y
	 * @param double_z
	 */
	public Vector(double _x, double _y, double _z) {
		//if (Util.isZero(_head.getX().get()) && Util.isZero(_head.getY().get()) && Util.isZero(_head.getZ().get()))
			//throw new IllegalArgumentException("vector 0 is illegal");
		this._head = new Point3D(_x, _y, _z);
		if (_head.equals(ZERO))
			throw new IllegalArgumentException("vector 0 is illegal");
	}
	//--------------------------------------------------------
	/**
	 * copy ctor
	 * @param Vector other
	 */
	public Vector(Vector other) {
		//if (Util.isZero(other._head.getX().get()) && Util.isZero(other._head.getY().get())
				//&& Util.isZero(other._head.getZ().get()))
			//throw new IllegalArgumentException("vector 0 is illegal");
		this._head = other._head;
	}

	// ***************** Getters ********************** //
	/**
	 * getter for point3D _head
	 * @return Point3D
	 */
	public Point3D getHead() {
		return _head;
	}

	// ***************** Administration ******************** //

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return (this._head.equals(other._head));
	}

	// ------------------------------------------------
	
	@Override
	public String toString() {
		return this._head.toString();
	}

	// ***************** Operations ******************** //
	/**
	 * func to add vectors
	 * 
	 * @param other vector
	 * @return new vector
	 */
	public Vector add(Vector other) {
		return new Vector(this._head.add(other._head));
	}

	// -------------------------------------------------
	/**
	 * func to subtract 2 vectors
	 * 
	 * @param other vector
	 * @return new vector
	 */
	public Vector subtract(Vector other) {
		return new Vector(this._head.subtract(other._head));
	}

	// -------------------------------------------------
	/**
	 * func to multiply vector and scalar
	 * 
	 * @param num
	 * @return new vector
	 */
	public Vector scale(double num) {
		return new Vector(this._head.getX().scale(num), this._head.getY().scale(num), this._head.getZ().scale(num));
	}

	// -----------------------------------------------------
	/**
	 * func to find length of vector
	 * 
	 * @return double - the length
	 */
	public double length() {
		return this._head.distance(ZERO);
	}

	// -------------------------------------------------------------
	/**
	 * func to find the square of length of a vector
	 * @return double - the desired length
	 */
	public double length2() {
		return this._head.distance2(ZERO);
	}
	
	//--------------------------------------------------------------
	/**
	 * func to make dot-product between 2 vectors
	 * 
	 * @param other vector
	 * @return scalar
	 */
	public double dotProduct(Vector other) {
		return this._head.getX().multiply(other._head.getX()).get() + this._head.getY().multiply(other._head.getY()).get()
				+ this._head.getZ().multiply(other._head.getZ()).get();
	}

	// -------------------------------------------------------------
	/**
	 * func to make cross-product between 2 vectors
	 * 
	 * @param other vector
	 * @return new vector
	 */
	public Vector crossProduct(Vector other) {
		return new Vector((this._head.getY().multiply(other._head.getZ())).subtract(this._head.getZ().multiply(other._head.getY())),
				(this._head.getZ().multiply(other._head.getX())).subtract(this._head.getX().multiply(other._head.getZ())),
				(this._head.getX().multiply(other._head.getY())).subtract(this._head.getY().multiply(other._head.getX())));
	}

	// ---------------------------------------------------------------
	/**
	 * func to find the normalized vector of a given vector
	 * 
	 * @return new vector - the normalized vector
	 */
	public Vector normalize() {
		double norm = 1/(this.length());		
		return new Vector(this._head.getX().scale(norm),
				this._head.getY().scale(norm),
				this._head.getZ().scale(norm));
	}
}
