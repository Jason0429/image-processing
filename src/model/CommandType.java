package model;

/**
 * Represents valid command enumerations.
 */
public enum CommandType {
  RED_COMPONENT("red-component"),
  GREEN_COMPONENT("green-component"),
  BLUE_COMPONENT("blue-component"),
  VALUE_COMPONENT("value-component"),
  LUMA_COMPONENT("luma-component"),
  GREYSCALE("greyscale"),
  INTENSITY_COMPONENT("intensity-component"),
  HORIZONTAL_FLIP("horizontal-flip"),
  VERTICAL_FLIP("vertical-flip"),
  BRIGHTEN("brighten"),
  GAUSSIAN_BLUR("gaussian-blur"),
  SHARPEN("sharpen"),
  SEPIA("sepia"),
  DOWNSCALE("downscale");

  private final String command;

  CommandType(String command) {
    this.command = command;
  }

  @Override
  public String toString() {
    return this.command;
  }

  public static CommandType fromString(String cmd) throws IllegalArgumentException {
    for (CommandType c : CommandType.values()) {
      if (c.toString().equals(cmd)) {
        return c;
      }
    }
    throw new IllegalArgumentException("Command not found");
  }
}
