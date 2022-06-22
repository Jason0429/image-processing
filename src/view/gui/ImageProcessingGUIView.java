package view.gui;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import controller.gui.Features;

public interface ImageProcessingGUIView {

  void makeVisible();

  void updateImagePreview(BufferedImage img);

  void setListener(ActionListener listener);

  String getSelectedQuery();

  String chooseExportLocation();

  String chooseLoadLocation();

  int askForIntegerValue(int defaultValue, int min, int max, int increment);

  void showError(String message);
}
