package controller.gui;

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
   * Processes the current image given the name of a command.
   *
   * @param cmdName the command name
   */
  void processImage(String cmdName);

  /**
   * Processes the current image given the name of a command with a value.
   *
   * @param cmdName the command name
   * @param value   the value for the command
   */
  void processImage(String cmdName, int value);

  /**
   * Exports the current image.
   */
  void exportImage(String filePath);

  /**
   * Quit the program.
   */
  void quitProgram();
}
