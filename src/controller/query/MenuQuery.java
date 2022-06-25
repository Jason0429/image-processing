package controller.query;

import model.ImageProcessingModel;
import view.text.ImageProcessingTextView;

/**
 * Represents the menu query command.
 */
public class MenuQuery extends AbstractQueryCommand {

  public MenuQuery(ImageProcessingModel model, ImageProcessingTextView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.writeMessage("Options:\n");
    this.writeMessage("menu (loads this menu)\n");
    this.writeMessage("list (lists all loaded images)\n");
    this.writeMessage("load [image-path] [image-name]\n");
    this.writeMessage("save [image-path] [image-name]\n");
    this.writeMessage("red-component [image-name] [dest-image-name]\n");
    this.writeMessage("green-component [image-name] [dest-image-name]\n");
    this.writeMessage("blue-component [image-name] [dest-image-name]\n");
    this.writeMessage("value-component [image-name] [dest-image-name]\n");
    this.writeMessage("luma-component [image-name] [dest-image-name]\n");
    this.writeMessage("intensity-component [image-name] [dest-image-name]\n");
    this.writeMessage("horizontal-flip [image-name] [dest-image-name]\n");
    this.writeMessage("vertical-flip [image-name] [dest-image-name]\n");
    this.writeMessage("brighten [image-name] [dest-image-name] [increment]\n");
    this.writeMessage("gaussian-blur [image-name] [dest-image-name]\n");
    this.writeMessage("sharpen [image-name] [dest-image-name]\n");
    this.writeMessage("* [source-image-name] [mask-image-name] [dest-image-name] [command-args, if any] "
            + "where * is any image processing command except flipping.\n");
    this.writeMessage("quit/q (quit the program)\n");
  }
}
