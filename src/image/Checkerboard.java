package image;

/**
 * Creates a Checkerboard style Image.
 */
public class Checkerboard extends AbstractImage implements ICreateImage {

  /**
   * Creates a Checkerboard.
   */
  public Checkerboard() {
    super(255);
  }

  /**
   * Creates a Checkerboard based on the given parameters.
   * @param squareSize    number of pixels in a square of an image
   * @param numSquares    number of squares in an image
   * @param colors        the colors to be used for the image
   * @throws IllegalArgumentException  if colors is null
   */
  @Override
  public void execute(int squareSize, int numSquares, IPixel[] colors)
      throws IllegalArgumentException {

    if (colors == null) {
      throw new IllegalArgumentException("no colors");
    }

    int checkerColor;

    int dim1 = (int)Math.sqrt(numSquares);
    while (numSquares % dim1 != 0) {
      dim1--;
    }
    int dim2 = numSquares / dim1;
    pixels = new IPixel[dim1 * squareSize][dim2 * squareSize];

    for (int y = 0; y < dim1; y++) {
      for (int x = 0; x < dim2; x++) {
        if ((y + x) % 2 == 0) {
          checkerColor = 1;
        }
        else {
          checkerColor = 0;
        }

        for (int i = y * squareSize; i < (y * squareSize) + squareSize; i++) {
          for (int p = x * squareSize; p < (x * squareSize) + squareSize; p++) {
            pixels[i][p] = colors[checkerColor] ;
          }
        }
      }
    }
  }
}
