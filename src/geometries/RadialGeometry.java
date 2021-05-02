package geometries;

public abstract class RadialGeometry extends Geometry {
	protected double _radius;

	// *************************** Constructors *******************//
	/**
	 * ctor for RadialGeometry
	 * 
	 * @param _radius
	 */
	public RadialGeometry(double _radius) {
		if (_radius <= 0)
			throw new IllegalArgumentException("radius can't be less than 0");
		this._radius = _radius;
	}

	/**
	 * copt ctor for RadialGeometry
	 * 
	 * @param other
	 */
	public RadialGeometry(RadialGeometry other) {
		this._radius = other._radius;
	}

	// ***************** Getters ********************** //
	/**
	 * getter for double _radiud
	 * 
	 * @return
	 */
	public double getRadius() {
		return _radius;
	}
}
