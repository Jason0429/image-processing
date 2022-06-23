package controller.gui;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.CommandType;
import model.ExceptionMessage;
import model.ImageInterface;
import model.Utils;
import model.commands.*;
import view.gui.ImageProcessingGUIView;

public class FeaturesImpl implements Features {
  private ImageInterface model;
  private final ImageProcessingGUIView view;

  public FeaturesImpl(ImageProcessingGUIView view)
          throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException(String.format(
              ExceptionMessage.SPECIFIC_NULL_ARGUMENT.toString(), "View"));
    }
    this.model = null;
    this.view = view;
    view.addFeatures(this);
  }

  @Override
  public void makeVisible() {
    this.view.makeVisible();
  }

  @Override
  public void load() {
    String loadFilePath = this.view.chooseLoadLocation();
    if (loadFilePath == null) {
      return;
    }
    try {
      this.model = ImageLoader.load(loadFilePath);
      BufferedImage bufferedLoadedImage = Utils.getBufferedImage(this.model);
      this.view.updateImagePreview(bufferedLoadedImage);
    } catch (IllegalArgumentException e) {
      this.view.showError(e.getMessage());
    }
  }

  @Override
  public void save() {
    if (this.model == null) {
      this.view.showError("No image loaded.");
      return;
    }
    String exportFilePath = this.view.chooseExportLocation();
    if (exportFilePath == null) {
      return;
    }
    try {
      ImageExporter.export(this.model, exportFilePath);
    } catch (IllegalArgumentException e) {
      this.view.showError(e.getMessage());
    }
  }

  @Override
  public void apply() {
    if (this.model == null) {
      this.view.showError("No image loaded.");
      return;
    }
    String query = this.view.getSelectedQuery();
    if (query.equals(CommandType.BRIGHTEN.toString())) {
      int value = this.view.askForIntegerValue(10, -255, 255, 1);
      System.out.println("value = " + value);
      this.model = new BrightenCommand(value).process(this.model);
      this.view.updateImagePreview(Utils.getBufferedImage(this.model));
    } else {
      ImageProcessingCommand cmd = Utils.getCommandMap().getOrDefault(query, null);
      if (cmd != null) {
        this.model = cmd.process(this.model);
        this.view.updateImagePreview(Utils.getBufferedImage(this.model));
      }
    }
  }
}
