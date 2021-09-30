package image;

/**
 * Specifies operations for different types of Programmatic Images to be created.
 */
public interface ICreateImage {

  /**
   * Creates an Image based on the given features.
   * @param squareSize    number of pixels in a square of an image
   * @param numSquares    number of squares in an image
   * @param colors        the colors to be used for the image
   */
  void execute(int squareSize, int numSquares, IPixel[] colors);
}
