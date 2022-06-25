package controller.gui;

import controller.ImageProcessingController;

/**
 * Represents the controller class for GUI user-interface of the program.
 */
public class ImageProcessingGUIControllerImpl implements ImageProcessingController {
  private final Features features;

  /**
   * Constructs a GUI controller given a set of features.
   *
   * @param features the available features to be used in the program.
   */
  public ImageProcessingGUIControllerImpl(Features features) {
    this.features = features;
  }

  @Override
  public void start() throws IllegalStateException {
    this.features.makeVisible();
  }
}
