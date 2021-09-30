import static org.junit.Assert.assertEquals;

import image.ApplyCreate;
import image.Checkerboard;
import image.IPixel;
import image.Pixel;
import org.junit.Test;


/**
 * Tests the operation for the ApplyCreate class that deal with the Checkerboard implementation.
 */
public class ApplyCreateTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullCommand() {
    ApplyCreate create = new ApplyCreate(null);
  }

  @Test
  public void testCreateCheckerboard() {
    Checkerboard check = new Checkerboard();
    ApplyCreate create  = new ApplyCreate(check);
    int[] green = new int[]{ 34, 123, 44};
    int[] blue = new int[]{67, 134, 232};
    IPixel[] colors = new Pixel[]{new Pixel(blue), new Pixel(green)};
    create.press(10, 16, colors);

    assertEquals("34 123 44 ", new Pixel(check.getRGBat(0,0)).toString());
    assertEquals("67 134 232 ", new Pixel(check.getRGBat(23,37)).toString());
    assertEquals("34 123 44 ", new Pixel(check.getRGBat(12,32)).toString());
  }
}