package tests;

import org.junit.Test;

import elements.AmbientLight;
import primitives.Color;
import primitives.Util;
import renderer.ImageWriter;
/**
 * 
 * class to test print of table
 *
 */
public class ImageWriterTest {
/**
 * test for func ImageWriter.WriteToImage
 */
	@Test
	public void testWriteToimage() {
		ImageWriter image = new ImageWriter("image1", 10, 10, 500, 500);
		AmbientLight light1 = new AmbientLight(new Color(0, 127, 255), 10);
		AmbientLight light2 = new AmbientLight(new Color(0,0,0), 4);
		
		for (int i=0; i<500;++i)
			for (int j=0; j<500;++j) {
				if (Util.isZero(i%50) || Util.isZero(j%50) || i==499 || j==499)
					image.writePixel(i, j, light2.getIntensity().getColor());
				else
				image.writePixel(i, j, light1.getIntensity().getColor());
			}
		image.writeToImage();
	}

}

