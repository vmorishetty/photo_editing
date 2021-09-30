package transformations;

import image.IImage;
import image.Pixel;
import java.awt.Point;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Downscales an image to a given dimensions.
 */
public class Downscale implements ITransformationCommander {

  /**
   * Downscales an image with a given width and height.
   * @param image   image to be transformed
   * @param dimensions    the width and height the image should become
   */
  @Override
  public void execute(IImage image, double[][] dimensions) {
    int newWidth = (int) dimensions[0][0];
    int newHeight = (int) dimensions[0][1];

    if (newWidth <= 0 || newWidth > image.imageWidth()
        || newHeight <= 0 || newHeight > image.imageHeight()) {
      throw new IllegalArgumentException("Invalid dimensions");
    }

    Pixel[][] downscale = new Pixel[newHeight][newWidth];

    HashMap<Point, ArrayList<int[]>> conversions = new HashMap<>();

    for (int i = 0; i < newHeight; i++) {
      for (int j = 0; j < newWidth; j++) {
        conversions.put(new Point(i, j), new ArrayList<>());
      }
    }

    for (int i = 0; i < image.imageHeight(); i++) {
      for (int j = 0; j < image.imageWidth(); j++) {

        int newI = i * newHeight / image.imageHeight();
        int newJ = j * newWidth / image.imageWidth();

        int[] oldRGB = image.getRGBat(i, j);

        ArrayList<int[]> hi = conversions.get(new Point(newI, newJ));
        hi.add(oldRGB);
      }
    }

    for (Point point: conversions.keySet()) {

      int[] arr = getNewRGBArray(conversions.get(point));

      downscale[point.x][point.y] = new Pixel(arr);

    }


    image.newPixels(downscale);

  }

  /**
   * Creates the new set of colors for each pixel in the downscaled image.
   * @param ints  the sets of colors for each pixel
   * @return  int[]
   */
  private int[] getNewRGBArray(ArrayList<int[]> ints) {


    int length = ints.size();

    if (length == 0) {
      return new int[]{170, 170, 170};
    }

    int totalRed = 0;
    int totalGreen = 0;
    int totalBlue = 0;

    for (int[] rgb: ints) {
      totalRed += rgb[0];
      totalGreen += rgb[1];
      totalBlue += rgb[2];
    }

    return new int[]{(totalRed / length), (totalGreen / length), (totalBlue / length)};
  }
}
