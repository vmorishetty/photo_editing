package transformations;

import image.IImage;
import image.Pixel;

/**
 * Applies a Color filter on the image.
 */
public class ColorTransform implements ITransformationCommander {

  /**
   * Changes the image based on the given kernel that applies a color transformation.
   * @param image   the given image that will be transformed
   * @param kernel  the matrix that will adjust the image
   */
  @Override
  public void execute(IImage image, double[][] kernel) throws IllegalArgumentException {
    if (image == null || kernel == null) {
      throw new IllegalArgumentException("null parameters");
    }

    if (kernel.length != 3 || kernel[0].length != 3) {
      throw new IllegalArgumentException("not 3by3 color transformation");
    }

    for (int y = 0; y < image.imageHeight(); y++) {
      for (int x = 0; x < image.imageWidth(); x++) {

        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
          result[i] = 0;
          for (int p = 0; p < 3; p++) {
            result[i] += kernel[i][p] * image.getRGBat(y,x)[p];
          }
          if (result[i] > 255) {
            result[i] = 255;
          }
          else if (result[i] < 0) {
            result[i] = 0;
          }
        }
        image.alterImage(y,x, new Pixel(result));
      }
    }
  }
}
