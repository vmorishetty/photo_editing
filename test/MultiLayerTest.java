import static org.junit.Assert.assertEquals;

import java.io.IOException;
import layer.IMultiLayer;
import layer.MultiLayer;
import org.junit.Test;

/**
 * Tests operations for the MultiLayer class.
 */
public class MultiLayerTest {

  @Test
  public void testAddLayer() throws IOException {
    IMultiLayer ml = new MultiLayer();
    ml.addLayer("first");
    ml.setCurrent("first");
    ml.load("chika.jpg");
    assertEquals(1, ml.getLayers().size());
  }

  @Test
  public void testRemoveLayer() throws IOException {
    IMultiLayer ml = new MultiLayer();
    ml.addLayer("first");
    ml.setCurrent("first");
    ml.load("chika.jpg");
    assertEquals(1, ml.getLayers().size());
    ml.removeLayer("first");
    assertEquals(0, ml.getLayers().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void fes() throws IOException {
    IMultiLayer ml = new MultiLayer();
    ml.addLayer("first");
    ml.setCurrent("first");
    ml.load("chika.jpg");
    ml.removeLayer("first");
    ml.removeLayer("first");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddDuplicateLayer() {
    IMultiLayer ml = new MultiLayer();
    ml.addLayer("first");
    ml.addLayer("first");
  }

  @Test
  public void testGetDeepLayers() throws IOException {
    IMultiLayer ml = new MultiLayer();
    ml.addLayer("first");
    ml.setCurrent("first");
    ml.load("chika.jpg");
    ml.addLayer("second");
    ml.setCurrent("second");
    ml.load("dababy.jpg");
    assertEquals(false, ml.getLayers().equals(ml.getLayers()));

  }
}