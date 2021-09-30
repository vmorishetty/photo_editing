package view.observer;

import controller.IGUIController;
import controller.LayerController;
import image.IImage;
import java.awt.event.ActionEvent;

public class GUIObserver implements IObserver {

  private IGUIController layerController;

  public void setController(IGUIController lc) {
    this.layerController = lc;
  }

  @Override
  public void perform(String cmd, String name) {
    layerController.executeCommand(cmd, name);
  }

//  public IImage topMost() {
//    layerController.
//  }

}
