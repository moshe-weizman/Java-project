/**
 * 
 */
package elements;

import primitives.*;

/**
 *interface for light-source functions
 *
 */
public interface LightSource {
	Color getIntensity(Point3D point);
	Vector getL(Point3D point);
	Vector getD(Point3D point);
}
