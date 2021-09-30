import static org.junit.Assert.assertEquals;
import image.Pixel;
import image.IPixel;

import image.Checkerboard;
import org.junit.Test;

/**
 * Tests the Checkerboard creation operations for making the checkerboard programmatic image.
 */
public class CheckerboardTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullCheckerboardColors() {
    Checkerboard check = new Checkerboard();
    check.execute(4, 16, null);
  }

  @Test
  public void testCreateSquareCheckerBoard() {
    Checkerboard check = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    check.execute(30, 16, colors);
    assertEquals("34 123 44 ", new Pixel(check.getRGBat(0,0)).toString());
    assertEquals("34 123 44 ", new Pixel(check.getRGBat(0,17)).toString());
    assertEquals("34 123 44 ",  new Pixel(check.getRGBat(0,29)).toString());
    assertEquals("67 134 232 ", new Pixel(check.getRGBat(0,30)).toString());
    assertEquals("67 134 232 ", new Pixel(check.getRGBat(30,17)).toString());
    assertEquals("67 134 232 ", new Pixel(check.getRGBat(59,29)).toString());
  }

  @Test
  public void testCreateNonSquareCheckerBoard() {
    Checkerboard check = new Checkerboard();
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    check.execute(10, 24, colors);
    assertEquals("34 123 44 ", new Pixel(check.getRGBat(0,0)).toString());
    assertEquals("34 123 44 ",  new Pixel(check.getRGBat(0,9)).toString());
    assertEquals("67 134 232 ", new Pixel(check.getRGBat(0,10)).toString());
    assertEquals("67 134 232 ", new Pixel(check.getRGBat(2,19)).toString());
    assertEquals("34 123 44 ", new Pixel(check.getRGBat(39,59)).toString());
  }
}