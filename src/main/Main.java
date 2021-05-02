//Uri Erlikh 066200569 urier3@gmail.com
//Moshe Weizmann 305343931 moshew1991@gmail.com

package main;

import primitives.*;
import geometries.*;

public class Main {

	public static void main(String[] args) {
		try {
			Point3D p1 = new Point3D(1, 0, 1);
			Point3D p2 = new Point3D(1, 0, -1);

			System.out.println(p1.equals(p2));
			System.out.println(p1.add(p2));
			System.out.println(p1.subtract(p2));
			System.out.println(p1.distance(p2));
			System.out.println("------------------------------");
			// ---------------------------------------------
			Vector v1 = new Vector(p1);
			Vector v2 = new Vector(p2);

			System.out.println(v1.equals(v2));
			System.out.println(v1.length());
			System.out.println(v1.add(v2));
			System.out.println(v1.getHead());
			System.out.println(v1.normalize());
			System.out.println(v1.scale(4));
			System.out.println(v1.subtract(v2));
			System.out.println(v1.dotProduct(v2));
			System.out.println(v1.crossProduct(v2));
			System.out.println("-------------------------------------------");
			// ---------------------------------------------
			System.out.println(v1.dotProduct(new Vector(new Point3D(2, 0, 2))));
			System.out.println(v1.dotProduct(new Vector(new Point3D(1, 0, 3))));
			System.out.println(v1.dotProduct(new Vector(new Point3D(-2, -4, -3))));
			System.out.println(v1.dotProduct(new Vector(new Point3D(-1, 0, -1))));
			System.out.println("-------------------------------------------");
			// ----------------------------------------------------------------
			// System.out.println(v1.crossProduct(new Vector(new Point3D(2,0,2))));
			System.out.println(v1.crossProduct(new Vector(new Point3D(1, 0, 3))));
			System.out.println(v1.crossProduct(new Vector(new Point3D(-2, -4, -3))));
			//System.out.println(v1.crossProduct(new Vector(new Point3D(-1,0,-1))));
			System.out.println("--------------------------------------");
			// ----------------------------------------------------
			Ray ray = new Ray(new Point3D(1, 0, 1), new Vector(v2));
			System.out.println(ray);

			Tube tube = new Tube(3, ray);
			System.out.println(tube);

			Cylinder cylinder = new Cylinder(3, ray, 1.25);
			System.out.println(cylinder);
			System.out.println(v1.normalize().length());
		} catch (IllegalArgumentException e) {
		}
	}

}

