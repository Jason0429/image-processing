package controller.gui;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.ImageInterface;
import model.Utils;
import model.commands.*;
import view.gui.ImageProcessingGUIView;

import java.util.HashMap;
import java.util.Map;

public class FeaturesImpl implements Features {
  private final ImageProcessingGUIView view;
  private ImageInterface model;

  public FeaturesImpl(ImageProcessingGUIView view, ImageInterface model) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    this.view = view;
    this.model = model;
  }

  @Override
  public void makeVisible() {
    this.view.makeVisible();
  }

  @Override
  public void update(String imgPath) throws IllegalArgumentException {
    this.model = ImageLoader.load(imgPath);
    this.view.updateImagePreview(Utils.convertBuffered(this.model));
  }

  @Override
  public void exportImage(String filePath) throws IllegalArgumentException {
    if (this.model == null) {
      throw new IllegalArgumentException("No image loaded!");
    }
    ImageExporter.export(this.model, filePath);
  }

  @Override
  public void processImage(String cmdName) throws IllegalArgumentException {
    Map<String, ImageProcessingCommand> commandMap = new HashMap<String, ImageProcessingCommand>();
    commandMap.put("red-component", new RedComponentGreyscaleCommand());
    commandMap.put("green-component", new GreenComponentGreyscaleCommand());
    commandMap.put("blue-component", new BlueComponentGreyscaleCommand());
    commandMap.put("value-component", new ValueComponentGreyscaleCommand());
    commandMap.put("luma-component", new LumaProcessingCommand());
    commandMap.put("intensity-component", new IntensityComponentGreyscaleCommand());
    commandMap.put("horizontal-flip", new FlipHorizontalCommand());
    commandMap.put("vertical-flip", new FlipVerticalCommand());
    commandMap.put("brighten", new BrightenCommand(1));
    commandMap.put("greyscale", new LumaProcessingCommand());
    commandMap.put("gaussian-blur", new GaussianBlurCommand());
    commandMap.put("sharpen", new SharpenCommand());
    commandMap.put("sepia", new SepiaProcessingCommand());
    ImageProcessingCommand cmd = commandMap.getOrDefault(cmdName, null);
    this.process(cmd);
  }

  @Override
  public void processImage(String cmdName, int value) throws IllegalArgumentException {
    Map<String, ImageProcessingCommand> commandMap = new HashMap<String, ImageProcessingCommand>();
    commandMap.put("brighten", new BrightenCommand(value));
    ImageProcessingCommand cmd = commandMap.getOrDefault(cmdName, null);
    this.process(cmd);
  }

  private void process(ImageProcessingCommand cmd) throws IllegalArgumentException {
    if (this.model == null) {
      throw new IllegalArgumentException("No image loaded!");
    }
    if (cmd != null) {
      this.model = cmd.process(this.model);
      this.view.updateImagePreview(Utils.convertBuffered(this.model));
    }
  }

  @Override
  public void quitProgram() {
    System.exit(0);
  }
}
