package image;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * A PPM image that will be read and may have some filters/transformations applied onto.
 */
public class SimpleImage extends AbstractImage {

  /**
   * Constructs a SimpleImage.
   * @param filename                  filename that will read the image
   * @throws FileNotFoundException    if file does not exist
   */
  public SimpleImage(String filename)
      throws FileNotFoundException {
    super(ImageUtil.importPPM(Objects.requireNonNull(filename)),
        ImageUtil.returnMaxValue(filename));
  }

  public SimpleImage(Pixel[][] pixels) {
    super(pixels, 255);
  }
}
