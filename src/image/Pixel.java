package image;

/**
 * Represents the RGB Values of an image.
 */
public class Pixel implements IPixel {

  private final int[] rgb;

  /**
   * Constructs a pixel.
   * @param rgb   the red, green, and blue values of a pixel in an image
   */
  public Pixel(int[] rgb) throws IllegalArgumentException {
    if (rgb == null) {
      throw new IllegalArgumentException("null Pixel");
    }

    this.rgb = rgb;
  }

  /**
   * Getter for the RGB value of a pixel.
   * @return  int[]
   */
  public int[] getRgb() {
    int[] colors = new int[3];
    colors[0] = rgb[0];
    colors[1] = rgb[1];
    colors[2] = rgb[2];
    return colors;
  }

  /**
   * Overrides toString to print out each color value.
   * @return  String
   */
  @Override
  public String toString() {
    return rgb[0] + " " + rgb[1] + " " + rgb[2] + " ";
  }
}
