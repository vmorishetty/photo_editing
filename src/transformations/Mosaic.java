package transformations;

import image.IImage;
import image.Pixel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creates a mosaic of the image by randomly placing seeds that become a cluster of a pixels of one
 * color.
 */
public class Mosaic implements ITransformationCommander {


  /**
   * Performs the Mosaic transformation on an image based on the of seeds.
   * @param image   image to be transformed
   * @param seeds   number of seeds to become clusters in the image
   */
  @Override
  public void execute(IImage image, double[][] seeds) {
    int numSeeds = (int) seeds[0][0];

    if (numSeeds > image.imageHeight() * image.imageWidth()) {
      throw new IllegalArgumentException("too many seeds");
    }

    HashMap<Point, ArrayList<Point>> clusters = new HashMap<>();

    while (numSeeds != 0) {
      int randy = (int)(Math.random() * image.imageHeight());
      int randx = (int)(Math.random() * image.imageWidth());
      Point coord = new Point(randy, randx);
      if (!clusters.containsKey(coord)) {
        numSeeds--;
        ArrayList<Point> points = new ArrayList<>();
        points.add(coord);
        clusters.put(new Point(randy, randx), points);
      }
    }

    for (int y = 0; y < image.imageHeight(); y ++) {
      for (int x = 0; x < image.imageWidth(); x++) {
        double distance = Integer.MAX_VALUE;
        Point dest = null;
        for (Point key: clusters.keySet()) {
          double dist = Math.sqrt(Math.pow(y - key.x, 2) + Math.pow(x - key.y, 2));
          if (dist < distance) {
            distance = dist;
            dest = key;
          }
        }
        ArrayList<Point> cluster = clusters.get(dest);
        cluster.add(new Point(y,x));
        clusters.put(dest, cluster);
      }
    }

    for (ArrayList<Point> group: clusters.values()) {
      int[] colors = new int[]{0,0,0};
      for (Point p: group) {
        int[] rgb = image.getRGBat(p.x, p.y);
        colors[0] += rgb[0];
        colors[1] += rgb[1];
        colors[2] += rgb[2];
      }
      colors[0] /= group.size();
      colors[1] /= group.size();
      colors[2] /= group.size();
      for (Point p: group) {
        image.alterImage(p.x, p.y, new Pixel(colors));
      }
    }
  }
}