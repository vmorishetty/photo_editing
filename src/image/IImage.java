package image;

/**
 * Specifies operations for different types of images.
 */
public interface IImage {


  /**
   * Replaces the pixel at the location given with a new pixel that is passed into the function.
   * @param y the row index of the 2D array of Pixels
   * @param x the column index of the 2D array of Pixels
   * @param pixel the Pixel that is to be placed in the 2D array of Pixels
   */
  void alterImage(int y, int x, IPixel pixel);

  /**
   * Returns the amount of columns in the 2D array of Pixels.
   * @return the amount of columns of pixels
   */
  int imageWidth();

  /**
   * Alters the pixels of an image without creating a new image.
   * @param pixels  the new pixels of the image
   */
  void newPixels(IPixel[][] pixels);

  /**
   * Returns the amount of rows in the 2D array of Pixels.
   * @return the amount of rows of pixels
   */
  int imageHeight();

  /**
   * Returns the max value for each RGB channel value.
   * @return an int which is the max RGB value
   */
  int getMaxValue();

  /**
   * Returns the Pixel at a given location.
   * @param y the row index
   * @param x the column index
   * @return a Pixel which was requested
   */
  int[] getRGBat(int x, int y);

  /**
   * Returns a 2D array for a requested color channel. Returns either the red channel, the
   * green channel, or the blue channel.
   * @param colorNum an int which determines which color channel to return
   * @return a 2D array of integers
   */
  int[][] getColorArray(int colorNum);
}
