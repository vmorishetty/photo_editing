package image;

/**
 * Invoker to send Image Creations to be created.
 */
public class ApplyCreate {

  private ICreateImage createImage;

  /**
   * Constructs an ApplyCreate.
   * @param createImage   the kind of Image to be created.
   */
  public ApplyCreate(ICreateImage createImage) throws IllegalArgumentException {
    if (createImage == null) {
      throw new IllegalArgumentException("null command");
    }

    this.createImage = createImage;
  }

  /**
   * Creates an Image via given parameters.
   * @param squareSize  number of pixels in a square of an image
   * @param numSquares  number of squares in an image
   * @param colors      the colors to be used in the image
   */
  public void press(int squareSize, int numSquares, IPixel[] colors) {
    createImage.execute(squareSize, numSquares, colors);
  }
}
