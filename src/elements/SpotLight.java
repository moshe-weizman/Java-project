/**
 * 
 */
package elements;

import primitives.*;

/**
 * class to represent a light from a spot
 *
 */
public class SpotLight extends PointLight {

	Vector direction;

	/**
	 * ctor for spotLight
	 * 
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param dir
	 */
	public SpotLight(Color color, Point3D position, double kL, double kQ, Vector dir) {
		super(color, position, kL, kQ);
		this.direction = dir.normalize();
	}

	// ************************************ Operations,Getters
	// ***********************
	/*
	 * func to get the intensity of light according to a specific point param -
	 * point/
	 */
	@Override
	public Color getIntensity(Point3D point) {
		double temp = Util.alignZero(this.direction.dotProduct(point.subtract(this.position).normalize()));
		// Il=I0*max(0,dir*l)/Kc+d*Kl+d^2*Kq
		if (temp <= 0)
			return Color.BLACK;
		return super.getIntensity(point).scale(temp);
	}

	/*
	 * func to get the direction vector (of the spot)
	 */
	@Override
	public Vector getD(Point3D point) {
		return this.direction;
	}
}
