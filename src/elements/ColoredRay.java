/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;

/**
 *class to represent ray that has color and weight 
 *
 */
public class ColoredRay {
	Ray ray;
	Color color;
	
	
	/**
	 * ctor for coloredRay
	 * @param ray
	 * @param color
	 * @param weight
	 */
	public ColoredRay(Ray ray, Color color) {
		this.ray = ray;
		this.color = color;
	}
	
	/**
	 * ctor for coloredRay
	 * @param ray
	 * @param color
	 * @param weight
	 */
	public ColoredRay(Ray ray) {
		this.ray = ray;
		this.color = color.BLACK;
	}
	
	/**
	 * @return the ray
	 */
	public Ray getRay() {
		return ray;
	}
	
	/**
	 * @return the rayPoint
	 */
	public Point3D getPoint() {
		return ray.getPoint();
	}

	/**
	 * @param ray the ray to set
	 */
	public void setRay(Ray ray) {
		this.ray = ray;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
