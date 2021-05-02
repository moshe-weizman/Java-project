/**
 * 
 */
package elements;

import primitives.*;

/**
 * class to represent directional light, f.e. sun etc.
 *
 */
public class DirectionalLight extends Light implements LightSource {
	Vector _direction;

	/**
	 * ctor for directional light;
	 * 
	 * @param _direction - vector of direction
	 */
	public DirectionalLight(Color color, Vector direction) {
		super(color);
		this._direction = direction.normalize();
	}

//******************************** Getters ********************************	
	/**
	 * @return the _direction
	 */
	public Vector getDirection() {
		return _direction;
	}

	/**
	 * func to get intensity of light in given point/
	 * this will be a permanent value
	 */
	public Color getIntensity(Point3D point) {
		return this.getIntensity();
	}

	/**
	 * func to get l in varios point
	 */
	public Vector getL(Point3D point) {
		return this._direction;
	}

	/**
	 * func to get the direction of light in given point.
	 */
	public Vector getD(Point3D point) {
		return this._direction;
	}

}
