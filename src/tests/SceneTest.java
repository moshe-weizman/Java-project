package tests;

import static org.junit.Assert.*;



import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.*;

public class SceneTest {

	@Test
	public void sphereInSphere3() {
	Scene scene = new Scene("scene ");
	scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1)),
	1000);
	scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 3));
	scene.setBackground(new Color(0, 191, 0));
	Geometries geometries = new Geometries();
	scene.setModel3D(geometries);
	Material mat = new Material(0.5, 0.5, 20, 0.2, 0.2);
    
	
	Color side = new Color(0, 191, 255);

	Triangle triangleSeaOpposite = new Triangle(mat, side, new Point3D(-150, 100, 500),
	new Point3D(150, 100, 500), new Point3D(0, -100, 500));

	Triangle triangleSeaOpposite1L = new Triangle(mat, side, new Point3D(-150, 100, 500),
	new Point3D(-150, -100, 500), new Point3D(0, -100, 500));

	Triangle triangleSeaOpposite2R = new Triangle(mat, side, new Point3D(150, -100, 500),
	new Point3D(150, 100, 500), new Point3D(0, -100, 500));

	Triangle triangleSeaSideRight1D = new Triangle(mat,side, new Point3D(150, -100, 500),
	new Point3D(150, 100, 500), new Point3D(150, 100, -600));

	Triangle triangleSeaSideRight2U = new Triangle(mat, side, new Point3D(150, -100, 500),
	new Point3D(150, -100, -600), new Point3D(150, 100, -600));

	Triangle triangleSeaSideLeft1D = new Triangle(mat, side, new Point3D(-150, 100, 500),
	new Point3D(-150, -100, 500), new Point3D(-150, 100, -600));

	Triangle triangleSeaSideLeft1U = new Triangle(mat, side, new Point3D(-150, -100, -600),
	new Point3D(-150, -100, 500), new Point3D(-150, 100, -600));

	Triangle triangleSeaGround1 = new Triangle(mat, new Color(152, 251, 152), new Point3D(-150, 100, 500),
	new Point3D(150, 100, 500), new Point3D(0, 100, -600));

	Triangle triangleSeaGroundL = new Triangle(mat, new Color(152, 251, 152), new Point3D(-150, 100, 500),
	new Point3D(-150, 100, -600), new Point3D(0, 100, -600));

	Triangle triangleSeaGroundR = new Triangle(mat, new Color(152, 251, 152), new Point3D(150, 100, 500),
	new Point3D(150, 100, -600), new Point3D(0, 100, -600));

	Triangle triangleSeaCeiling1 = new Triangle(mat, new Color(135, 206, 250), new Point3D(-150, -100, 500),
	new Point3D(150, -100, 500), new Point3D(0, -100, -600));

	Triangle triangleSeaCeilingL = new Triangle(mat, new Color(135, 206, 250), new Point3D(-150, -100, 500),
	new Point3D(-150, -100, -600), new Point3D(0, -100, -600));

	Triangle triangleSeaCeilingR = new Triangle(mat, new Color(135, 206, 250), new Point3D(150, -100, 500),
	new Point3D(150, -100, -600), new Point3D(0, -100, -600));
	// -----------------------
	Sphere sphere = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0), 20,
	new Point3D(60, 33, 200));

	Triangle triangleBody = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0),
	new Point3D(60, 52, 179), new Point3D(120, 37, 179), new Point3D(60, 12, 179));

	Triangle triangleTail = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0),
	new Point3D(120, 35, 179), new Point3D(130, 50, 179), new Point3D(130, 10, 179));

	Triangle triangleMouth = new Triangle(new Material(0.5, 0.5, 20, 0, 0.2), new Color(0, 191, 255),
	new Point3D(60, 33, 179), new Point3D(35, 40, 179), new Point3D(35, 26, 179));

	Sphere sphereEye = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 0, 0), 2,
	new Point3D(50, 23, 179));

	Sphere sphereBlu1 = new Sphere(new Material(0.5, 0.5, 20, 0, 1), new Color(58, 201, 212), 4,
	new Point3D(20, 18, 200));

	Sphere sphereBlu2 = new Sphere(new Material(0.5, 0.5, 20, 0, 1), new Color(58, 201, 212), 6,
	new Point3D(0, -12, 200));

	Sphere sphereBlu3 = new Sphere(new Material(0.5, 0.5, 20, 0, 1), new Color(58, 201, 212), 8,
	new Point3D(-30, -50, 200));
	// --------------------

	Sphere sphere1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0), 20,
	new Point3D(-60, 13, 200));

	Triangle triangleBody1 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0),
	new Point3D(-60, 32, 179), new Point3D(0, 17, 179), new Point3D(-60, -8, 179));

	Triangle triangleTail1 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0),
	new Point3D(0, 15, 179), new Point3D(10, 30, 179), new Point3D(10, -10, 179));

	Triangle triangleMouth1 = new Triangle(new Material(0.5, 0.5, 20, 0, 0.2), new Color(0, 191, 255),
	new Point3D(-60, 13, 179), new Point3D(-85, 20, 179), new Point3D(-85, 6, 179));

	Sphere sphereEye1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 0, 0), 2,
	new Point3D(-70, 3, 179));

	Sphere sphereBlu1_1 = new Sphere(new Material(0.5, 0.5, 20, 0, 1), new Color(58, 201, 212), 4,
	new Point3D(-100, -2, 200));

	Sphere sphereBlu1_2 = new Sphere(new Material(0.5, 0.5, 20, 0, 1), new Color(58, 201, 212), 6,
	new Point3D(-120, -32, 200));

	Sphere sphereBlu1_3 = new Sphere(new Material(0.5, 0.5, 20, 0, 1), new Color(58, 201, 212), 8,
	new Point3D(-140, -70, 200));
	// -----------------
	Triangle star1 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	new Point3D(-50, 40, 50), new Point3D(-150, 40, 50), new Point3D(-100, 70, 30));

	Triangle star2 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	new Point3D(-90, 40, 50), new Point3D(-116, 40, 50), new Point3D(-100, 10, 80));

	Triangle star3 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	new Point3D(-100, 70, 30), new Point3D(-138, 90, 30), new Point3D(-110, 40, 30));

	Triangle star4 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	new Point3D(-100, 70, 30), new Point3D(-70, 90, 30), new Point3D(-80, 40, 30));

	Sphere sp1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 255, 0), 4, new Point3D(-90, 60, 28));
	Sphere sp2 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 255, 0), 4, new Point3D(-100, 40, 28));
	Sphere sp3 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 255, 0), 4, new Point3D(-110, 60, 28));

	scene.addGeometry( sp3, sp2, sp1, star4, star3, star2, star1,  sphere1, sphereBlu1_3, sphereBlu1_2,
	sphereBlu1_1, sphereEye1, triangleMouth1, triangleTail1, triangleBody1, sphere, triangleBody,
	triangleTail, triangleMouth, sphereEye, sphereBlu1, sphereBlu2, sphereBlu3, triangleSeaCeilingR,
	triangleSeaCeilingL, triangleSeaCeiling1, triangleSeaGroundR, triangleSeaGroundL, triangleSeaGround1,
	triangleSeaSideLeft1U, triangleSeaSideLeft1D, triangleSeaSideRight2U, triangleSeaSideRight1D,
	triangleSeaOpposite1L, triangleSeaOpposite, triangleSeaOpposite2R);

	//scene.addLightSource(new pointLight(new Color(127,0,150), new Point3D(0, -80, 0), 0.0001, 0.000000001));
	scene.addLightSource(new SpotLight(new Color(128, 0, 128), new Point3D(250, 90, -100), 0.5, 0.5, new Vector(-250, -90, 270)));
    scene.addLightSource(new SpotLight(new Color(0, 255, 0), new Point3D(-100,-50, -100), 0.5,0.5, new Vector(100, 50, 279)));
	ImageWriter imageWriter = new ImageWriter("אקווריום", 500, 500, 500, 500);

	Render render = new Render(imageWriter, scene);
	render.renderImage();
	render.writeToImage();
	}
}
