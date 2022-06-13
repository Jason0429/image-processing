package model.commands;

/**
 * Represents a command that produces a sepia version.
 */
public class SepiaProcessingCommand extends TransformationProcessingCommand implements ImageProcessingCommand {

  public SepiaProcessingCommand() {
    super(Transformation.sepia());
  }
}