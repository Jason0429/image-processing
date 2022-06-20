package view.gui;

import controller.loader.ImageLoader;
import model.ImageInterface;

public class GUIController {

  public GUIController(ImageInterface model, IView view) {
    Features features = new FeaturesImpl(view, model);
    view.addFeatures(features);
  }

  public static void main(String[] args) {
    GUIController controller = new GUIController(null, new GUIView());
  }
}
