package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a model for the image processing program and stores representations of
 * .PMM images.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private final Map<String, Image> images;

  /**
   * Constructs a new ImageProcessingModel with an empty map responsible for holding
   * the name of the image and their corresponding {@code Image} object.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, Image>();
  }

  @Override
  public void storeImage(String name, Image img) throws IllegalArgumentException {
    if (name.trim().equals("")) {
      throw new IllegalArgumentException(
              String.format(ExceptionMessage.SPECIFIC_STRING_CANNOT_BE_EMPTY.toString(),
                      "Name of image"));
    }
    if (img == null) {
      throw new IllegalArgumentException(
              String.format(ExceptionMessage.SPECIFIC_NULL_ARGUMENT.toString(), "Image"));
    }
    this.images.put(name, img);
  }

  @Override
  public Image getImage(String name) throws IllegalArgumentException {
    if (this.images.containsKey(name)) {
      return this.images.get(name);
    }
    throw new IllegalArgumentException(ExceptionMessage.IMAGE_NOT_FOUND.toString());
  }
}
