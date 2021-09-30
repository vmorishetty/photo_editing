import static org.junit.Assert.assertEquals;

import image.IImage;
import image.ImageUtil;
import image.Pixel;
import image.SimpleImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;


/**
 * Tests the operations of the ImageUtil class including the I/O operations.
 */
public class ImageUtilTest {


  @Test (expected = FileNotFoundException.class)
  public void invalidFileReturnPixels() throws FileNotFoundException {
    Pixel[][] pixels =
        ImageUtil.importPPM("C:\\Users\\hsubr\\CS3500 projects\\HelloGrader!\\res\\cake.ppm");
  }

  @Test
  public void returnPixelsForCakePPM() throws FileNotFoundException {
    Pixel[][] pixels =
        ImageUtil.importPPM("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\res\\cake.ppm");
    assertEquals(540, pixels.length);
    assertEquals(720, pixels[0].length);
    assertEquals("123 125 124 ", pixels[0][0].toString());
    assertEquals("157 165 161 ", pixels[100][0].toString());
    assertEquals("189 198 195 ", pixels[0][400].toString());
    assertEquals("199 215 211 ", pixels[220][700].toString());
    assertEquals("23 25 24 ", pixels[539][0].toString());
    assertEquals("215 225 207 ", pixels[0][719].toString());
    assertEquals("252 254 253 ", pixels[539][719].toString());
  }

  @Test
  public void returnMaxValueCake() throws FileNotFoundException {
    assertEquals(255,
        ImageUtil.returnMaxValue("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\res\\cake.ppm"));
  }

  @Test
  public void returnMaxValueWallpaper() throws FileNotFoundException {
    assertEquals(255,
        ImageUtil.returnMaxValue("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\res\\wallpaper.ppm"));
  }


  @Test (expected = FileNotFoundException.class)
  public void returnMaxValueInvalidFile() throws FileNotFoundException {
    assertEquals(255,
        ImageUtil.returnMaxValue(
            "C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\invalid_folder\\wallpaper.ppm"));
  }

  @Test
  public void exportImageToPPM() throws IOException {
    IImage cake = new SimpleImage("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\res\\cake.ppm");
    ImageUtil.exportImageToPPM(cake, "output");
    IImage cakeExported = new SimpleImage("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\output.ppm");
    assertEquals(540, cakeExported.imageHeight());
    assertEquals(720, cakeExported.imageWidth());
    assertEquals(255, cakeExported.getMaxValue());
    assertEquals(new Pixel(cake.getRGBat(0, 0)).toString(),
        new Pixel(cakeExported.getRGBat(0, 0)).toString());
    assertEquals(new Pixel(cake.getRGBat(110, 540)).toString(),
        new Pixel(cakeExported.getRGBat(110, 540)).toString());
    assertEquals(new Pixel(cake.getRGBat(220, 400)).toString(),
        new Pixel(cakeExported.getRGBat(220, 400)).toString());
    assertEquals(new Pixel(cake.getRGBat(539, 719)).toString(),
        new Pixel(cakeExported.getRGBat(539, 719)).toString());
    assertEquals(new Pixel(cake.getRGBat(539, 0)).toString(),
        new Pixel(cakeExported.getRGBat(539, 0)).toString());
    assertEquals(new Pixel(cake.getRGBat(0, 719)).toString(),
        new Pixel(cakeExported.getRGBat(0, 719)).toString());

  }

  @Test (expected = IOException.class)
  public void exportImageToPPMIOException() throws IOException {
    IImage cake = new SimpleImage("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\res\\cake.ppm");
    ImageUtil.exportImageToPPM(cake, "output");
    IImage cakeExported =
        new SimpleImage("C:\\Users\\hsubr\\CS3500 projects\\OOD_HW5\\varun\\output.ppm");
  }

}