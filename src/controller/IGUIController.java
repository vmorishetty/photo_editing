package controller;

public interface IGUIController extends LayerController {

  void executeCommand(String cmd, String name);

  String getTopmostVisible();


}
