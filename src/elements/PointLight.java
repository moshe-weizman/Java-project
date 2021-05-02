/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class to represent a point light from a source of light
 *
 */
public class PointLight extends Light implements LightSource {
	Point3D position;
	double kC, kL, kQ;

//****************************** Constructor ***************************************	
	/**
	 * ctor of pointLight
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color color, Point3D position, double kL, double kQ) {
		super(color);
		this.position = position;
		this.kC = 1;
		this.kL = kL;
		this.kQ = kQ;
	}

//************************************************************************************
	/**
	 * func to get the intensity of light according to a specific point
	 * param - point/
	 */
	public Color getIntensity(Point3D point) {
		Color i0 = this.getIntensity();
		double d = this.position.distance(point);
		return (i0.reduce(this.kC + this.kL * d + this.kQ * d * d));
	}

	/**
	 * func to get l -vector in varios point
	 */
	public Vector getL(Point3D point) {
		return point.subtract(this.position).normalize();
	}

	/**
	 * func to get the direction of light in given point.
	 */
	public Vector getD(Point3D point) {
		return this.getL(point);
	}

}
