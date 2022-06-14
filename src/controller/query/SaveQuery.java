package controller.query;

import java.io.IOException;

import controller.exporter.ImageExporter;
import controller.exporter.ImageExporterFactory;
import model.ImageInterface;
import model.ImageProcessingModel;
import view.ImageProcessingView;

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
    ImageExporterFactory.getImageExporter(imageToExport, filePath).export();
    this.writeMessage("Successfully saved " + imageName + " to " + filePath + ".\n");
  }
}
