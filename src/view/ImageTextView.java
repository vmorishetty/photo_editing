package view;

import java.awt.event.ActionListener;
import java.io.IOException;
import view.observer.IObserver;

/**
 * A text view that outputs the responses of a model to the system for the user to see.
 */
public class ImageTextView implements ImageView {

  private final Appendable ap;

  /**
   * Constructs a new ImageTextView containing a multi layered image storage system and an
   * appendable for the user to view messages in.
   * @param ap the Appendable for the message outputting
   */
  public ImageTextView(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException("null appendable");
    }
    this.ap = ap;
  }


  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message + "\n");
  }

  @Override
  public void setObserver(IObserver obs) { }

}
