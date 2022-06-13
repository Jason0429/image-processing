package model.commands;

import model.Pixel;

/**
 * Represents a command that uses each pixel's luma to produce a greyscale version.
 */
public class LumaProcessingCommand
        extends TransformationProcessingCommand implements ImageProcessingCommand {

  public LumaProcessingCommand() {
    super(Transformation.luma());
  }
}
