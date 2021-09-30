package transformations;


import image.IImage;

/**
 * Operation to change the given image based on kernel.
 */
public interface ITransformationCommander {

  /**
   * Transsforms and image based on its kernel.
   * @param image   image to be transformed
   * @param kernel  matrix that transforms the image
   */
  void execute(IImage image, double[][] kernel);
}
