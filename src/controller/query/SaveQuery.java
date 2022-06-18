package controller.query;

import controller.exporter.ImageExporter;
import model.ImageInterface;
import model.ImageProcessingModel;
import view.text.ImageProcessingView;

/**
 * Represents the save query command.
 */
public class SaveQuery extends AbstractQueryCommand {

  public SaveQuery(ImageProcessingModel model, ImageProcessingView view) {
    super(model, view);
  }

  @Override
  protected void executeCommand(String[] query) throws IllegalArgumentException {
    this.checkQueryLength(query, 3);
    String filePath = query[1];
    String imageName = query[2];
    ImageInterface imageToExport = this.model.getImage(imageName);
    ImageExporter.export(imageToExport, filePath);
    this.writeMessage("Successfully saved " + imageName + " to " + filePath + ".\n");
  }
}
