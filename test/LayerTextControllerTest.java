import static org.junit.Assert.assertEquals;

import controller.LayerTextController;
import java.io.FileNotFoundException;
import java.io.StringReader;
import layer.MultiLayer;
import org.junit.Test;

/**
 * Tests all operations of the LayerTextController class.
 */
public class LayerTextControllerTest {

  @Test
  public void readScriptWithScript() throws FileNotFoundException {
    MultiLayer ml = new MultiLayer();
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("ff");
    LayerTextController layerTextController = new LayerTextController(ml, rd, ap);

    layerTextController.readScript("script2.txt");

    assertEquals("Filter/transformation does not exist\n"
        + "End script.\n\n", ap.toString());

  }

  @Test
  public void testReadScriptWithoutScript() {
    MultiLayer ml = new MultiLayer();
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("create layer hello \n current hi");
    LayerTextController layerTextController = new LayerTextController(ml, rd, ap);

    layerTextController.readScript();

    assertEquals("Filter/transformation does not exist\n"
        + "End script.\n\n", ap.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullMultiLayer() {
    MultiLayer ml = null;
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader("");
    LayerTextController layerTextController = new LayerTextController(ml, rd, ap);

  }

  @Test (expected = IllegalArgumentException.class)
  public void nullAppendable() {
    MultiLayer ml = new MultiLayer();
    Appendable ap = null;
    Readable rd = new StringReader("");
    LayerTextController layerTextController = new LayerTextController(ml, rd, ap);

  }

  @Test (expected = IllegalArgumentException.class)
  public void nullReadable() {
    MultiLayer ml = new MultiLayer();
    Appendable ap = new StringBuilder();
    Readable rd = null;
    LayerTextController layerTextController = new LayerTextController(ml, rd, ap);
  }

}