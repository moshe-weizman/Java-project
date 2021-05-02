/**
 * 
 */
package elements;

import primitives.Color;

/**
 * class to represent ambient light
 *
 */
public class AmbientLight extends Light {
	//Color _iP;
	double _kA;
	
	
	/**
	 * ctor
	 * @param _color
	 * @param _kA
	 */
	public AmbientLight(Color color, double kA) {
		super(color.scale(kA));
	}
	
//------------------------------------------------------------
}
