package model.commands;

import model.Pixel;

/**
 * Represents a processing command applies a transformation to an image.
 */
public abstract class TransformationProcessingCommand
        extends PixelInPlaceProcessingCommand implements ImageProcessingCommand {
  private final double[][] transformationMatrix;

  /**
   * Constructs a transformation processing command given a transformation matrix.
   *
   * @param transformationMatrix the transformation matrix.
   */
  public TransformationProcessingCommand(double[][] transformationMatrix) {
    if (transformationMatrix.length == 3 && transformationMatrix[0].length == 3) {
      this.transformationMatrix = transformationMatrix;
    } else {
      throw new IllegalArgumentException("Transformation matrix must be 3x3");
    }
  }

  /**
   * Produces a transformed version of the pixel.
   *
   * @param pixel the pixel to be processed
   * @return the transformed version of the pixel
   * @throws IllegalArgumentException if the pixel is null
   */
  @Override
  protected Pixel processPixel(Pixel pixel) throws IllegalArgumentException {
    int red = (int) (this.transformationMatrix[0][0] * pixel.getRed()
            + (this.transformationMatrix[0][1] * pixel.getGreen())
            + (this.transformationMatrix[0][2] * pixel.getBlue()));
    int green = (int) (this.transformationMatrix[1][0] * pixel.getRed()
            + (this.transformationMatrix[1][1] * pixel.getGreen())
            + (this.transformationMatrix[1][2] * pixel.getBlue()));
    int blue = (int) (this.transformationMatrix[2][0] * pixel.getRed()
            + (this.transformationMatrix[2][1] * pixel.getGreen())
            + (this.transformationMatrix[2][2] * pixel.getBlue()));
    return new Pixel(pixel.getMaxValue(),
            Math.max(0, Math.min(pixel.getMaxValue(), red)),
            Math.max(0, Math.min(pixel.getMaxValue(), green)),
            Math.max(0, Math.min(pixel.getMaxValue(), blue)),
            pixel.getAlpha());
  }
}
