/**
 * 
 */
package elements;

import primitives.Color;

/**
 *abstract class to represent a light source
 *
 */
public abstract class Light {
	private Color _color;
	
	public Light(Color color) {
		this._color = new Color(color);
	}
	
	/**
	 * func to get the desired power of an ambient light
	 * 
	 */
	public Color getIntensity() {
		return this._color;
	};
}
