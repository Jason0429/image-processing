package view.gui;

import model.ImageInterface;

public class GUIController {

  public GUIController(ImageInterface model, IView view, Features features) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    view.addFeatures(features);
  }

  public static void main(String[] args) {
    GUIView view = new GUIView();
    GUIController controller = new GUIController(null, view, new FeaturesImpl(view, null));
  }
}
