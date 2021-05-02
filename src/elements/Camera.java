package elements;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import primitives.*;
import scene.Scene;
import primitives.*;
//import geometries.*;

/***
 * 
 * class to represent a camera
 *
 */
public class Camera {
	Point3D p0;
	Vector vUp;
	Vector vTo;
	Vector vRight;
	double focalDistanceFromView;
	double aperturSize;
	final public static int NUM_OF_POINTS = 9;

///******************** Constructor **********************
	/**
	 * ctor for camera
	 * 
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		this.p0 = p0;
		if (Util.isZero(vTo.dotProduct(vUp))) {
			this.vUp = vUp.normalize();
			this.vTo = vTo.normalize();
			this.vRight = vTo.crossProduct(vUp).normalize();
		}
	}

// -----------------------
	/**
	 * ctor for camera
	 * 
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo, double focalDistanceFromView, double aperturSize) {
		this.p0 = p0;
		if (Util.isZero(vTo.dotProduct(vUp))) {
			this.vUp = vUp.normalize();
			this.vTo = vTo.normalize();
			this.vRight = vTo.crossProduct(vUp).normalize();
		}
		this.focalDistanceFromView = focalDistanceFromView;
		this.aperturSize = aperturSize;
	}

///////////////// Getters //////////////////////
	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the focalDistanceFromView
	 */
	public double getFocalDistanceFromView() {
		return focalDistanceFromView;
	}

	/**
	 * @return the aperturSize
	 */
	public double getAperturSize() {
		return aperturSize;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

//////////////////////// Operations /////////////////////////////
	/***
	 * func to construct ray from camera to specific pixel
	 * 
	 * @param nX
	 * @param nY
	 * @param i
	 * @param j
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return list of colored-rays
	 */
	public List<ColoredRay> constructRayThroughPixel1(int nX, int nY, int i, int j, double screenDistance, double screenWidth,
			double screenHeight, boolean adaptive) {

		Point3D pToShot;
		List<ColoredRay> rays = new ArrayList<ColoredRay>();
		Util.alignZero(aperturSize);
		Util.alignZero(focalDistanceFromView);
		if (i < 0 && i >= nX && j < 0 && j >= nY || focalDistanceFromView <= 0 || aperturSize < 0)
			throw new IllegalArgumentException("illegal argument exception");

		Point3D pCenter = this.p0.add(this.vTo.scale(screenDistance));
		double rY = screenHeight / nY;
		double rX = screenWidth / nX;
		double yJ = (j - (nY / 2.0)) * rY;// + rY / 2.0;
		double xI = (i - (nX / 2.0)) * rX;// + rX / 2.0;
		Point3D pIJ = pCenter;
		if (xI != 0)
			pIJ = pIJ.add(this.vRight.scale(xI));
		if (yJ != 0)
			pIJ = pIJ.add(this.vUp.scale(-yJ));

		if (!adaptive) {
			pToShot = pIJ;
			double moveX = rX / (NUM_OF_POINTS - 1);
			double moveY = rY / (NUM_OF_POINTS - 1);
			for (int m = 0; m < NUM_OF_POINTS; ++m) {
				pToShot = pIJ;
				if (m != 0)
					pToShot = pToShot.add(this.vRight.scale(moveX * m));

				for (int n = 0; n < NUM_OF_POINTS; ++n) {
					ColoredRay centeralRay = new ColoredRay(new Ray(this.p0, pToShot.subtract(p0)));

					if (aperturSize != 0)
						rays = funcForDOF(pToShot, centeralRay);
					rays.add(centeralRay);
					pToShot = pToShot.add(this.vUp.scale(-moveY));
				}
			}
		}

		return rays;
	}

// -----------------------------------------------------------------------------
	/**
	 * func to calc 4 rays from camera to vertexes of pixel
	 * @param nX - number of columns of pixels 
	 * @param nY -  number of rays of pixels 
	 * @param i 
	 * @param j
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @param adaptive - boolean if we need perform adaptive supersampling
	 * @return list of rays
	 */
	public List<ColoredRay> constructRayThroughPixel(int nX, int nY, int i, int j, double screenDistance, double screenWidth,
			double screenHeight, boolean adaptive) {

		Point3D pToCalcDOF;
		List<ColoredRay> rays = new ArrayList<ColoredRay>();
		Util.alignZero(aperturSize);
		Util.alignZero(focalDistanceFromView);
		if (i < 0 && i >= nX && j < 0 && j >= nY || focalDistanceFromView <= 0 || aperturSize < 0)
			throw new IllegalArgumentException("illegal argument exception");

		Point3D pCenter = this.p0.add(this.vTo.scale(screenDistance)); //pCenter is the middle point of view-plane
		double rY = screenHeight / nY;
		double rX = screenWidth / nX;//
		double yJ = (j - (nY / 2.0)) * rY; 
		double xI = (i - (nX / 2.0)) * rX;
		Point3D pIJ = pCenter;  //pIJ is the lft top corner of pixel
		if (xI != 0)
			pIJ = pIJ.add(this.vRight.scale(xI));
		if (yJ != 0)
			pIJ = pIJ.add(this.vUp.scale(-yJ));

		if (adaptive) {   //construct 4 rays only, to 4 corners
			ColoredRay upLeft = new ColoredRay(new Ray(pIJ, pIJ.subtract(p0)));
			pIJ = pIJ.add(this.vUp.scale(rY));
			ColoredRay downLeft = new ColoredRay(new Ray(pIJ, pIJ.subtract(p0)));
			pIJ = pIJ.add(this.vRight.scale(rX));
			ColoredRay downRight = new ColoredRay(new Ray(pIJ, pIJ.subtract(p0)));
			pIJ = pIJ.add(this.vUp.scale(-rY));
			ColoredRay upRight = new ColoredRay(new Ray(pIJ, pIJ.subtract(p0)));
			rays.add(upLeft);
			rays.add(downLeft);
			rays.add(downRight);
			rays.add(upRight);

			return rays;
		}

		if (!adaptive) {  //now we construct 81 (9*9 -per config. definitions) rays (or more - if aperture >0 ) to list
			pToCalcDOF = pIJ;
			double moveX = rX / (NUM_OF_POINTS - 1);
			double moveY = rY / (NUM_OF_POINTS - 1);
			//run over the pixel and build the rays
			for (int m = 0; m < NUM_OF_POINTS; ++m) {  
				pToCalcDOF = pIJ;
				if (m != 0)
					pToCalcDOF = pToCalcDOF.add(this.vRight.scale(moveX * m));

				for (int n = 0; n < NUM_OF_POINTS; ++n) {
					ColoredRay centeralRay =new ColoredRay( new Ray(p0, pToCalcDOF.subtract(p0)));

					if (aperturSize != 0)
						rays.addAll(funcForDOF(pToCalcDOF, centeralRay));
					rays.add(centeralRay);
					pToCalcDOF = pToCalcDOF.add(this.vUp.scale(-moveY)); //go to next pToCalcDOF..
				}
			}
		}
		return rays;
	}

// --------------------------------------------------------------------------
	/**
	 * func to create 5 'middles' rays at the pixel
	 * @param pixel
	 * @return
	 */
	public List<ColoredRay> construct5RaysThroughPixel(Pixel pixel) {
		List<ColoredRay> rays = new ArrayList<ColoredRay>();
        //calcuate the middle point of every vertice of the pixel
		double distanceX = (pixel.getTopRight().getPoint().distance(pixel.getTopLeft().getPoint())) / 2; 
		double distanceY = (pixel.getDownLeft().getPoint().distance(pixel.getTopLeft().getPoint())) / 2;

		Point3D middleTop = pixel.getTopLeft().getPoint().add(this.vRight.scale(distanceX));
		rays.add (new ColoredRay(new Ray(middleTop, middleTop.subtract(p0))));
		Point3D middleRight = pixel.getTopRight().getPoint().add(this.vUp.scale(distanceY));
		rays.add(new ColoredRay(new Ray(middleRight, middleRight.subtract(p0))));
		Point3D middleDown = pixel.getDownLeft().getPoint().add(this.vRight.scale(distanceX));
		rays.add(new ColoredRay(new Ray(middleDown, middleDown.subtract(p0))));
		Point3D middleLeft = pixel.getDownLeft().getPoint().add(this.vUp.scale(-distanceY));
		rays.add(new ColoredRay(new Ray(middleLeft, middleLeft.subtract(p0))));
		Point3D middle = middleLeft.add(this.vRight.scale(distanceX));
		rays.add(new ColoredRay(new Ray(middle, middle.subtract(p0))));
		return rays;
	}

// ---------------------------------------------------------------------------
	/**
	 * this func will create list of DOF rays
	 * @param pToCalcDof - point the middle of the aperture
	 * @param centeralRay 
	 * @return list of DOF rays
	 */
	public List<ColoredRay> funcForDOF(Point3D pToCalcDof, ColoredRay centeralRay) {
		List<ColoredRay> rays = new ArrayList<ColoredRay>();
		Point3D newPoint;
		Point3D focalPoint = pToCalcDof.add(centeralRay.getRay().getNormalVec().scale(focalDistanceFromView)); //this will be the center of the foacl pixel
		for (int k = 0; k < Scene.NUM_OF_DOF_RAYS; ++k) {
			newPoint = pToCalcDof;
			double randX = Math.random() - 0.5;
			double randY = Math.random() - 0.5;
			double shiftX = randX * aperturSize; //randomal size to shift, in X's and Y's 
			double shiftY = randY * aperturSize;
            //now we shifting...
			if (!Util.isZero(shiftX))
				newPoint = newPoint.add(vRight.scale(shiftX));
			if (!Util.isZero(shiftY))
				newPoint = newPoint.add(vUp.scale(-shiftY));

			Vector vIJ = focalPoint.subtract(newPoint); //the new DOF ray!
			rays.add(new ColoredRay(new Ray(newPoint, vIJ))); // ctor of ray performs the normalization
		}
		return rays;
	}
}