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

public class ReflectedAndRefractedTests {

	@Test
	public void SphereIntoSphere() {
		Scene scene = new Scene("scene with spheres");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1)),
				1000);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Sphere externalSphere = new Sphere(new Material(0.5, 0.5, 20, 0.4, 0.4), new Color(0, 0, 255), 120,
				new Point3D(0, 0, 200));
		Sphere internalSphere = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(200, 0, 255), 40,
				new Point3D(-50, 50, 200));
		scene.addGeometry(internalSphere, externalSphere);

		scene.addLightSource(new SpotLight(new Color(255,255, 255), new Point3D(-50,-50,-300), 0.0001, 0.000000001,
				new Vector(0,0,1)));

		ImageWriter imageWriter = new ImageWriter("ספירה בתוך ספירה ", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		//render.printGrid(50);
		render.writeToImage();
	}
	//-------------------------------------------------------------------------------
	@Test
	public void sphereInSphere() {
	Scene scene = new Scene("scene with sphereInSphere");
	scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1)),
	1000);
	scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
	scene.setBackground(Color.BLACK);
	Geometries geometries = new Geometries();
	scene.setModel3D(geometries);

	Sphere sphere = new Sphere(new Material(0.5, 0.5, 20, 0.4, 0.4), new Color(0, 0, 255), 120,
	new Point3D(0, 0, 200));
	Sphere sphere1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 0, 0), 40, new Point3D(0, 0, 200));
	scene.addGeometry(sphere1, sphere);

	scene.addLightSource(new PointLight(new Color(255, 192, 203), new Point3D(-10, 40, 10), 0.0001, 0.000000001));

	ImageWriter imageWriter = new ImageWriter("טסט של משה ", 500, 500, 500, 500);

	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
	}
//---------------------------------------------------------------------

	/**
	 * sphere && spot && point light
	 */
	@Test
	public void combinedTest() {

		Scene scene = new Scene("scene with sphere && triangles");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000));
		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20,0.5,0.5), new Color(0, 0, 200), 550,
				new Point3D(200, -200, -1000));
		scene.addGeometry(triangle, triangle2, sphere);

		scene.addLightSource(
				new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -500),  0.000001, 0.000001));
		scene.addLightSource(new SpotLight(new Color(0, 255, 0), new Point3D(400, -200, -300),  0.0001, 0.000000001,
				new Vector(-200, 0, -700)));
		scene.addLightSource(new DirectionalLight(new Color(147,112,219),new Vector(-10, 5, 1)));

		ImageWriter imageWriter = new ImageWriter("עם שקיפות משולשים וכדור", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
//---------------------------------------------------------
	@Test
	public void testtest() {
	Scene scene = new Scene("scene with sphereInSphere");
	scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1)),
	1000);
	scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
	scene.setBackground(Color.BLACK);
	Geometries geometries = new Geometries();
	scene.setModel3D(geometries);

	Sphere sphere = new Sphere(new Material(0.5,0.5,20,0.5,0.5), new Color(0, 0, 255), 120,
	new Point3D(0, 0, 200));
	scene.addGeometry(sphere);
	scene.addLightSource(new SpotLight(new Color(255, 192, 203), new Point3D(-200, 200,0),
			0.0001, 0.000000001, new Vector(200,-200, 200)));

	ImageWriter imageWriter = new ImageWriter("רק ספירה אחת ", 500, 500, 500, 500);

	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
	}
}
