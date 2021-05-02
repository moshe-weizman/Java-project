/**
 * 
 */
package scene;

import java.util.ArrayList;
import java.util.List;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.*;

/**
 * class to represent a scene
 *
 */
public class Scene {
	String _sceneName;
	Color _background;
	AmbientLight _ambientLight;
	Geometries model3D;
	Camera _camera;
	double _screenDistance;
	List<LightSource> _lights;
	boolean adaptive;
	
	public final static int NUM_OF_DOF_RAYS=15;

//------------------------------------------------------------------	
	/**
	 * ctor; gets only string for scene's name
	 * 
	 * @param _sceneName
	 */
	public Scene(String _sceneName) {
		this._sceneName = _sceneName;
		this.model3D = new Geometries();
		this._lights = new ArrayList<LightSource>();
		this.adaptive = false;
	}

	// ------------------------- Getters ---------------------------------------

	/**
	 * @return the _sceneName
	 */
	public String getSceneName() {
		return _sceneName;
	}

	/**
	 * @return the _background
	 */
	public Color getBackground() {
		return _background;
	}

	/**
	 * @return the _ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}

	/**
	 * @return the _geometries
	 */
	public Geometries getModel3D() {
		return model3D;
	}

	/**
	 * @return the _camera
	 */
	public Camera getCamera() {
		return _camera;
	}

	/**
	 * @return the _screenDistance
	 */
	public double getScreenDistance() {
		return _screenDistance;
	}

	/**
	 * @return the _lights
	 */
	public List<LightSource> getLights() {
		return _lights;
	}

	// ---------------------------------- Setters --------------------------

	/**
	 * @return the addaptive
	 */
	public boolean isAddaptive() {
		return adaptive;
	}

	/**
	 * @param addaptive the addaptive to set
	 */
	public void setAddaptive(boolean addaptive) {
		this.adaptive = addaptive;
	}

	/**
	 * @param _background the _background to set
	 */
	public void setBackground(Color background) {
		this._background = background;
	}

	/**
	 * @param _ambientLight the _ambientLight to set
	 */
	public void setAmbientLight(AmbientLight ambientLight) {
		this._ambientLight = ambientLight;
	}

	
	/**
	 * @param _camera         the _camera to set
	 * @param _screenDistance the _screenDistance to set
	 */
	public void setCameraAndDistance(Camera camera, double screenDistance) {
		this._camera = camera;
		this._screenDistance = screenDistance;
	}

	/**
	 * @param _geometries the _geometries to set
	 */
	public void setModel3D(Geometries model3D) {
		this.model3D = model3D;
	}

	// -----------------------------------------------------------------------
	/**
	 * func to add a geometry to list _geometry
	 */
	public void addGeometry(Intersectable... shapes) {
		for (Intersectable shape : shapes)
			this.model3D.add(shape);
	}

	/**
	 * func to add a light source to list _lights
	 */
	public void addLightSource(LightSource... lights) {
		for (LightSource light : lights)
			this._lights.add(light);
	}
}
