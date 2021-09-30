import static org.junit.Assert.assertEquals;

import image.AbstractImage;
import image.Checkerboard;
import image.IPixel;
import image.Pixel;
import org.junit.Test;
import transformations.ColorTransform;
import transformations.ITransformationCommander;


/**
 * Tests the operations of the ColorTransform class.
 */
public class ColorTransformTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    ITransformationCommander colorTrans = new ColorTransform();
    double[][] sepia = {new double[]{.393, .769, .189},
        new double[]{.349, .686, .168},
        new double[]{.272, .534, .131}};
    colorTrans.execute(null, sepia);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullKernel() {
    ITransformationCommander colorTrans = new ColorTransform();
    AbstractImage image = new Checkerboard();
    colorTrans.execute(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullParameters() {
    ITransformationCommander colorTrans = new ColorTransform();
    colorTrans.execute(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidKernel() {
    ITransformationCommander colorTrans = new ColorTransform();
    Checkerboard image = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    image.execute(10, 16, colors);
    double[][] sepia = {new double[]{.0625, .125, .0625},
        new double[]{.125, .25, .125}};
    colorTrans.execute(image, sepia);
  }

  @Test
  public void testSepiaTransformation() {
    ITransformationCommander colorTrans = new ColorTransform();
    Checkerboard image = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    image.execute(10, 16, colors);
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("67 134 232 ", new Pixel(image.getRGBat(23,37)).toString());
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(12,32)).toString());
    double[][] sepia = {new double[]{.393, .769, .189},
        new double[]{.349, .686, .168},
        new double[]{.272,.534,.131}};
    colorTrans.execute(image, sepia);

    assertEquals("115 102 79 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("172 152 119 ", new Pixel(image.getRGBat(23,37)).toString());
    assertEquals("115 102 79 ", new Pixel(image.getRGBat(12,32)).toString());
  }

  @Test
  public void testMonochromeTransformation() {
    ITransformationCommander colorTrans = new ColorTransform();
    Checkerboard image = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    image.execute(10, 16, colors);
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("67 134 232 ", new Pixel(image.getRGBat(23,37)).toString());
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(12,32)).toString());
    double[][] monochrome = {new double[]{.2126, .7152, .0722},
        new double[]{.2126, .7152, .0722},
        new double[]{.2126, .7152, .0722}};
    colorTrans.execute(image, monochrome);

    assertEquals("97 97 97 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("125 125 125 ", new Pixel(image.getRGBat(23,37)).toString());
    assertEquals("97 97 97 ", new Pixel(image.getRGBat(12,32)).toString());
  }
}