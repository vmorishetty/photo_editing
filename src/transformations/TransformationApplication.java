package transformations;


import image.IImage;

/**
 * Applies all the Filters and Colors Transformation on the image.
 */
public class TransformationApplication {

  private ITransformationCommander iTransformationCommander;

  /**
   * Creates an Invoker to invoke all filters and transformations.
   * @param iTransformationCommander  the transformation/filter
   */
  public TransformationApplication(ITransformationCommander iTransformationCommander)
      throws IllegalArgumentException {
    if (iTransformationCommander == null) {
      throw new IllegalArgumentException("null commander");
    }

    this.iTransformationCommander = iTransformationCommander;
  }

  /**
   * Invokes the trandformation/filter to be applied.
   * @param image   the given image to be applies upon to
   * @param kernel  the kernel/matrix that will change the image
   */
  public void press(IImage image, double[][] kernel) {
    iTransformationCommander.execute(image, kernel);
  }
}
