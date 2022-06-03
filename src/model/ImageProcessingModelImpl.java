package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a model for the image processing program and stores representations of
 * .PMM images.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private Map<String, Image> images;

  /**
   * Constructs a new ImageProcessingModel.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<String, Image>();
  }

  @Override
  public void storeImage(String name, Image img) throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.images.put(name, img);
  }

  @Override
  public Image getImage(String name) throws IllegalArgumentException {
    return this.images.get(name);
  }
}
