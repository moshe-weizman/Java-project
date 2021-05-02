/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * @author Home
 *
 */
public class ShadowTests {

	/**
	 * Test method for {@link renderer.Render#renderImage()}.
	 */
	@Test
	public void testRenderWithShadowImage() {
		Scene scene = new Scene("scene with sphere && triangle && shadow");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0,-1000), new Vector(0,-1, 0), new Vector(0, 0, 1)), 1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(0,0,255),
				new Point3D(-160,80,50), new Point3D(-80,160,50), new Point3D(-160,160,100));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0,255), 150,
				new Point3D(0,0,200));
		scene.addGeometry(triangle, sphere);

		//scene.addLightSource(
				//new pointLight(new Color(255, 100, 100), new Point3D(-200, -200, -500), 0, 0.000001, 0.000001));
		scene.addLightSource(new SpotLight(new Color(255, 150,150), new Point3D(-200,200,0),  0.0001, 0.000000001,
				new Vector(200,-200,100)));
		//scene.addLightSource(new DirectionalLight(new Color(255,255,255),new Vector(-10, 5, 1)));

		ImageWriter imageWriter = new ImageWriter("צל משולש על ספירה", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
	
//--------------------------------------------------------------
	/**
	 * Test method for {@link renderer.Render#renderImage()}.
	 */
	@Test
	public void moreTestWithShadow() {
		Scene scene = new Scene("scene with sphere && triangle && shadow");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0,-1000), new Vector(0,-1, 0), new Vector(0, 0, 1)), 1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(0,0,255),
				new Point3D(-160,80,100), new Point3D(-80,160,100), new Point3D(-160,160,100));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0,255), 120,
				new Point3D(0,0,200));
		scene.addGeometry(triangle, sphere);

		scene.addLightSource(new SpotLight(new Color(255, 255 ,255), new Point3D(-250,250,0), 0.0001, 0.000000001,
				new Vector(250,-250,200)));

		ImageWriter imageWriter = new ImageWriter("צל זעיר משולש ", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
	
//-----------------------------------------------------------------------------------------
	/**
	 * .
	 */
	@Test
	public void moreTest() {
		Scene scene = new Scene("scene with sphere && triangle && shadow");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0,-1000), new Vector(0,-1, 0), new Vector(0, 0, 1)), 1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(0,0,255),
				new Point3D(-160,80,100), new Point3D(-80,160,100), new Point3D(-160,160,100));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0,255), 100,
				new Point3D(0,0,200));
		scene.addGeometry(triangle, sphere);

		scene.addLightSource(new SpotLight(new Color(255, 255 ,255), new Point3D(-150,150,95),  0.0001, 0.000000001,
				new Vector(160,-160,100)));

		ImageWriter imageWriter = new ImageWriter("צל ענקקק משולש ", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
	
//------------------------------------------------------------------
	/**
	 * .
	 */
	@Test
	public void normalTest() {
		Scene scene = new Scene("scene with sphere && triangle && shadow");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0,-1000), new Vector(0,-1, 0), new Vector(0, 0, 1)), 1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(0,0,255),
				new Point3D(-145,50,50), new Point3D(-70,125,50), new Point3D(-145,125,50));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0,255), 150,
				new Point3D(0,0,400));
		scene.addGeometry(triangle, sphere);

		scene.addLightSource(new SpotLight(new Color(255, 150 ,150), new Point3D(-200,150,-300),  0.0001, 0.000000001,
				new Vector(250,-250,450)));

		ImageWriter imageWriter = new ImageWriter("משולש עם צל רגיל ", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
//---------------------------------------------
	
}

