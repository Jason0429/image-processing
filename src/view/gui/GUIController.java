package view.gui;

import controller.loader.ImageLoader;
import model.ImageInterface;

public class GUIController {

  private ImageInterface model;
  private IView view;
  private Features features;

  public GUIController(ImageInterface model, IView view) {
    this.model = model;
    this.view = view;
    this.features = new FeaturesImpl(view, model);
    this.view.addFeatures(this.features);
    this.view.update(this.model);
  }

  public static void main(String[] args) {
    GUIController controller = new GUIController(ImageLoader.load("res/mario.png"), new GUIView());
  }
}
