import static org.junit.Assert.assertEquals;

import image.IImage;
import image.SimpleImage;
import java.io.FileNotFoundException;
import org.junit.Test;

/**
 * Tests Operations for SimpleImage class.
 */
public class SimpleImageTest {

  @Test(expected = FileNotFoundException.class)
  public void testInvalidFile() throws FileNotFoundException {
    IImage image = new SimpleImage("adhadhglhadhao");
  }

  @Test
  public void testFileRead() throws FileNotFoundException {
    IImage image =
        new SimpleImage("C:\\Users\\varun\\IdeaProjects\\hw5\\res\\cake.ppm");
    assertEquals(540, image.imageHeight());
  }

}