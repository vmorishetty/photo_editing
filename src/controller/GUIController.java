package controller;

import image.ImageUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import layer.MultiLayer;
import view.SwingView;
import view.observer.IObserver;

/**
 * Represents a controller for the Swing GUI that allows the user to perform specific commands
 * that perform specific actions on images that they can load in to a multi layered image structure.
 */
public class GUIController implements IGUIController {

  private final MultiLayer model;
  private final SwingView view;
  private final IObserver obs;

  public GUIController(MultiLayer model, SwingView view, IObserver obs) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("null parameters");
    }

    this.model = model;
    this.view = view;
    this.obs = obs;
    this.view.setObserver(obs);
    this.obs.setController(this);
    this.layers = new ArrayList<>();
    this.fileLocations = new ArrayList<>();
  }


  @Override
  public void readScript() {
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);
  }


  @Override
  public void readScript(String fileName) throws FileNotFoundException {

  }

  @Override
  public String getTopmostVisible() {
    return fileLocations.get(layers.indexOf(model.getTopVisible()));
  }

  @Override
  public void executeCommand(String cmd, String name) {
    System.out.println(cmd);
    System.out.println(name);

    switch(cmd) {
      case "blur":
        model.applyTransformation(cmd);
        break;
      case "sharpen":
        model.applyTransformation(cmd);
        break;
      case "sepia":
        model.applyTransformation(cmd);
        break;
      case "monochrome":
        model.applyTransformation(cmd);
        break;
      case "mosaic":
        model.applyTransformation(name);
        break;
      case "downscale":
        model.applyTransformation(name);
        break;
      case "create layer":
        model.addLayer(name);
        layers.add(name);
        fileLocations.add("");
        break;
      case "remove layer":
        model.removeLayer(name);
        fileLocations.remove(layers.indexOf(name));
        layers.remove(name);
        break;
      case "save":
        try {
          model.save(name);
        }
        catch (IOException io) {
          System.out.println("incorrect file name");
        }
        break;
      case "save all":
        try {
          model.saveAll(name);
        }
        catch (IOException io) {
          System.out.println("incorrect folder name");
        }
        break;
      case "current":
        model.setCurrent(name);
        current = layers.indexOf(name);
        break;
      case "load":
        try {
          model.load(name);
        } catch (IOException e) {
        }
        fileLocations.set(current, name);
        break;
      case "load all":
        try {
          model.loadAll(name);
          fileLocations = ImageUtil.importMultiLayer(name);
          for (String fileName: fileLocations) {
            layers.add(fileName.substring(0, fileName.length()
                - (ImageUtil.getExtension(fileName).length() + 1)));
          }
        }
        catch (IOException e) {}
        break;
      default:
        model.applyTransformation(cmd);
    }
  }

}
