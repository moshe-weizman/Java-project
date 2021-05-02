package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {
	@Test
	public void basicRendering() {

		Scene scene = new Scene("Test scene");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)), 100);

		// scene.setDistance(100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);
		geometries.add(new Sphere(/*new Color(100, 100,100 ),*/  50, new Point3D(0, 0, 150)));

		geometries.add(new Triangle(new Color(255,0,0 ),  new Point3D(100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(100, 100, 149)));

		geometries.add(new Triangle( new Color(0, 255,0 ),  new Point3D(100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(100, -100, 149)));

		geometries.add(new Triangle( new Color(0, 0,255 ), new Point3D(-100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(-100, 100, 149)));

		geometries.add(new Triangle(/*new Color(100, 100,100 ),*/ new Point3D(-100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 1000, 1000);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(100);
		render.writeToImage();
	}

//-----------------------------------------------
//	/**
//	 * func to test func Render.getClosestPoint
//	 */
//	@Test
//	public  void getClosestPointTest(){
//		List<Point3D> intersectionPoints= new ArrayList<Point3D>();
//		intersectionPoints.add(new Point3D(0, 0, 3));
//		intersectionPoints.add(new Point3D(0, 0, 4));
//		intersectionPoints.add(new Point3D(0, 3, 3));
//		intersectionPoints.add(new Point3D(3, 3, 3));
//		
//		Scene scene = new Scene("Test scene");
//		scene.setCameraAndDistamce(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)),100);
//		scene.setAmbientLight(new AmbientLight(new Color(0, 200, 100), 3));
//		scene.setBackground(new Color(0, 0, 0));
//		Geometries geometries = new Geometries();
//		scene.setModel3D(geometries);
//		
//		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
//		Render render = new Render(imageWriter, scene);
//
//		Point3D p=render.getClosestPoint(intersectionPoints);
//		assertEquals(new Point3D(0, 0, 3), p);
//		
//	}
}