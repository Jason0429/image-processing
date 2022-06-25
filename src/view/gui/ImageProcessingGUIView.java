package view.gui;

import java.awt.image.BufferedImage;

import controller.gui.Features;

/**
 * Contains the methods required for an image processing GUI view.
 */
public interface ImageProcessingGUIView {

  void makeVisible();

  void updateImagePreview(BufferedImage img);

  String getSelectedQuery();

  String chooseExportLocation();

  String chooseLoadLocation();

  int askForIntegerValue(String valueName, int defaultValue, int min, int max, int increment);

  void showError(String message);

  void addFeatures(Features features);
}
