import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import view.ImageTextView;

/**
 * Tests all operations of the ImageTextView class.
 */
public class ImageTextViewTest {

  @Test (expected = IllegalArgumentException.class)
  public void nullAppendable() throws IOException {
    Appendable ap = null;
    ImageTextView imageTextView = new ImageTextView(ap);

    imageTextView.renderMessage("hello");

  }

  @Test (expected = IllegalArgumentException.class)
  public void nullMultiLayer() throws IOException {
    Appendable ap = null;
    ImageTextView imageTextView = new ImageTextView(ap);

    imageTextView.renderMessage("hello");
  }

  @Test
  public void renderMessage() throws IOException {
    Appendable ap = new StringBuilder();
    ImageTextView imageTextView = new ImageTextView(ap);

    imageTextView.renderMessage("hello");
    assertEquals("hello\n", ap.toString());
  }


}