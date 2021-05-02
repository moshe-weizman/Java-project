/**
 * 
 */
package primitives;

/**
 * class to represent a material
 *
 */
public class Material {
	double kD;
	double kS;
	int nShininess;
	double kR; // reflection (mirror=1 mat=0)
	double kT; // Transparency (block=0)

//----------------------------------------------------------------
	/**
	 * ctor for material
	 * 
	 * @param kD
	 * @param kS
	 * @param nShininess - shininess of the material
	 */
	public Material(double kD, double kS, int nShininess) {
		this.kD = kD;
		this.kS = kS;
		this.nShininess = nShininess;
		this.kR = 0;
		this.kT = 0;
	}

//------------------------------------------------------------------------------
	/**
	 * ctor for material
	 * 
	 * @param kD
	 * @param kS
	 * @param nShininess
	 * @param kR         -reflection
	 * @param kT         -transparency
	 */
	public Material(double kD, double kS, int nShininess, double kR, double kT) {
		super();
		this.kD = kD;
		this.kS = kS;
		this.nShininess = nShininess;
		this.kR = kR;
		this.kT = kT;
	}

//-------------------------------------------------------------------------------
	/**
	 * copy ctor
	 * 
	 * @param other
	 */
	public Material(Material other) {
		this.kD = other.kD;
		this.kS = other.kS;
		this.nShininess = other.nShininess;
		this.kR = other.kR;
		this.kT = other.kT;
	}

//------------------------------- Getters -----------------------------
	/**
	 * @return the kD
	 */
	public double getkD() {
		return this.kD;
	}

	/**
	 * @return the kS
	 */
	public double getkS() {
		return this.kS;
	}

	/**
	 * @return the nShininess
	 */
	public int getnShininess() {
		return this.nShininess;
	}

	/**
	 * @return the kR
	 */
	public double getkR() {
		return this.kR;
	}

	/**
	 * @return the kT
	 */
	public double getkT() {
		return this.kT;
	}

}
