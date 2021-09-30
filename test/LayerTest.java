import static org.junit.Assert.assertEquals;

import image.IImage;
import image.ImageUtil;
import java.io.IOException;
import layer.Layer;
import image.SimpleImage;
import org.junit.Test;

/**
 * Tests all operations of the Layer class.
 */
public class LayerTest {

  @Test
  public void testEmptyLayerVisibility() {
    Layer lay = new Layer("first");
    assertEquals(false, lay.isVisible());
  }

  @Test
  public void testShow() {
    Layer lay = new Layer("first");
    lay.visible(true);
    assertEquals(true, lay.isVisible());
  }

  @Test
  public void testInvisible() {
    Layer lay = new Layer("first");
    lay.visible(true);
    assertEquals(true, lay.isVisible());
    lay.visible(false);
    assertEquals(false, lay.isVisible());
  }

  @Test
  public void testLayerName() {
    Layer lay = new Layer("first");
    assertEquals("first", lay.getLayerName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    Layer lay = new Layer(null);
  }

  @Test
  public void testImageConstructor() throws IOException {
    IImage image = new SimpleImage(ImageUtil.importImage("chika.jpg"));
    Layer chika = new Layer("chika", image, "jpg");
    assertEquals("jpg", chika.getExtension());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageConstructorNull() throws IOException {
    IImage image = new SimpleImage(ImageUtil.importImage("chika.jpg"));
    Layer chika = new Layer("chika", null, "jpg");
  }

  @Test
  public void testGetImage() throws IOException {
    IImage image = new SimpleImage(ImageUtil.importImage("chika.jpg"));
    Layer chika = new Layer("chika", image, "jpg");
    assertEquals(image, chika.getImage());
  }

  @Test
  public void testGetExtension() throws IOException {
    IImage image = new SimpleImage(ImageUtil.importImage("chika.jpg"));
    Layer chika = new Layer("chika", image, "jpg");
    assertEquals("jpg", chika.getExtension());
  }

  @Test
  public void testChikaVisibility() throws IOException {
    IImage image = new SimpleImage(ImageUtil.importImage("chika.jpg"));
    Layer chika = new Layer("chika", image, "jpg");
    assertEquals(true, chika.isVisible());
  }
}