package controller.gui;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.CommandType;
import model.ImageInterface;
import model.Utils;
import model.commands.BrightenCommand;
import model.commands.ImageProcessingCommand;
import view.gui.ImageProcessingGUIViewImpl;
import view.gui.ImageProcessingGUIView;

public class ImageProcessingGUIControllerImpl implements ImageProcessingGUIController {
  private ImageInterface model;
  private final ImageProcessingGUIView view;

  public ImageProcessingGUIControllerImpl(ImageInterface model, ImageProcessingGUIView view)
          throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.");
    }
    this.model = model;
    this.view = view;
//    this.view.addFeatures(features);
    this.view.setListener(this);
  }

  public static void main(String[] args) {
    ImageProcessingGUIViewImpl view = new ImageProcessingGUIViewImpl();
    view.setVisible(true);
    ImageProcessingGUIControllerImpl controller = new ImageProcessingGUIControllerImpl(null, view);
  }

  @Override
  public void start() throws IllegalStateException {
    this.view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch(event.getActionCommand()) {
      case "load":
        String loadFilePath = this.view.chooseLoadLocation();
        if (loadFilePath == null) {
          break;
        }
        try {
          this.model = ImageLoader.load(loadFilePath);
          BufferedImage bufferedLoadedImage = Utils.convertBuffered(this.model);
          this.view.updateImagePreview(bufferedLoadedImage);
        } catch (IllegalArgumentException e) {
          this.view.showError(e.getMessage());
        }
        break;
      case "save":
        if (this.model == null) {
          this.view.showError("No image loaded.");
          break;
        }
        String exportFilePath = this.view.chooseExportLocation();
        if (exportFilePath == null) {
          break;
        }
        try {
          ImageExporter.export(this.model, exportFilePath);
        } catch (IllegalArgumentException e) {
          this.view.showError(e.getMessage());
        }
        break;
      case "apply":
        if (this.model == null) {
          this.view.showError("No image loaded.");
          break;
        }
        String query = this.view.getSelectedQuery();
        if (query.equals(CommandType.BRIGHTEN.toString())) {
          int value = this.view.askForIntegerValue(10, -255, 255, 1);
          System.out.println("value = " + value);
          this.model = new BrightenCommand(value).process(this.model);
          this.view.updateImagePreview(Utils.convertBuffered(this.model));
        } else {
          ImageProcessingCommand cmd = Utils.getCommandMap().getOrDefault(query, null);
          if (cmd != null) {
            this.model = cmd.process(this.model);
            this.view.updateImagePreview(Utils.convertBuffered(this.model));
          }
        }
        break;
      default:
        break;
    }
  }
}
