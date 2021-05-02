package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

public class DOFTest {

	/* Test method for {@link renderer.Render#renderImage()}.
	 * 3 spheres
	 */
	 @Test
	 public void testRenderImage() {
	 Scene scene = new Scene("scene with spheres");
	 scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1), 250, 7),
	 1000);
	 scene.setAmbientLight(new AmbientLight(Color.BLACK, 3));
	 scene.setBackground(new Color(15, 15, 15));
	 Geometries geometries = new Geometries();
	 scene.setModel3D(geometries);
	 Material mat = new Material(0.3, 0.3, 20, 0.3, 0.1);
	 Color colorWall = new Color(180, 180, 180);
	 Color colorGround = new Color(105, 105, 105);
	 Color colorCube = new Color(10, 10, 10);

	 Triangle triangleSeaOpposite = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite1L = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(-550, -550, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite2R = new Triangle(mat, colorWall, new Point3D(550, -550, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaGround1 = new Triangle(mat, colorGround, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundL = new Triangle(mat, colorGround, new Point3D(-550, 250, 1100),
	 new Point3D(-250, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundR = new Triangle(mat, colorGround, new Point3D(550, 250, 1100),
	 new Point3D(250, 250, 0), new Point3D(0, 250, 0));
	 //---------------
	 Triangle triangleGroundCube1 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(100, 200, 500), new Point3D(200, 200, 500));

	 Triangle triangleGroundCube2 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(200, 200, 600), new Point3D(200, 200, 500));

	 Triangle triangleSideCube1 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(100, 220, 600), new Point3D(100, 220, 500));

	 Triangle triangleSideCube2 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(100, 200, 500), new Point3D(100, 220, 500));

	 Triangle triangleSideCube3 = new Triangle(mat, colorCube, new Point3D(200, 200, 500),
	 new Point3D(100, 200, 500), new Point3D(100, 220, 500));

	 Triangle triangleSideCube4 = new Triangle(mat, colorCube, new Point3D(200, 200, 500),
	 new Point3D(200, 220, 500), new Point3D(100, 220, 500));
	 // -------
	 Triangle triangleGroundCube1_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(-50, 200, 300), new Point3D(50, 200, 300));

	 Triangle triangleGroundCube2_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(50, 200, 400), new Point3D(50, 200, 300));

	 Triangle triangleSideCube1_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(-50, 220, 400), new Point3D(-50, 220, 300));

	 Triangle triangleSideCube2_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(-50, 200, 300), new Point3D(-50, 220, 300));

	 Triangle triangleSideCube3_2 = new Triangle(mat, Color.BLACK, new Point3D(50, 200, 300),
	 new Point3D(-50, 200, 300), new Point3D(-50, 220, 300));

	 Triangle triangleSideCube4_2 = new Triangle(mat, Color.BLACK, new Point3D(50, 200, 300),
	 new Point3D(50, 220, 300), new Point3D(-50, 220, 300));

	 Sphere sphere = new Sphere(new Material(0, 0, 20, 0, 0), new Color(0, 0, 150), 50, new Point3D(0, 150, 300));
	 Sphere sphere1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(150, 0, 0), 50, new Point3D(150, 150, 500));
	 Sphere sphere2 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 150, 0), 50, new Point3D(-150, 200, 60));
	// Sphere sphere3 = new Sphere(new Material(0.5, 0.5, 20, 0.5, 0.5), new Color(Color.BLACK), 40,
	// new Point3D(-150, 210, 500));

	 scene.addGeometry(triangleSideCube1_2, triangleSideCube2_2, triangleSideCube3_2, triangleSideCube4_2,
	 triangleGroundCube2_2, triangleGroundCube1_2, triangleSideCube4, triangleSideCube3, triangleSideCube2,
	 triangleSideCube1, triangleGroundCube2, triangleGroundCube1,  triangleSeaGroundR,
	 triangleSeaGroundL, triangleSeaGround1, triangleSeaOpposite2R, triangleSeaOpposite1L,
	 triangleSeaOpposite, sphere2, sphere, sphere1);

	 scene.addLightSource(new PointLight(new Color(80, 70, 30), new Point3D(-400, -140, 600), 0.0001, 0.000000001));
	// scene.addLightSource(new PointLight(new Color(80, 70, 30), new Point3D(0, 150, -400),0.0001, 0.000000001));

	 ImageWriter imageWriter = new ImageWriter("   �����", 500, 500, 500, 500);

	 Render render = new Render(imageWriter, scene);

	 render.writeToImage();
	 }

//-----------------------------------------------------------------------------------
	 /**
	  * 4 spheres in room
	  */
	 @Test
	 public void room2() {
	 Scene scene = new Scene("scene ");
	 scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1), 350, 5),
	 1000);
	 scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 3));
	 scene.setBackground(new Color(15, 15, 15));
	 Geometries geometries = new Geometries();
	 scene.setModel3D(geometries);
	 Material mat = new Material(0.4, 0.4, 20, 0.1, 0.1);

	 Triangle triangleSeaOpposite = new Triangle(mat, new Color(0, 250, 154), new Point3D(-300, 250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(0, -250, 1100));

	 Triangle triangleSeaOpposite1L = new Triangle(mat, new Color(0, 250, 154), new Point3D(-300, 250, 1100),
	 new Point3D(-300, -250, 1100), new Point3D(0, -250, 1100));

	 Triangle triangleSeaOpposite2R = new Triangle(mat, new Color(0, 250, 154), new Point3D(300, -250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(0, -250, 1100));

	 Triangle triangleSeaSideRight1D = new Triangle(mat, new Color(139, 0, 0), new Point3D(300, -250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(350, 250, 0));

	 Triangle triangleSeaSideRight2U = new Triangle(mat, new Color(139, 0, 0), new Point3D(300, -250, 1100),
	 new Point3D(350, -250, 0), new Point3D(350, 250, 0));

	 Triangle triangleSeaSideLeft1D = new Triangle(mat, new Color(255, 255, 0), new Point3D(-300, 250, 1100),
	 new Point3D(-300, -250, 1100), new Point3D(-350, 250, 0));

	 Triangle triangleSeaSideLeft1U = new Triangle(mat, new Color(255, 255, 0), new Point3D(-350, -250, 0),
	 new Point3D(-300, -250, 1100), new Point3D(-350, 250, 0));

	 Triangle triangleSeaGround1 = new Triangle(mat, new Color(152, 251, 152), new Point3D(-300, 250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundL = new Triangle(mat, new Color(152, 251, 152), new Point3D(-300, 250, 1100),
	 new Point3D(-350, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundR = new Triangle(mat, new Color(152, 251, 152), new Point3D(300, 250, 1100),
	 new Point3D(350, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaCeiling1 = new Triangle(mat, new Color(250, 206, 135), new Point3D(-300, -250, 1100),
	 new Point3D(300, -250, 1100), new Point3D(0, -250, 0));

	 Triangle triangleSeaCeilingL = new Triangle(mat, new Color(250, 206, 135), new Point3D(-300, -250, 1100),
	 new Point3D(-350, -250, 0), new Point3D(0, -250, 0));

	 Triangle triangleSeaCeilingR = new Triangle(mat, new Color(250, 206, 135), new Point3D(300, -250, 1100),
	 new Point3D(350, -250, 0), new Point3D(0, -250, 0));

	 Sphere sphere1 = new Sphere(new Material(0.1, 0.1, 20, 0.2, 0.2), new Color(58, 201, 212), 50,
	 new Point3D(-100, 150, 400));

	 Sphere sphere2 = new Sphere(new Material(0.1, 0.1, 20, 0.1, 0.2), new Color(20, 57, 212), 50,
	 new Point3D(100, 150, 400));

	 Sphere sphere4 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(238, 130, 238), 30,
	 new Point3D(-40, -60, 50));

	 Sphere sphere5 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(192, 0, 192), 20,
	 new Point3D(10, 130, 100));

	 scene.addGeometry(sphere5, sphere4, sphere2, sphere1, triangleSeaCeilingR, triangleSeaCeilingL,
	 triangleSeaCeiling1, triangleSeaGroundR, triangleSeaGroundL, triangleSeaGround1, triangleSeaSideLeft1U,
	 triangleSeaSideLeft1D, triangleSeaSideRight2U, triangleSeaSideRight1D, triangleSeaOpposite1L,
	 triangleSeaOpposite, triangleSeaOpposite2R);
	 scene.addLightSource(
	 new SpotLight(new Color(128, 0, 128), new Point3D(0, 0, -100), 0.5, 0.5, new Vector(0, 0, 1)));
	 scene.addLightSource(
	 new SpotLight(new Color(0, 255, 0), new Point3D(-100, -50, -100), 0.5, 0.5, new Vector(100, 50, 279)));

	 // scene.addLightSource(new pointLight(new Color(40, 40, 40), new Point3D(0,
	 // -95, -10), 0.0001, 0.000000001));

	 ImageWriter imageWriter = new ImageWriter(" ��� 2", 500, 500, 500, 500);

	 Render render = new Render(imageWriter, scene);
	 render.renderImage();
	 render.writeToImage();
	 }
	 
//---------------------------------------------------------------------
	 /**
	  * 4 spheres in a room
	  */
	 @Test
	 public void room3() {
	 Scene scene = new Scene("scene ");
	 scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1), 100, 10),
	 1000);
	 scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 3));
	 scene.setBackground(new Color(15, 15, 15));
	 Geometries geometries = new Geometries();
	 scene.setModel3D(geometries);
	 Material mat = new Material(0.4, 0.4, 20, 0.1, 0.1);
	 Color colorWall = new Color(180, 180, 180);
	 Color colorGround = new Color(105, 105, 105);

	 Triangle triangleSeaOpposite = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite1L = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(-550, -550, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite2R = new Triangle(mat, colorWall, new Point3D(550, -550, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaGround1 = new Triangle(mat, colorGround, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundL = new Triangle(mat, colorGround, new Point3D(-550, 250, 1100),
	 new Point3D(-250, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundR = new Triangle(mat, colorGround, new Point3D(550, 250, 1100),
	 new Point3D(250, 250, 0), new Point3D(0, 250, 0));
	 //---------------
	 Sphere sphere1 = new Sphere(new Material(0.3, 0.3, 20, 0.1, 0), new Color(58, 201, 212), 50,
	 new Point3D(-100, 50, 150));

	 Sphere sphere2 = new Sphere(new Material(0.2, 0.3, 20, 0.1, 0), new Color(20, 57, 212), 50,
	 new Point3D(100, 50, 500));



	 Sphere sphere4 = new Sphere(new Material(0, 0, 20, 1, 0), Color.BLACK, 40,
	 new Point3D(-40, -60, 50));

	 Sphere sphere5 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(192, 0, 192), 20,
	 new Point3D(40, 0, 300));

	 scene.addGeometry(sphere5, sphere4, sphere2, sphere1, triangleSeaGroundR, triangleSeaGroundL,
	 triangleSeaGround1, triangleSeaOpposite1L, triangleSeaOpposite, triangleSeaOpposite2R);
	 scene.addLightSource(
	 new SpotLight(new Color(128, 0, 128), new Point3D(0, 0, -100), 0.5, 0.5, new Vector(0, 0, 1)));
	 scene.addLightSource(
	 new SpotLight(new Color(0, 255, 0), new Point3D(-100, -50, -100), 0.5, 0.5, new Vector(100, 50, 279)));

	 // scene.addLightSource(new pointLight(new Color(40, 40, 40), new Point3D(0,
	 // -95, -10), 0.0001, 0.000000001));

	 ImageWriter imageWriter = new ImageWriter(" ��� 3", 500, 500, 500, 500);

	 Render render = new Render(imageWriter, scene);
	 render.renderImage();
	 render.writeToImage();
	 }

//-----------------------------------------------------------------
	 /**
	  * the aquarium
	  */
	 @Test
	 public void aquaryum() {
	 Scene scene = new Scene("scene ");
	 scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1), 100, 5),
	 1000);
	 scene.setAmbientLight(new AmbientLight(new Color(0, 0, 0), 3));
	 scene.setBackground(new Color(0, 191, 0));
	 Geometries geometries = new Geometries();
	 scene.setModel3D(geometries);
	 Material mat = new Material(0.3, 0.3, 20, 0.2, 0.2);

	 Color side = new Color(0, 191, 255);

	 Triangle triangleSeaOpposite = new Triangle(mat, side, new Point3D(-300, 250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(0, -250, 1100));

	 Triangle triangleSeaOpposite1L = new Triangle(mat, side, new Point3D(-300, 250, 1100),
	 new Point3D(-300, -250, 1100), new Point3D(0, -250, 1100));

	 Triangle triangleSeaOpposite2R = new Triangle(mat, side, new Point3D(300, -250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(0, -250, 1100));

	 Triangle triangleSeaSideRight1D = new Triangle(mat, side, new Point3D(300, -250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(350, 250, 0));

	 Triangle triangleSeaSideRight2U = new Triangle(mat, side, new Point3D(300, -250, 1100),
	 new Point3D(350, -250, 0), new Point3D(350, 250, 0));

	 Triangle triangleSeaSideLeft1D = new Triangle(mat, side, new Point3D(-300, 250, 1100),
	 new Point3D(-300, -250, 1100), new Point3D(-350, 250, 0));

	 Triangle triangleSeaSideLeft1U = new Triangle(mat, side, new Point3D(-350, -250, 0),
	 new Point3D(-300, -250, 1100), new Point3D(-350, 250, 0));

	 Triangle triangleSeaGround1 = new Triangle(mat, new Color(152, 251, 152), new Point3D(-300, 250, 1100),
	 new Point3D(300, 250, 1100), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundL = new Triangle(mat, new Color(152, 251, 152), new Point3D(-300, 250, 1100),
	 new Point3D(-350, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundR = new Triangle(mat, new Color(152, 251, 152), new Point3D(300, 250, 1100),
	 new Point3D(350, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaCeiling1 = new Triangle(mat, new Color(250, 206, 135), new Point3D(-300, -250, 1100),
	 new Point3D(300, -250, 1100), new Point3D(0, -250, 0));

	 Triangle triangleSeaCeilingL = new Triangle(mat, new Color(250, 206, 135), new Point3D(-300, -250, 1100),
	 new Point3D(-350, -250, 0), new Point3D(0, -250, 0));

	 Triangle triangleSeaCeilingR = new Triangle(mat, new Color(250, 206, 135), new Point3D(300, -250, 1100),
	 new Point3D(350, -250, 0), new Point3D(0, -250, 0));
	 // -----------------------
	 Sphere sphere = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0), 20,
	 new Point3D(60, 33, 200));

	 Triangle triangleBody = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0),
	 new Point3D(60, 52, 179), new Point3D(120, 37, 179), new Point3D(60, 12, 179));

	 Triangle triangleTail = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 215, 0),
	 new Point3D(120, 35, 179), new Point3D(130, 50, 179), new Point3D(130, 10, 179));

	 Triangle triangleMouth = new Triangle(mat, new Color(0, 191, 255), new Point3D(60, 33, 179),
	 new Point3D(35, 40, 179), new Point3D(35, 26, 179));

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

	 Triangle triangleMouth1 = new Triangle(mat, new Color(0, 191, 255), new Point3D(-60, 13, 179),
	 new Point3D(-85, 20, 179), new Point3D(-85, 6, 179));

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
	 new Point3D(-50, 40, 100), new Point3D(-150, 40, 100), new Point3D(-100, 70, 80));

	 Triangle star2 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	 new Point3D(-90, 40, 100), new Point3D(-116, 40, 100), new Point3D(-100, 10, 100));

	 Triangle star3 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	 new Point3D(-100, 70, 80), new Point3D(-138, 90, 80), new Point3D(-110, 40, 80));

	 Triangle star4 = new Triangle(new Material(0.5, 0.5, 20, 0, 0), new Color(255, 127, 0),
	 new Point3D(-100, 70, 80), new Point3D(-70, 90, 80), new Point3D(-80, 40, 80));

	 Sphere sp1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 255, 0), 4, new Point3D(-90, 60, 78));
	 Sphere sp2 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 255, 0), 4, new Point3D(-100, 40, 78));
	 Sphere sp3 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 255, 0), 4, new Point3D(-110, 60, 78));

	 scene.addGeometry(sp3, sp2, sp1, star4, star3, star2, star1, sphere1, sphereBlu1_3, sphereBlu1_2, sphereBlu1_1,
	 sphereEye1, triangleMouth1, triangleTail1, triangleBody1, sphere, triangleBody, triangleTail,
	 triangleMouth, sphereEye, sphereBlu1, sphereBlu2, sphereBlu3, triangleSeaCeilingR, triangleSeaCeilingL,
	 triangleSeaCeiling1, triangleSeaGroundR, triangleSeaGroundL, triangleSeaGround1, triangleSeaSideLeft1U,
	 triangleSeaSideLeft1D, triangleSeaSideRight2U, triangleSeaSideRight1D, triangleSeaOpposite1L,
	 triangleSeaOpposite, triangleSeaOpposite2R);

	 scene.addLightSource(new PointLight(new Color(5, 10, 20), new Point3D(0, -1100, 250), 0.0001, 0.000000001));

	 ImageWriter imageWriter = new ImageWriter("�������� 1", 500, 500, 500, 500);

	 Render render = new Render(imageWriter, scene);
	 render.renderImage();
	 render.writeToImage();
	 }
//--------------------------------------------------------------------------------
	 /**
	  * the 3 spheres but now the focus is on the red ball
	  */
	 @Test
	 public void testSphres2() {
	 Scene scene = new Scene("scene with spheres");
	 scene.setCameraAndDistance(new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1), 800, 5),
	 1000);
	 scene.setAmbientLight(new AmbientLight(Color.BLACK, 3));
	 scene.setBackground(new Color(15, 15, 15));
	 Geometries geometries = new Geometries();
	 scene.setModel3D(geometries);
	 Material mat = new Material(0.3, 0.3, 20, 0.3, 0.1);
	 Color colorWall = new Color(180, 180, 180);
	 Color colorGround = new Color(105, 105, 105);
	 Color colorCube = new Color(10, 10, 10);

	 Triangle triangleSeaOpposite = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite1L = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(-550, -550, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite2R = new Triangle(mat, colorWall, new Point3D(550, -550, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaGround1 = new Triangle(mat, colorGround, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundL = new Triangle(mat, colorGround, new Point3D(-550, 250, 1100),
	 new Point3D(-250, 250, 0), new Point3D(0, 250, 0));

	 Triangle triangleSeaGroundR = new Triangle(mat, colorGround, new Point3D(550, 250, 1100),
	 new Point3D(250, 250, 0), new Point3D(0, 250, 0));
	 //---------------
	 Triangle triangleGroundCube1 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(100, 200, 500), new Point3D(200, 200, 500));

	 Triangle triangleGroundCube2 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(200, 200, 600), new Point3D(200, 200, 500));

	 Triangle triangleSideCube1 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(100, 220, 600), new Point3D(100, 220, 500));

	 Triangle triangleSideCube2 = new Triangle(mat, colorCube, new Point3D(100, 200, 600),
	 new Point3D(100, 200, 500), new Point3D(100, 220, 500));

	 Triangle triangleSideCube3 = new Triangle(mat, colorCube, new Point3D(200, 200, 500),
	 new Point3D(100, 200, 500), new Point3D(100, 220, 500));

	 Triangle triangleSideCube4 = new Triangle(mat, colorCube, new Point3D(200, 200, 500),
	 new Point3D(200, 220, 500), new Point3D(100, 220, 500));
	 // -------
	 Triangle triangleGroundCube1_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(-50, 200, 300), new Point3D(50, 200, 300));

	 Triangle triangleGroundCube2_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(50, 200, 400), new Point3D(50, 200, 300));

	 Triangle triangleSideCube1_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(-50, 220, 400), new Point3D(-50, 220, 300));

	 Triangle triangleSideCube2_2 = new Triangle(mat, Color.BLACK, new Point3D(-50, 200, 400),
	 new Point3D(-50, 200, 300), new Point3D(-50, 220, 300));

	 Triangle triangleSideCube3_2 = new Triangle(mat, Color.BLACK, new Point3D(50, 200, 300),
	 new Point3D(-50, 200, 300), new Point3D(-50, 220, 300));

	 Triangle triangleSideCube4_2 = new Triangle(mat, Color.BLACK, new Point3D(50, 200, 300),
	 new Point3D(50, 220, 300), new Point3D(-50, 220, 300));

	 Sphere sphere = new Sphere(new Material(0, 0, 20, 0, 0), new Color(0, 0, 150), 50, new Point3D(0, 150, 300));
	 Sphere sphere1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(150, 0, 0), 50, new Point3D(150, 150, 850));
	 Sphere sphere2 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 150, 0), 50, new Point3D(-150, 200, 60));
	// Sphere sphere3 = new Sphere(new Material(0.5, 0.5, 20, 0.5, 0.5), new Color(Color.BLACK), 40,
	// new Point3D(-150, 210, 500));

	 scene.addGeometry(triangleSideCube1_2, triangleSideCube2_2, triangleSideCube3_2, triangleSideCube4_2,
	 triangleGroundCube2_2, triangleGroundCube1_2, triangleSideCube4, triangleSideCube3, triangleSideCube2,
	 triangleSideCube1, triangleGroundCube2, triangleGroundCube1,  triangleSeaGroundR,
	 triangleSeaGroundL, triangleSeaGround1, triangleSeaOpposite2R, triangleSeaOpposite1L,
	 triangleSeaOpposite, sphere2, sphere, sphere1);

	 scene.addLightSource(new PointLight(new Color(80, 70, 30), new Point3D(-400, -140, 600), 0.0001, 0.000000001));

	 ImageWriter imageWriter = new ImageWriter("������� ������ ������ �� �����", 500, 500, 500, 500);

	 Render render = new Render(imageWriter, scene);
	 render.renderImage();
	 render.writeToImage();
	 }
	 //------------------------------------------------------------------------
	 /**
	 * Test method for {@link renderer.Render#renderImage()}.
	 */
	 @Test
	 public void testRenderImage1() {
	 Scene scene = new Scene("scene with spheres");
	 scene.setCameraAndDistance(
	 new Camera(new Point3D(0, 0, -1000), new Vector(0, -1, 0), new Vector(0, 0, 1), 250,0), 1000);
	 scene.setAmbientLight(new AmbientLight(Color.BLACK, 3));
	 scene.setBackground(new Color(15, 15, 15));
	 Geometries geometries = new Geometries();
	 scene.setModel3D(geometries);
	 Material mat = new Material(0.3, 0.3, 20, 0.3, 0.1);
	 Color colorWall = new Color(180, 180, 180);
	 Color colorGround = new Color(105, 105, 105);
	 Color colorCube = new Color(Color.BLACK);
	 scene.setAddaptive(false);
	 Triangle triangleSeaOpposite = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite1L = new Triangle(mat, colorWall, new Point3D(-550, 250, 1100),
	 new Point3D(-550, -550, 1100), new Point3D(0, -550, 1100));

	 Triangle triangleSeaOpposite2R = new Triangle(mat, colorWall, new Point3D(550, -550, 1100),
	 new Point3D(550, 250, 1100), new Point3D(0, -550, 1100));
	 Sphere sphere = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 0, 150), 50,
	 new Point3D(0, 150, 300));
	 Sphere sphere1 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(150, 0, 0), 50,
	 new Point3D(150, 150, 500));
	 Sphere sphere2 = new Sphere(new Material(0.5, 0.5, 20, 0, 0), new Color(0, 150, 0), 50,
	 new Point3D(-150, 150, 60));
	 scene.addGeometry(triangleSeaOpposite1L, triangleSeaOpposite2R, triangleSeaOpposite, sphere, sphere1, sphere2);
	 ImageWriter imageWriter = new ImageWriter("   ����� ��� ���� ������", 500, 500, 500, 500);

	 Render render = new Render(imageWriter, scene,true);
	 render.renderImage();
	 render.writeToImage();

	 }
}




