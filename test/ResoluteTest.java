import static org.junit.Assert.assertEquals;

import image.Checkerboard;
import image.AbstractImage;
import image.IPixel;
import image.Pixel;
import org.junit.Test;
import transformations.ITransformationCommander;
import transformations.Resolute;


/**
 * Tests the operations of the Resolute class.
 */
public class ResoluteTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    ITransformationCommander resolute = new Resolute();
    double[][] blur = {new double[]{.0625, .125, .0625},
        new double[]{.125, .25, .125},
        new double[]{.0625, .125, .0625}};
    resolute.execute(null, blur);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullKernel() {
    ITransformationCommander resolute = new Resolute();
    AbstractImage image = new Checkerboard();
    resolute.execute(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullParameters() {
    ITransformationCommander resolute = new Resolute();
    resolute.execute(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonSquareKernel() {
    ITransformationCommander resolute = new Resolute();
    Checkerboard image = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    image.execute(10, 16, colors);
    double[][] blur = {new double[]{.0625, .125, .0625},
        new double[]{.125, .25, .125}};
    resolute.execute(image, blur);
  }

  @Test
  public void testBlurImage() {
    ITransformationCommander resolute = new Resolute();
    Checkerboard image = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    image.execute(10, 16, colors);
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(39,39)).toString());
    assertEquals("67 134 232 ", new Pixel(image.getRGBat(1,38)).toString());
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(15,17)).toString());
    double[][] blur = {new double[]{.0625, .125, .0625},
        new double[]{.125, .25, .125},
        new double[]{.0625, .125, .0625}};
    resolute.execute(image, blur);

    assertEquals("18 67 23 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("18 67 23 ", new Pixel(image.getRGBat(39,39)).toString());
    assertEquals("64 129 230 ", new Pixel(image.getRGBat(1,38)).toString());
    assertEquals("32 118 39 ", new Pixel(image.getRGBat(15,17)).toString());
  }

  @Test
  public void testSharpenImage() {
    ITransformationCommander resolute = new Resolute();
    Checkerboard image = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    image.execute(10, 16, colors);
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(39,39)).toString());
    assertEquals("67 134 232 ", new Pixel(image.getRGBat(1,36)).toString());
    assertEquals("34 123 44 ", new Pixel(image.getRGBat(35,17)).toString());
    double[][] sharp = {new double[]{-.125, -.125, -.125, -.125, -.125},
        new double[]{-.125, .25, .25, .25, -.125},
        new double[]{-.125, .25, 1, .25, -.125},
        new double[]{-.125, .25, .25, .25, -.125},
        new double[]{-.125, -.125, -.125, -.125, -.125}};
    resolute.execute(image, sharp);

    assertEquals("33 133 47 ", new Pixel(image.getRGBat(0,0)).toString());
    assertEquals("39 139 52 ", new Pixel(image.getRGBat(39,39)).toString());
    assertEquals("97 212 255 ", new Pixel(image.getRGBat(1,36)).toString());
    assertEquals("28 117 44 ", new Pixel(image.getRGBat(35,17)).toString());
  }

}