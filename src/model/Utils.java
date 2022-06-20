package model;

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
    return new CommandType[] {
      CommandType.RED_COMPONENT,
      CommandType.GREEN_COMPONENT,
      CommandType.BLUE_COMPONENT,
      CommandType.VALUE_COMPONENT,
      CommandType.LUMA_COMPONENT,
      CommandType.INTENSITY_COMPONENT,
      CommandType.HORIZONTAL_FLIP,
      CommandType.VERTICAL_FLIP,
      CommandType.BRIGHTEN,
      CommandType.GAUSSIAN_BLUR,
      CommandType.SHARPEN,
      CommandType.SEPIA,
    };
  }

  /**
   * Returns all commands in {@code CommandType} enum.
   *
   * @return all commands.
   */
  public static CommandType[] getCommands() {
    return CommandType.values();
  }
}
