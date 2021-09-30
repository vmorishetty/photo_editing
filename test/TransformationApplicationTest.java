import static org.junit.Assert.assertEquals;

import image.Checkerboard;
import image.IPixel;
import image.Pixel;
import org.junit.Test;
import transformations.ColorTransform;
import transformations.Resolute;
import transformations.TransformationApplication;


/**
 * Tests the operations of the TransformationApplication class.
 */
public class TransformationApplicationTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullCommand() {
    TransformationApplication sepia = new TransformationApplication(null);
  }

  @Test
  public void testMonochromeCommand() {
    TransformationApplication gray = new TransformationApplication(new ColorTransform());
    double[][] monochrome = {new double[]{.2126, .7152, .0722},
        new double[]{.2126, .7152, .0722},
        new double[]{.2126, .7152, .0722}};
    Checkerboard check = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    check.execute(10, 16, colors);
    gray.press(check, monochrome);
    assertEquals("97 97 97 ", new Pixel(check.getRGBat(0,0)).toString());
    assertEquals("125 125 125 ", new Pixel(check.getRGBat(23,37)).toString());
    assertEquals("97 97 97 ", new Pixel(check.getRGBat(12,32)).toString());
  }

  @Test
  public void testSharpenCommand() {
    TransformationApplication sharpen = new TransformationApplication(new Resolute());
    double[][] sharp = {new double[]{-.125, -.125, -.125, -.125, -.125},
        new double[]{-.125, .25, .25, .25, -.125},
        new double[]{-.125, .25, 1, .25, -.125},
        new double[]{-.125, .25, .25, .25, -.125},
        new double[]{-.125, -.125, -.125, -.125, -.125}};
    Checkerboard check = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    check.execute(10, 16, colors);
    sharpen.press(check, sharp);
    assertEquals("33 133 47 ", new Pixel(check.getRGBat(0,0)).toString());
    assertEquals("39 139 52 ", new Pixel(check.getRGBat(39,39)).toString());
    assertEquals("97 212 255 ", new Pixel(check.getRGBat(1,36)).toString());
    assertEquals("28 117 44 ", new Pixel(check.getRGBat(35,17)).toString());
  }
}