package view;

import java.awt.event.ActionListener;
import java.io.IOException;
import view.observer.IObserver;

/**
 * Specifies the operations a view has to perform so that the user can see messages.
 */
public interface ImageView {

  /**
   * Adds a message to the appendable so that the user can see new error/confirmation messages.
   * @param message the message to be appended
   * @throws IOException is appendable doesn't exist
   */
  void renderMessage(String message) throws IOException;

  void setObserver(IObserver obs);
}
