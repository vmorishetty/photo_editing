package image;

/**
 * Represents the Abstract class for Images. This class contains all of the simple operations to be
 * performed on an Image type.
 */
public abstract class AbstractImage implements IImage {

  protected IPixel[][] pixels;
  protected final int maxValue;

  /**
   * Creates an Abstract Image with a maximum RGB color value passed into it.
   * @param maxValue a maximum RGB color value
   */
  public AbstractImage(int maxValue) {
    this.maxValue = maxValue;
  }

  /**
   * Creates an Abstract Image with a maximum RGB color value passed in and a 2D array of pixels
   * that represents the image.
   * @param pixels a 2D array of Pixels to represent the image
   * @param maxValue a maximum RGB color value
   */
  public AbstractImage(IPixel[][] pixels, int maxValue) {
    this.pixels = pixels;
    this.maxValue = maxValue;
  }

  /**
   * Replaces the pixel at the location given with a new pixel that is passed into the function.
   * @param y the row index of the 2D array of Pixels
   * @param x the column index of the 2D array of Pixels
   * @param pixel the Pixel that is to be placed in the 2D array of Pixels
   */
  public void alterImage(int y, int x, IPixel pixel) {
    this.pixels[y][x] = pixel;
  }

  /**
   * Returns the amount of rows in the 2D array of Pixels.
   * @return the amount of rows of pixels
   */
  public int imageHeight() {
    return pixels.length;
  }

  /**
   * Returns the amount of columns in the 2D array of Pixels.
   * @return the amount of columns of pixels
   */
  public int imageWidth() {
    return pixels[0].length;
  }

  /**
   * Returns the max value for each RGB channel value.
   * @return an int which is the max RGB value
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Returns the Pixel at a given location.
   * @param y the row index
   * @param x the column index
   * @return a Pixel which was requested
   */
  public int[] getRGBat(int y, int x) {

    return pixels[y][x].getRgb();
  }

  /**
   * Returns a 2D array for a requested color channel. Returns either the red channel, the
   * green channel, or the blue channel.
   * @param colorNum an int which determines which color channel to return
   * @return a 2D array of integers
   */
  public int[][] getColorArray(int colorNum) {
    int[][] color = new int[pixels.length][pixels[0].length];

    for (int y = 0; y < pixels.length; y++) {
      for (int x = 0; x < pixels[y].length; x++) {

        color[y][x] = pixels[y][x].getRgb()[colorNum];
      }
    }
    return color;
  }

  /**
   * Alters the pixels of an image without creating a new image.
   * @param pixels  the new pixels of the image
   */
  public void newPixels(IPixel[][] pixels) {
    this.pixels = pixels;
  }

}
