package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import layer.MultiLayer;
import view.ImageTextView;
import view.ImageView;

/**
 * Represents a controller interface which allows users to input specific commands that perform
 * actions on images that they can load in to a multi layered image structure.
 */
public class LayerTextController implements LayerController {
  private final MultiLayer model;
  private final Readable rd;
  private final Appendable ap;

  /**
   * Constructs a new controller that allows for the capability for allowing users to type in
   * commands.
   * @param model a multilayer class that is where the images are stored
   * @param rd a readable that outputs the commands and any errors
   * @param ap an appendable that appends messages to the readable
   */
  public LayerTextController(MultiLayer model, Readable rd, Appendable ap) {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("null paramters");
    }

    this.model = model;
    this.rd = rd;
    this.ap = ap;

  }

  /**
   * Writes a given message to the console which the user can see.
   * @param message the message to be written
   * @param view the view channel that stores the image
   * @throws IllegalStateException if it cannot append
   */
  private void write(String message, ImageView view) throws IllegalStateException {
    try {
      view.renderMessage(message);
    }
    catch (IOException io) {
      throw new IllegalStateException("Cannot append \n");
    }
  }


  @Override
  public void readScript(String filename) throws FileNotFoundException {
    ImageView view = new ImageTextView(ap);
    File file = new File(".\\res\\" + filename);
    Scanner scanner = new Scanner(file);
    executeScript(scanner, view);
  }

  @Override
  public void readScript() {
    ImageView view = new ImageTextView(ap);
    Scanner scanner = new Scanner(rd);
    executeScript(scanner, view);
  }

  /**
   * Performs the actual script and parses the readable for user's inputted commands.
   * @param scanner a Scanner that contains the readable
   * @param view the view that the user can see
   */
  private void executeScript(Scanner scanner, ImageView view) {
    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();

      if (input.startsWith("create layer ")) {
        try {
          model.addLayer(input.substring(13));
        }
        catch (IllegalArgumentException iae) {
          write("Layer already exists", view);
        }
      }
      else if (input.startsWith("current ")) {
        try {
          model.setCurrent(input.substring(8));
        }
        catch (IllegalArgumentException iae) {
          write("Layer doesn't exist cannot set as current", view);
        }
      }
      else if (input.startsWith("remove layer ")) {
        try {
          model.removeLayer(input.substring(13));
        }
        catch (IllegalArgumentException iae) {
          write("Layer doesn't exist cannot remove", view);
        }
      }
      else if (input.startsWith("load all ")) {
        try {
          model.loadAll(input.substring(9));
        } catch (IOException io) {
          write("File location does not exist", view);
        }

      }
      else if (input.startsWith("load ")) {
        try {
          model.load(input.substring(5));
        } catch (IOException io) {
          write("File location does not exist", view);
        }
        catch (IllegalStateException iss) {
          write("Nothing set as current", view);
        }
      }
      else if (input.startsWith("invisible ")) {
        try {
          model.setVisibility(input.substring(10), false);
        } catch (IllegalArgumentException iae) {
          write("Layer doesn't exist cannot make invisible", view);
        }
      }
      else if (input.startsWith("show ")) {
        try {
          model.setVisibility(input.substring(5), true);
        } catch (IllegalArgumentException iae) {
          write("Layer doesn't exist cannot make invisible", view);
        }
      }
      else if (input.startsWith("save all ")) {
        try {
          model.saveAll(input.substring(9));
        }
        catch (IOException e) {
          write("IO failed.", view);
        }
        catch (IllegalStateException ise) {
          write("Layers do not have images yet", view);
        }
      }
      else if (input.startsWith("save ")) {
        try {
          model.save(input.substring(5));
        }
        catch (IOException e) {
          write("Invalid filetype or filetype not provided.", view);
        }
        catch (IllegalStateException efe) {
          write("Layer image does not exist.", view);
        }
      }
      else if (input.equalsIgnoreCase("end")) {
        write("Script has ended.", view);
        return;
      }
      else {
        try {
          model.applyTransformation(input);
        }
        catch (IllegalArgumentException iae) {
          write("Filter/transformation does not exist", view);
        }
      }
    }
    write("End script.\n", view);
  }
}
