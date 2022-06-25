package controller.gui;

/**
 * Contains all the methods for a GUI implementation of Image Processing.
 */
public interface Features {

  /**
   * Make the current view visible.
   */
  void makeVisible();

  /**
   * Load a new image.
   */
  void load();

  /**
   * Save the current image.
   */
  void save();

  /**
   * Apply a command to the current image.
   */
  void apply();
}
