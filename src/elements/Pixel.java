/**
 * 
 */
package elements;

import primitives.Color;

/**
 * class to represent a pixel at the viewPlane
 */
public class Pixel {
	ColoredRay topLeft, topRight, downLeft, downRight;
	double weight;

//----------------------------------------------------------------------   
	/**
	 * ctor. build a pixel from 4 rays.
	 * 
	 * @param topLeft
	 * @param topRight
	 * @param downLeft
	 * @param downRight
	 * @param weight
	 */
	public Pixel(ColoredRay topLeft, ColoredRay topRight, ColoredRay downLeft, ColoredRay downRight, double weight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.downLeft = downLeft;
		this.downRight = downRight;
		this.weight = weight;
	}

//-----------------------------------------------------------------------------
	/**
	 * @return the topLeft
	 */
	public ColoredRay getTopLeft() {
		return topLeft;
	}

	/**
	 * @return the topRight
	 */
	public ColoredRay getTopRight() {
		return topRight;
	}

	/**
	 * @return the downLeft
	 */
	public ColoredRay getDownLeft() {
		return downLeft;
	}

	/**
	 * @return the downRight
	 */
	public ColoredRay getDownRight() {
		return downRight;
	}

	/**
	 * @return the wightOfRay
	 */
	public double getWeight() {
		return weight;
	}

//----------------------------------------------------
	/**
	 * func to check if pixel has 4 rays with the same color
	 * @return true if it really have; false if not
	 */
	public boolean equalsColors() {
		Color colorDownLeft = this.downLeft.getColor();
		Color colorDownRight = this.downRight.getColor();
		Color colorTopLeft = this.topLeft.getColor();
		Color colorTopRight = this.topRight.getColor();

		if (!colorDownLeft.equals(colorDownRight) || !colorDownLeft.equals(colorTopLeft)
				|| !colorDownLeft.equals(colorTopRight) || !colorDownRight.equals(colorTopLeft)
				|| !colorDownRight.equals(colorTopRight) || !colorTopLeft.equals(colorTopRight))
			return false;
		return true;
	}

}
