import static org.junit.Assert.assertEquals;
import image.IPixel;
import image.Pixel;

import org.junit.Test;

/**
 * Tests the operations of the Pixel class.
 */
public class PixelTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullPixel() {
    IPixel color = new Pixel(null);
  }

  @Test
  public void testDeepCopyBlue() {
    int[] b = new int[]{0, 0, 255};
    IPixel blue = new Pixel(b);
    assertEquals(false, blue.getRgb().equals(blue));
  }

  @Test
  public void testToStringColor() {
    int[] c = new int[]{235, 53, 175};
    Pixel color = new Pixel(c);
    assertEquals("235 53 175 ", color.toString());

  }
}