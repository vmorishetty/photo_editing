package controller;

import java.io.FileNotFoundException;

/**
 * Represents a controller for layers which allow the users to perform function of different
 * images that they can load into a multilayer structure.
 */
public interface LayerController {

  /**
   * Opens a new script where the user can input commands that they want to perform based on
   * what they can do to a multi layered image structure.
   */
  void readScript();

  /**
   * Reads an inputted script that is from the res folder and this inputted script is parsed and
   * all the functions inside of the script are performed in the controller.
   * @param fileName the name of the script file
   * @throws FileNotFoundException if file is invalid
   */
  void readScript(String fileName) throws FileNotFoundException;

}
