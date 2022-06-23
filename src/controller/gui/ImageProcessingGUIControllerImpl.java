package controller.gui;

import controller.ImageProcessingController;
import view.gui.ImageProcessingGUIViewImpl;

public class ImageProcessingGUIControllerImpl implements ImageProcessingController {
  private final Features features;

  public ImageProcessingGUIControllerImpl(Features features) {
    this.features = features;
  }

  public static void main(String[] args) {
    ImageProcessingGUIViewImpl view = new ImageProcessingGUIViewImpl();
    Features features = new FeaturesImpl(view);
    ImageProcessingGUIControllerImpl controller = new ImageProcessingGUIControllerImpl(features);
    controller.start();
  }

  @Override
  public void start() throws IllegalStateException {
    this.features.makeVisible();
  }
}
