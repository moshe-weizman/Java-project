/**
 * 
 */
package renderer;

import scene.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import elements.Camera;
import elements.ColoredRay;
import elements.LightSource;
import elements.Pixel;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * class to render a image
 *
 */
public class Render {
	Scene _scene;
	ImageWriter _imageWriter;
	boolean _multiThread = false;
	private static final double EPS = 0.1;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final int MAX_CALC_COLOR_LEVEL = 15;

	/**
	 * ctor
	 * 
	 * @param _scene
	 * @param _imageWriter
	 */
	public Render(ImageWriter imageWriter, Scene scene) {
		this._scene = scene;
		this._imageWriter = imageWriter;
	}

	/**
	 * ctor
	 * 
	 * @param _scene
	 * @param _imageWriter
	 */
	public Render(ImageWriter imageWriter, Scene scene, boolean multiThread) {
		this._scene = scene;
		this._imageWriter = imageWriter;
		this._multiThread = multiThread;
	}

//--------------------------------------------------------
	/**
	 * func to render an image
	 */
	public void renderImage1() {
		int nX = this._imageWriter.getNx();
		int nY = this._imageWriter.getNy();
		double width = this._imageWriter.getWidth();
		double height = this._imageWriter.getHeight();
		double screenDistance = this._scene.getScreenDistance();
		int numOfRays = Scene.NUM_OF_DOF_RAYS + 1;
		Color backGroundColor = this._scene.getBackground();
		GeoPoint closestPoint;
		boolean adaptive = this._scene.isAddaptive();
		for (int i = 0; i < nX; ++i)
			for (int j = 0; j < nY; ++j) {
				Color color = Color.BLACK;
				List<ColoredRay> rays = _scene.getCamera().constructRayThroughPixel(nX, nY, i, j, screenDistance, width,
						height, adaptive);

				for (ColoredRay ray : rays) {
					closestPoint = findClosestIntersection(ray.getRay());
					if (closestPoint != null)
						color = color.add(calcColor(closestPoint, ray.getRay()));
					else
						color = color.add(backGroundColor);
				}
				if (rays.size() > 1)
					color = color.reduce(numOfRays);
				_imageWriter.writePixel(i, j, color.getColor());
			}
	}

//-----------------------------------------------------------------	
	/**
	 * func to render an image
	 * 
	 * @throws InterruptedException
	 */
	public void renderImage() {
		// Pixel pixel = null;
		int nX = this._imageWriter.getNx();
		int nY = this._imageWriter.getNy();
		double width = this._imageWriter.getWidth();
		double height = this._imageWriter.getHeight();
		double screenDistance = this._scene.getScreenDistance();
		int numOfRays = Camera.NUM_OF_POINTS * Camera.NUM_OF_POINTS; // num to reduce the color with
		if (_scene.getCamera().getAperturSize() != 0)
			numOfRays *= (Scene.NUM_OF_DOF_RAYS + 1); //// +1 => the centeral ray
		Color backGroundColor = this._scene.getBackground();
		boolean adaptive = this._scene.isAddaptive();

//		ThreadPoolExecutor executor = null;
//		if (_multiThread) {
//			int cores = Runtime.getRuntime().availableProcessors();
//			executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores);

			for (int i = 0; i < nX; ++i)
				for (int j = 0; j < nY; ++j) {
					final int numOfRays1 = numOfRays;
					final int i1 = i;
					final int j1 = j;
//					Runnable worker = () -> {
						
						Color color = Color.BLACK;
						List<ColoredRay> rays = _scene.getCamera().constructRayThroughPixel(nX, nY, i1, j1, screenDistance,
								width, height, adaptive);
						// if adaptive- returned 4 rays (of the pixel-edges), if not - returned 81(*16)
						// rays. (and the DOF's)
						for (ColoredRay ray : rays) {
							color = color.add(funcToCalcColorOfRay(ray, backGroundColor)); // calcuates the ray's color

							if (adaptive) {
								if (_scene.getCamera().getAperturSize() > 0)  // so we need to shot DOF rays
									color = funcToAddDofToColor(color, ray, backGroundColor); // update the color after DOF																						
								ray.setColor(color); //updatethe color of this ray
								color = Color.BLACK;
							}
						}

						if (adaptive) { // now we build a pixel from 4 rays
							final Pixel pixel = new Pixel(rays.get(0), rays.get(1), rays.get(2),
									rays.get(3), 1);
							rays.clear();
							if (!pixel.equalsColors()) // if not all colors are equals
								color = supersampling(pixel, backGroundColor);
							else // if the pixel has one color only
								color = pixel.getDownLeft().getColor();
						}
						if (rays.size() > 1 && !adaptive) // now we have to reduce the color (resulted of many rays!!)
							color = color.reduce(numOfRays1);
						_imageWriter.writePixel(i1, j1, color.getColor());
//					};
//					if (_multiThread)
//						executor.execute(worker);
//					else {
//						worker.run();
//					}
//				}
//			if (_multiThread) {
//				executor.shutdown();
//				try {
//					executor.awaitTermination(550, TimeUnit.MINUTES);
//				} catch (Exception e) {
//				}
//			}
		}
	}

//--------------------------------------------------------------------------------
	/**
	 * func that gets a color of a ray; calc DOF rays and add their color to
	 * original color, finally reduce the color and return it.
	 * 
	 * @param color           - of the ray
	 * @param ray             - the centeral ray
	 * @param backGroundColor
	 * @return updated color;
	 */
	private Color funcToAddDofToColor(Color color, ColoredRay ray, Color backGroundColor) {
		List<ColoredRay> raysForDOF = new ArrayList<ColoredRay>();
		raysForDOF = _scene.getCamera().funcForDOF(ray.getPoint(), ray);
		for (ColoredRay rayDOF : raysForDOF)
			color = color.add(funcToCalcColorOfRay(rayDOF, backGroundColor));
		color = color.reduce(Scene.NUM_OF_DOF_RAYS + 1);  //+1 => the centeral ray
		return color;
	}

// -----------------------------------
	private final static int NUM_RAYES_IN_PIXEL = 4;
	
/**
 * func to perform the adaptive supersampling
 * @param pixel - to divide to  4 parts etc.
 * @param backGroundColor
 * @return color of the pixel
 */
	private Color supersampling(Pixel pixel, Color backGroundColor) {
		Color color = Color.BLACK;
		List<ColoredRay> rays = new ArrayList<ColoredRay>();
		//List<ColoredRay> raysColor = new ArrayList<ColoredRay>();
		List<Pixel> pixels = new ArrayList<Pixel>();     //for pixels which should be checked
		List<Pixel> pixelsEnd = new ArrayList<Pixel>(); //for fixed pixels
		pixels.add(pixel);
		do {
			rays = _scene.getCamera().construct5RaysThroughPixel(pixel);  //create 5 rays at the 'middles'
			for (ColoredRay ray : rays) {
				color = color.add(funcToCalcColorOfRay(ray, backGroundColor));  

				if (_scene.getCamera().getAperturSize() > 0)   //so we need to shot DOF rays
					color = funcToAddDofToColor(color, ray, backGroundColor); // update the color after DOF																						
				ray.setColor(color);  //now we have the final color of this ray
				color = Color.BLACK; 
			}
           
			ColoredRay middleTop = rays.get(0);
			ColoredRay middleRight = rays.get(1);
			ColoredRay middleDown = rays.get(2);
			ColoredRay middleLeft = rays.get(3);
			ColoredRay middle = rays.get(4);
            //now we create 4 new pixels, and insert them to the lists according to their color equalicy
			Pixel pixel1 = new Pixel(pixel.getTopLeft(), middleLeft, middle, middleTop, pixel.getWeight() / 4);
			Pixel pixel2 = new Pixel(middleTop, middle, middleRight, pixel.getTopRight(), pixel.getWeight() / 4);
			Pixel pixel3 = new Pixel(middleLeft, pixel.getDownLeft(), middleDown, middle, pixel.getWeight() / 4);
			Pixel pixel4 = new Pixel(middle, middleDown, pixel.getDownRight(), middleRight, pixel.getWeight() / 4);
			insertPixel(pixels, pixelsEnd, pixel1, pixel2, pixel3, pixel4);

			pixels.remove(0);  //since we already handle it
			if (!pixels.isEmpty())
				pixel = pixels.get(0);
			rays.clear();

		} while (!pixels.isEmpty());
		color = Color.BLACK;
		//now we calc the final color of the whole sub-pixels, we don't forget to reduce it by the weight of every sub-pixel
		for (Pixel p : pixelsEnd) {
			Color color1 = (p.getDownLeft().getColor());
			color1 = color1.add(p.getDownRight().getColor());
			color1 = color1.add(p.getTopLeft().getColor());
			color1 = color1.add(p.getTopRight().getColor());
			color = color.add(color1.reduce(NUM_RAYES_IN_PIXEL).scale(p.getWeight()));
		}
		return color;
	}

// ------------------------------------------
	/**
	 * func to calc color of a given ray
	 * 
	 * @param ray             - to calc its color
	 * @param backGroundColor of the scene
	 * @return color of the ray
	 */
	private Color funcToCalcColorOfRay(ColoredRay ray, Color backGroundColor) {
		Color color = Color.BLACK;
		GeoPoint closestPoint = findClosestIntersection(ray.getRay());
		if (closestPoint != null)
			color = color.add(calcColor(closestPoint, ray.getRay()));
		else
			color = color.add(backGroundColor);
		return color;
	}

// -------------------------------------------------------
	private static final double MIN_WEIGHT = 0.015625;
	/**
	 * func gets some pixels and sort them to lists
	 * @param pixels - list of un-solid pixel 
	 * @param pixelsEnd - list of 'complete' pixels, (with solid color)
	 * @param checkPixels - pixels to check
	 */
	private void insertPixel(List<Pixel> pixels, List<Pixel> pixelsEnd, Pixel... checkPixels) {
		for (Pixel p : checkPixels) {
			if (!p.equalsColors() && p.getWeight() > MIN_WEIGHT)
				pixels.add(p);
			else
				pixelsEnd.add(p);
		}
	}

// -------------------------------------------------------------
	/**
	 * func to calcuate a color of ray (calls to recursive func)
	 * @param closestPoint
	 * @param inRay
	 * @return calcuated color
	 */
	private Color calcColor(GeoPoint closestPoint, Ray inRay) {
		return calcColor(closestPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());

	}

//--------------------------------------------------------------------
	/**
	 * func to calc a color of single pixel
	 * 
	 * @param intersection tested point and its geometry
	 * @return color of a point
	 */
	private Color calcColor(GeoPoint intersection, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K)
			return Color.BLACK;
		Color reflectedLight = Color.BLACK;
		Color refractedLight = Color.BLACK;
		Color color = intersection.geometry.getEmmission();
		Vector v = inRay.getNormalVec();
		Vector n = intersection.geometry.getNormal(intersection.point);
		int nShininess = intersection.geometry.getMaterial().getnShininess();
		double kd = intersection.geometry.getMaterial().getkD();
		double ks = intersection.geometry.getMaterial().getkS();
		for (LightSource lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(intersection.point);
			if (n.dotProduct(l) * n.dotProduct(v) > 0) { // both are with the same sign
				double ktr = transparency(l, intersection, n);
				if (!Util.isZero(ktr * k)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		double kR = intersection.geometry.getMaterial().getkR();
		double temp = k * kR;
		if (temp > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, intersection.point, v);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null) {
				reflectedLight = calcColor(reflectedPoint, reflectedRay, level - 1, temp).scale(kR);
			}
		}
		double kT = intersection.geometry.getMaterial().getkT();
		temp = k * kT;
		if (temp > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n, intersection.point, v);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null) {
				refractedLight = calcColor(refractedPoint, refractedRay, level - 1, temp).scale(kT);
			}
		}
		color = color.add(reflectedLight, refractedLight);
		return color;
	}

// ---------------------------------------------------------------
	/**
	 * func to find the closest intersection of a ray with a geometry
	 * @param ray
	 * @return geopoint; the closest intersection
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intresectionList = this._scene.getModel3D().findIntersections(ray);
		if (intresectionList.isEmpty())
			return null;
		return getClosestPoint(ray.getPoint(), intresectionList);
	}

// ---------------------------------------------------------------
	/**
	 * func to  
	 * @param n
	 * @param point
	 * @param inRay
	 * @return the reflected ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Vector v) {
		Vector r = calcR(v, n);
		point = addEpsToPoint(n, point, r);
		return new Ray(point, r);
	}

//-------------------------------------------------------------------------
	/**
	 * func to calc the refracted ray
	 * @param point
	 * @param inRay
	 * return - refracted ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Vector v) {
		point = addEpsToPoint(n, point, v);
		return new Ray(point, v);
	}

//------------------------------------------------------------------------
	/**
	 * func to check if the point have shadows
	 * 
	 * @param l
	 * @param geopoint
	 * @return
	 */
	private double transparency(Vector l, GeoPoint geopoint, Vector n) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Point3D point = addEpsToPoint(n, geopoint.getPoint(), l);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = _scene.getModel3D().findIntersections(lightRay);
		double ktr = 1;
		for (GeoPoint gp : intersections)
			ktr *= gp.geometry.getMaterial().getkT();
		return ktr;
	}

//-------------------------------------------------------------------
	/**
	 * 
	 * @param       n-normal Vector to geometry
	 * @param point
	 * @param v
	 * @return
	 */
	private Point3D addEpsToPoint(Vector n, Point3D point, Vector v) {
		Vector eps = n.scale(n.dotProduct(v) > 0 ? EPS : -EPS);
		return point.add(eps);
	}

//------------------------------------------------------------------------
	/**
	 * func to calc the diffusive effect of a light source
	 * 
	 * @param kd
	 * @param l              - the light vector
	 * @param n              - normal to the shape
	 * @param lightIntensity
	 * @return color
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double temp = Util.alignZero(l.dotProduct(n));
		if (temp < 0)
			temp = -temp;
		return lightIntensity.scale(temp * kd);
	}

// -----------------------------------------------------------------
	/**
	 * func to calc the specular effect of a light source
	 * 
	 * @param ks             -
	 * @param l              - vector of light source
	 * @param n              - normal to shape
	 * @param v              - the vector of camera point view
	 * @param nShininess
	 * @param lightIntensity
	 * @return
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = calcR(l, n); // r= l - 2n*(l*n)
		double temp = Util.alignZero(v.scale(-1).dotProduct(r)); // max(0,-v*r)
		if (temp < 0)
			return Color.BLACK;
		return lightIntensity.scale(ks * Math.pow(temp, nShininess)); // il*ks*temp^nshinines
	}

// ----------------------------------------------------------
	/**
	 * func to calc the r vec.
	 * @param v
	 * @param n
	 * @return
	 */
	private Vector calcR(Vector v, Vector n) {
		return v.subtract(n.scale(v.dotProduct(n) * 2)).normalize();
	}

//-----------------------------------------------------------------
	/**
	 * func to find the closest intersection point (taken from a list)
	 * 
	 * @param intersectionPoints
	 * @return
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
// In the intersectionPoints - find the point with minimal distance from the ray
// begin point (now it is just the camera location) and return it
		return getClosestPoint(this._scene.getCamera().getP0(), intersectionPoints);

	}

// -------------------------------------------------------
	/**
	 * func to get closest point-of-intersection
	 * @param point
	 * @param intersectionPoints
	 * @return
	 */
	private GeoPoint getClosestPoint(Point3D beginPoint3d, List<GeoPoint> intersectionPoints) {
		GeoPoint pointToReturn = intersectionPoints.get(0);
		double distance = intersectionPoints.get(0).getPoint().distance2(beginPoint3d);

		for (GeoPoint point : intersectionPoints) {
			double tempD = point.getPoint().distance2(beginPoint3d);
			if (tempD < distance) {
				distance = tempD;
				pointToReturn = point;
			}
		}
		return pointToReturn;
	}

//----------------------------------------------------------
	/**
	 * func to call to image-writer
	 */
	public void writeToImage() {
		this._imageWriter.writeToImage();
	}

//-------------------------------------------------------
	/**
	 * func to paint a grid on the image
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		int nX = this._imageWriter.getNx();
		int nY = this._imageWriter.getNy();
		java.awt.Color color = new Color(255, 255, 255).getColor();

		for (int i = 0; i < nX; ++i)
			for (int j = 0; j < nY; ++j) {
				if (i % interval == 0 || j % interval == 0) {
					_imageWriter.writePixel(i, j, color);
				}
			}
	}
}
