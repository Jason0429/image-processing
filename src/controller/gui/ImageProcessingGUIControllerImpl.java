package controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import controller.exporter.ImageExporter;
import controller.loader.ImageLoader;
import model.CommandType;
import model.ImageInterface;
import model.Utils;
import model.commands.BrightenCommand;
import model.commands.ImageProcessingCommand;
import view.gui.ImageProcessingGUIView;
import view.gui.ImageProcessingGUIViewImpl;

public class ImageProcessingGUIControllerImpl
        implements ImageProcessingGUIController, ActionListener {
  private final Features features;

  public ImageProcessingGUIControllerImpl(ImageProcessingGUIView view, Features features)
          throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.");
    }

    this.features = features;

    view.setListener(this);
  }

  public static void main(String[] args) {
    ImageProcessingGUIViewImpl view = new ImageProcessingGUIViewImpl();
    Features features = new FeaturesImpl(view);
    ImageProcessingGUIControllerImpl controller =
            new ImageProcessingGUIControllerImpl(view, features);
    controller.start();
  }

  @Override
  public void start() throws IllegalStateException {
    this.features.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch(event.getActionCommand()) {
      case "load":
        this.features.load();
        break;
      case "save":
        this.features.save();
        break;
      case "apply":
        this.features.apply();
        break;
      default:
        break;
    }
  }
}
