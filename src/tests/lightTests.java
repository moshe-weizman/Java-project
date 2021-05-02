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
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * @author Home
 *
 */
public class lightTests {

	/**
	 * Test method for {@link renderer.Render#renderImage()}. spot && 2 triangles
	 */
	@Test
	public void spotLightTest3() {

		Scene scene = new Scene("light test");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000));

		scene.addGeometry(triangle, triangle2);

		scene.addLightSource(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -1000),  0.000001,
				0.0000005, new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		// render.printGrid(100);
		render.writeToImage();
	}

//------------------------------------------------------------------------
	/**
	 * point light && 2 triangles
	 */
	@Test
	public void pointLight1Test() {

		Scene scene = new Scene("point1 test");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000));

		scene.addGeometry(triangle, triangle2);

		scene.addLightSource(
				new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -1000),  0.000001, 0.0000005));

		ImageWriter imageWriter = new ImageWriter("point test 3", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		// render.printGrid(100);
		render.writeToImage();
	}

//--------------------------------------------------------------
	/**
	 * direction light && 2 triangles
	 */
	@Test
	public void directionalLightTest() {

		Scene scene = new Scene("directional light test");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000));

		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(15, 15, 15),
				new Point3D(3500, 3500, -2000), new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000));

		scene.addGeometry(triangle, triangle2);

		scene.addLightSource(new DirectionalLight(new Color(255, 100, 100), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("directional test 3", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		// render.printGrid(100);
		render.writeToImage();
	}

//---------------------------------------------------------------------------------
	/**
	 * sphere && spot
	 */
	@Test
	public void spotLightTest() {

		Scene scene = new Scene("scene with sphere && spotLight");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0, 100), 800, new Point3D(0.0, 0.0, -1000));
		scene.addGeometry(sphere);
		scene.addLightSource(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),  0.00001,
				0.000005, new Vector(2, 2, -3)));

		ImageWriter imageWriter = new ImageWriter("Sphere test", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

//------------------------------------------------------------------
	/**
	 * sphere && point light
	 */
	@Test
	public void pointLightTest() {

		Scene scene = new Scene("scene with sphere && pointLight");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -100), new Vector(1, 0, 0), new Vector(0, 0, -1)), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0, 100), 800, new Point3D(0.0, 0.0, -1000));
		scene.addGeometry(sphere);
		scene.addLightSource(
				new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),  0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("טסט עם מנורה", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

//--------------------------------------------------------------------------------
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
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(0, 0, 100), 550,
				new Point3D(200, -200, -1000));
		scene.addGeometry(triangle, triangle2, sphere);

		scene.addLightSource(
				new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -500),  0.000001, 0.000001));
		scene.addLightSource(new SpotLight(new Color(0, 200, 200), new Point3D(400, -200, -300),  0.0001, 0.000000001,
				new Vector(-200, 0, -700)));
		scene.addLightSource(new DirectionalLight(new Color(255,255,255),new Vector(-10, 5, 1)));

		ImageWriter imageWriter = new ImageWriter("משולשים וכדור", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

//--------------------------------------------------------
	/**
	 * japan flag
	 */
	/**
	 * sphere && spot && point light
	 */
	@Test
	public void japanTest() {

		Scene scene = new Scene("japaneese scene");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -200), new Vector(0, -1, 0), new Vector(0, 0, 1),50,5), 100);
		scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 3));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);

		Triangle triangle = new Triangle(new Material(0.5, 0.5, 20), new Color(255, 255, 255),
				new Point3D(-350, -200, 150), new Point3D(350, -200, 150), new Point3D(350, 200, 150));
		Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 20), new Color(255, 255, 255),
				new Point3D(-350, -200, 150), new Point3D(-350, 200, 150), new Point3D(350, 200, 150));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(255, 0, 0), 85, new Point3D(0, 0, 150));
		scene.addGeometry(triangle, triangle2, sphere);

		scene.addLightSource(
				new PointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),  0.00001, 0.000005));
		scene.addLightSource(new SpotLight(new Color(255, 255, 255), new Point3D(0, 100, 0),  0.00001, 0.000005,
				new Vector(0, -100, 100)));

		ImageWriter imageWriter = new ImageWriter("דגל יפן", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

//--------------------------------------------------------
	/**
	 * create a packman shape
	 */
	@Test
	public void packmanTest() {

		Scene scene = new Scene("packman scene");
		scene.setCameraAndDistance(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)),100);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 1));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setModel3D(geometries);
		Triangle triangle = new Triangle(new Material(0, 0, 0),Color.BLACK,
				new Point3D(0,0,110), new Point3D(50,-50, 49), new Point3D(50, 50, 49));
		Sphere sphere = new Sphere(new Material(0.5, 0.5, 20), new Color(255,255, 0),100,
				new Point3D(0, 0, 210));
		scene.addGeometry(triangle, sphere);

//		scene.addLightSource(
//				new pointLight(new Color(255, 100, 100), new Point3D(-200, -200, -100), 0, 0.00001, 0.000005));
		scene.addLightSource(new SpotLight(new Color(255,255,255), new Point3D(-300, -100, 0), 0.00001, 0.000005,
				new Vector(200,100, 190)));

		ImageWriter imageWriter = new ImageWriter("פק-מאן",500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
}
