package transformations;


import image.IImage;
import image.Pixel;

/**
 * Blurs and Sharpens and Image.
 */
public class Resolute implements ITransformationCommander {

  /**
   * Either Sharpens or Blurs an image based on the kernel.
   * @param image   the given image that will be blurred or sharpened
   * @param kernel  the matrix that will change the image
   */
  @Override
  public void execute(IImage image, double[][] kernel) throws IllegalArgumentException {
    if (image == null || kernel == null) {
      throw new IllegalArgumentException("no  null parameters");
    }

    if (kernel.length != kernel[0].length) {
      throw new IllegalArgumentException("must be square kernel");
    }

    int[][] red = image.getColorArray(0);
    int[][] green = image.getColorArray(1);
    int[][] blue = image.getColorArray(2);

    int kernelMetric = kernel.length;

    for (int y = 0; y < image.imageHeight(); y++) {
      for (int x = 0; x < image.imageWidth(); x++) {

        int[][] redAdjacent = getAdjacentValues(red, kernelMetric, y, x);
        int[][] greenAdjacent = getAdjacentValues(green, kernelMetric, y, x);
        int[][] blueAdjacent = getAdjacentValues(blue, kernelMetric, y, x);

        int sumRed = sumValues(redAdjacent, kernel, image.getMaxValue());
        int sumGreen = sumValues(greenAdjacent, kernel, image.getMaxValue());
        int sumBlue = sumValues(blueAdjacent, kernel, image.getMaxValue());


        int[] p = new int[]{sumRed, sumGreen, sumBlue};
        image.alterImage(y, x, new Pixel(p));
      }
    }
  }

  /**
   * Returns the 2D array of pixels that needs to be used in the calculation for blurring and
   * sharpening images. This resulting 2D array will have the same dimensions as the blurring or
   * shapening kernel.
   * @param channel the 2D int array for the given color channel
   * @param kernelMetric the size of the kernel
   * @param y the row index location of the Pixel
   * @param x the column index location of the Pixel
   * @return the 2D array of pixels of size kernelMetric
   */
  protected int[][] getAdjacentValues(int[][] channel, int kernelMetric, int y, int x) {

    int[][] result = new int[kernelMetric][kernelMetric];
    int height = 0;
    int width = 0;

    for (int i = y - kernelMetric / 2; i < y + kernelMetric / 2 + 1; i++) {
      for (int j = x - kernelMetric / 2; j < x + kernelMetric / 2 + 1; j++) {

        try {
          result[height][width] = channel[i][j];
        }
        catch (ArrayIndexOutOfBoundsException e) {
          result[height][width] = 0;
        }

        width++;
        if (width == kernelMetric) {
          width = 0;
          height++;
        }
      }
    }

    return result;
  }


  /**
   * Returns an int which performs the filtering operation and applies the blurring or
   * sharpening kernel to the pixels that are affected.
   * @param channelAdjacents the Pixels that are using the resolute operation for a specific color
   * @param kernel a 2D array of the kernel that is the filter matrix
   * @param max the max value of any RGB value
   * @return the respective channel's int value that was changed after the filter
   */
  protected int sumValues(int[][] channelAdjacents, double[][] kernel, int max) {

    int sum = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {

        sum += (channelAdjacents[i][j] * kernel[i][j]);

      }
    }

    if (sum > max) {
      return max;
    }
    else if (sum < 0) {
      return 0;
    }

    return sum;
  }
}
