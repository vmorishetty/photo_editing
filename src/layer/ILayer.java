package layer;

import image.IImage;

/**
 * Specifies operations for different types of Layers to be created.
 */
public interface ILayer {

  /**
   * Determines if an image layer is visible or not.
   * @return  boolean
   */
  boolean isVisible();

  /**
   * Sets a layer to a given visibility.
   * @param visible   whether the image is invisible or not
   */
  void visible(boolean visible);

  /**
   * Gets the name of a layer.
   * @return  String
   */
  String getLayerName();

  /**
   * Gets the image associated with the layer.
   * @return  IImage
   */
  IImage getImage();

  /**
   * Gets the image type of the image in the layer.
   * @return  String
   */
  String getExtension() ;

}
