package view.observer;

import controller.IGUIController;
import controller.LayerController;

public interface IObserver {

  void setController(IGUIController lc);

  void perform(String cmd, String name);

}
