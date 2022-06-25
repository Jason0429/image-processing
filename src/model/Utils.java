package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.commands.BlueComponentGreyscaleCommand;
import model.commands.FlipHorizontalCommand;
import model.commands.FlipVerticalCommand;
import model.commands.GaussianBlurCommand;
import model.commands.GreenComponentGreyscaleCommand;
import model.commands.ImageProcessingCommand;
import model.commands.IntensityComponentGreyscaleCommand;
import model.commands.LumaProcessingCommand;
import model.commands.RedComponentGreyscaleCommand;
import model.commands.SepiaProcessingCommand;
import model.commands.SharpenCommand;
import model.commands.ValueComponentGreyscaleCommand;
import view.gui.PixelReader;

/**
 * Represents utilities class to store available commands and queries
 * that are available to the user.
 */
public final class Utils {

  /**
   * Returns all commands to be used in dropdown by the GUI.
   *
   * @return all dropdown commands.
   */
  public static CommandType[] getDropdownCommands() {
    return new CommandType[]{
      CommandType.RED_COMPONENT,
      CommandType.GREEN_COMPONENT,
      CommandType.BLUE_COMPONENT,
      CommandType.VALUE_COMPONENT,
      CommandType.LUMA_COMPONENT,
      CommandType.GREYSCALE,
      CommandType.INTENSITY_COMPONENT,
      CommandType.HORIZONTAL_FLIP,
      CommandType.VERTICAL_FLIP,
      CommandType.BRIGHTEN,
      CommandType.GAUSSIAN_BLUR,
      CommandType.SHARPEN,
      CommandType.SEPIA,
      CommandType.DOWNSCALE
    };
  }

  /**
   * Returns the command mappings for GUI program.
   *
   * @return the command name to command mappings as a {@code Map}.
   */
  public static Map<String, ImageProcessingCommand> getGUICommandMap() {
    Map<String, ImageProcessingCommand> commandMap = new HashMap<String, ImageProcessingCommand>();
    commandMap.put(CommandType.RED_COMPONENT.toString(), new RedComponentGreyscaleCommand());
    commandMap.put(CommandType.GREEN_COMPONENT.toString(), new GreenComponentGreyscaleCommand());
    commandMap.put(CommandType.BLUE_COMPONENT.toString(), new BlueComponentGreyscaleCommand());
    commandMap.put(CommandType.VALUE_COMPONENT.toString(), new ValueComponentGreyscaleCommand());
    commandMap.put(CommandType.LUMA_COMPONENT.toString(), new LumaProcessingCommand());
    commandMap.put(CommandType.INTENSITY_COMPONENT.toString(),
            new IntensityComponentGreyscaleCommand());
    commandMap.put(CommandType.HORIZONTAL_FLIP.toString(), new FlipHorizontalCommand());
    commandMap.put(CommandType.VERTICAL_FLIP.toString(), new FlipVerticalCommand());
    commandMap.put(CommandType.GREYSCALE.toString(), new LumaProcessingCommand());
    commandMap.put(CommandType.BRIGHTEN.toString(), new LumaProcessingCommand());
    commandMap.put(CommandType.GAUSSIAN_BLUR.toString(), new GaussianBlurCommand());
    commandMap.put(CommandType.SHARPEN.toString(), new SharpenCommand());
    commandMap.put(CommandType.SEPIA.toString(), new SepiaProcessingCommand());
    commandMap.put(CommandType.DOWNSCALE.toString(), new SepiaProcessingCommand());
    return commandMap;
  }

  /**
   * Returns the command mappings for text view program.
   *
   * @return the command name to command mappings as a {@code Map}.
   */
  public static Map<String, ImageProcessingCommand> getTextViewCommandMap() {
    Map<String, ImageProcessingCommand> commandMap = new HashMap<String, ImageProcessingCommand>();
    commandMap.put(CommandType.RED_COMPONENT.toString(), new RedComponentGreyscaleCommand());
    commandMap.put(CommandType.GREEN_COMPONENT.toString(), new GreenComponentGreyscaleCommand());
    commandMap.put(CommandType.BLUE_COMPONENT.toString(), new BlueComponentGreyscaleCommand());
    commandMap.put(CommandType.VALUE_COMPONENT.toString(), new ValueComponentGreyscaleCommand());
    commandMap.put(CommandType.LUMA_COMPONENT.toString(), new LumaProcessingCommand());
    commandMap.put(CommandType.INTENSITY_COMPONENT.toString(),
            new IntensityComponentGreyscaleCommand());
    commandMap.put(CommandType.HORIZONTAL_FLIP.toString(), new FlipHorizontalCommand());
    commandMap.put(CommandType.VERTICAL_FLIP.toString(), new FlipVerticalCommand());
    commandMap.put(CommandType.BRIGHTEN.toString(), new FlipVerticalCommand());
    commandMap.put(CommandType.GREYSCALE.toString(), new LumaProcessingCommand());
    commandMap.put(CommandType.GAUSSIAN_BLUR.toString(), new GaussianBlurCommand());
    commandMap.put(CommandType.SHARPEN.toString(), new SharpenCommand());
    commandMap.put(CommandType.SEPIA.toString(), new SepiaProcessingCommand());
    return commandMap;
  }

  /**
   * Returns all types in available in the histogram.
   *
   * @return the types as a {@code Map}.
   */
  public static Map<Color, Function<Pixel, Integer>> getTypes() {
    Map<Color, Function<Pixel, Integer>> types = new HashMap<Color, Function<Pixel, Integer>>();
    types.put(new Color(255, 0, 0, 50), PixelReader::getRed);
    types.put(new Color(0, 255, 0, 50), PixelReader::getGreen);
    types.put(new Color(0, 0, 255, 50), PixelReader::getBlue);
    types.put(new Color(50, 50, 50, 50), PixelReader::getIntensity);
    return types;
  }

  /**
   * Converts an image to its BufferedImage.
   *
   * @param image image to be converted
   * @return BufferedImage version of the image
   */
  public static BufferedImage getBufferedImage(ImageInterface image) {
    BufferedImage img = new BufferedImage(image.getWidth(),
            image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        Pixel currentPixel = image.getPixelAt(row, col);
        int rgb = new Color(currentPixel.getRed(), currentPixel.getGreen(),
                currentPixel.getBlue(), currentPixel.getAlpha()).getRGB();
        img.setRGB(col, row, rgb);
      }
    }
    return img;
  }
}
