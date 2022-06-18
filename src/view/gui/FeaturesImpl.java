package view.gui;

import controller.exporter.ImageExporter;
import model.ImageInterface;

public class FeaturesImpl implements Features {
  private IView view;
  private ImageInterface model;

  public FeaturesImpl(IView view, ImageInterface model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void makeVisible() {
    this.view.makeVisible();
  }

  @Override
  public void update() {
    this.view.update(this.model);
  }

  @Override
  public String getSelectedQuery() {
    return this.view.getSelectedQuery();
  }

  @Override
  public void exportImage(String filePath) {
    ImageExporter.export(model, filePath);
  }

  @Override
  public void quitProgram() {

  }
}
