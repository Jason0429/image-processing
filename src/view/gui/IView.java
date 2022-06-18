package view.gui;

import model.ImageInterface;

public interface IView {

  void makeVisible();

  void update(ImageInterface img);

  String getSelectedQuery();

  void addFeatures(Features features);
}
