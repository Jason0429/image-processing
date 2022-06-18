package view.gui;

public interface Features {

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Update the current image in the view.
   */
  void updateImage();

  /**
   * Update the histogram in the view.
   */
  void updateHistogram();

  /**
   * Get the current selected processing query, if any.
   */
  String getSelectedQuery();

  /**
   * Exports the current image.
   */
  void exportImage();

  /**
   * Quit the program.
   */
  void quitProgram();
}
