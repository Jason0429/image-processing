package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a model for the image processing program and stores representations of
 * .PMM images.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private final Map<String, ImageInterface> images;

  /**
   * Constructs a new ImageProcessingModel with an empty map responsible for holding
   * the name of the image and their corresponding {@code Image} object.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, ImageInterface>();
  }

  @Override
  public void storeImage(String name, ImageInterface img) throws IllegalArgumentException {
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
  public ImageInterface getImage(String name) throws IllegalArgumentException {
    if (this.images.containsKey(name)) {
      return this.images.get(name);
    }
    throw new IllegalArgumentException(ExceptionMessage.IMAGE_NOT_FOUND.toString());
  }

  @Override
  public String[] getImageNames() {
    String[] imageNames = new String[this.images.keySet().size()];
    int i = 0;
    for (String name : this.images.keySet()) {
      imageNames[i] = name;
      i++;
    }
    return imageNames;
  }
}
