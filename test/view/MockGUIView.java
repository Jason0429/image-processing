package view;

import controller.gui.Features;
import model.CommandType;
import view.gui.ImageProcessingGUIView;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Mock implementation of the GUI view for testing.
 */
public class MockGUIView implements ImageProcessingGUIView {
  private CommandType commandType;
  private String exportLocation;
  private String loadLocation;
  private final Appendable log;
  private BufferedImage img;

  public MockGUIView(Appendable log) {
    this.commandType = null;
    this.exportLocation = null;
    this.loadLocation = null;
    this.log = log;
    this.img = null;
  }

  public void setCommandType(CommandType ct) {
    this.commandType = ct;
  }

  public void setExportLocation(String location) {
    this.exportLocation = location;
  }

  public void setLoadLocation(String location) {
    this.loadLocation = location;
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void updateImagePreview(BufferedImage img) {
    this.img = img;
  }

  @Override
  public String getSelectedQuery() {
    return this.commandType.toString();
  }

  @Override
  public String chooseExportLocation() {
    return this.exportLocation;
  }

  @Override
  public String chooseLoadLocation() {
    return this.loadLocation;
  }

  @Override
  public int askForIntegerValue(String valueName, int defaultValue, int min, int max, int increment) {
    switch (valueName) {
      case "Brighten":
        return 10;
      case "New Width":
      case "New Height":
        return max - 1;
      default:
        return 0;
    }
  }

  @Override
  public void showError(String message) {
    try {
      this.log.append(message);
    } catch (IOException ignored) {
    }
  }

  @Override
  public void addFeatures(Features features) {

  }
}
