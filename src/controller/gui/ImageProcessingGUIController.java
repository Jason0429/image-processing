package controller.gui;

import java.awt.event.ActionListener;

/**
 * This interface contains the methods for the GUI controller that handles processing images.
 */
public interface ImageProcessingGUIController extends ActionListener {

  /**
   * Starts the image processing program.
   */
  void start() throws IllegalStateException;
}