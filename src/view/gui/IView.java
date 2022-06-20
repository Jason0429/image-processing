package view.gui;

import java.awt.image.BufferedImage;

import controller.gui.Features;

public interface IView {

  void makeVisible();

  void update(BufferedImage img);

  String getSelectedQuery();

  void addFeatures(Features features);
}
