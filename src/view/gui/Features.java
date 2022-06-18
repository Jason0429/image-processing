package view.gui;

import model.ImageInterface;

public interface Features {

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Update the current image in the view.
   */
  void update(String imgPath);

  /**
   * Get the current selected processing query, if any.
   */
  String getSelectedQuery();

  /**
   * Exports the current image.
   */
  void exportImage(String filePath);

  /**
   * Quit the program.
   */
  void quitProgram();
}
