package view.gui;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.ImageInterface;
import model.commands.*;

import java.util.HashMap;
import java.util.Map;

public class FeaturesImpl implements Features {
  private final IView view;
  private ImageInterface model;

  public FeaturesImpl(IView view, ImageInterface model) {
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
    this.view.update(ImageExporter.convertBuffered(this.model));
  }

  @Override
  public String getSelectedQuery() {
    return this.view.getSelectedQuery();
  }

  @Override
  public void exportImage(String filePath) throws IllegalArgumentException {
    ImageExporter.export(model, filePath);
  }

  @Override
  public void processImage(String cmdName) {
    Map<String, ImageProcessingCommand> commandMap = new HashMap<String, ImageProcessingCommand>();
    commandMap.put("red-component", new RedComponentGreyscaleCommand());
    commandMap.put("green-component", new GreenComponentGreyscaleCommand());
    commandMap.put("blue-component", new BlueComponentGreyscaleCommand());
    commandMap.put("value-component", new ValueComponentGreyscaleCommand());
    commandMap.put("luma-component", new LumaProcessingCommand());
    commandMap.put("intensity-component", new IntensityComponentGreyscaleCommand());
    commandMap.put("horizontal-flip", new FlipHorizontalCommand());
    commandMap.put("vertical-flip", new FlipVerticalCommand());
    // TODO: Need to figure out how to add this increment value
    commandMap.put("brighten", new BrightenCommand(1));
    commandMap.put("greyscale", new LumaProcessingCommand());
    commandMap.put("gaussian-blur", new GaussianBlurCommand());
    commandMap.put("sharpen", new SharpenCommand());
    commandMap.put("sepia", new SepiaProcessingCommand());

    ImageProcessingCommand cmd = commandMap.getOrDefault(cmdName, null);
    if (cmd != null) {
      this.model = cmd.process(this.model);
      this.view.update(ImageExporter.convertBuffered(this.model));
    }
  }

  @Override
  public void quitProgram() {

  }
}
