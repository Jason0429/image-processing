//package controller;
//
//import model.Image;
//import model.ImageProcessingModel;
//import model.commands.BrightenCommand;
//
//import java.util.function.Function;
//
//public abstract class AbstractQueryCommand implements QueryCommand {
//  private final String[] query;
//  private final ImageProcessingModel model;
//  private Runnable displayInvalidCommand;
//
//  public AbstractQueryCommand(String[] query, ImageProcessingModel model,
//                              Runnable displayInvalidCommand) {
//    this.query = query;
//    this.model = model;
//    this.displayInvalidCommand = displayInvalidCommand;
//  }
//
//  @Override
//  public void execute() {
//    if (query.length != 4) {
//      this.displayInvalidCommand.run();
//      return;
//    }
//
//    try {
//      int change = Integer.parseInt(query[1]);
//      String imageName = query[2];
//      String destImageName = query[3];
//      Image unprocessedImage = this.model.getImage(imageName);
//      Image processedImage = new BrightenCommand(change).process(unprocessedImage);
//      this.model.storeImage(destImageName, processedImage);
//      this.displayMessage(
//              "Successfully brightened image and stored as: " + destImageName + "\n");
//    } catch (IllegalArgumentException e) {
//      this.displayInvalidCommandParametersError();
//    }
//  }
//}
