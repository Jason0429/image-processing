package model;

import controller.ImageLoader;
import model.Image;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageProcessingModelImplTest {
  private ImageProcessingModel model1;

  @Before
  public void init() {
    this.model1 = new ImageProcessingModelImpl();
  }

  @Test
  public void testConstructor() {
    ImageProcessingModel test = new ImageProcessingModelImpl();
    Image img = ImageLoader.load("images/test3x3.ppm");
    test.storeImage("square", img);
    test.getImage("square");
  }

  @Test
  public void testStoreImage() {
    Image img = ImageLoader.load("images/test3x3.ppm");
    model1.storeImage("square", img);
    assertEquals(img, model1.getImage("square"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoreImageEmptyName() {
    Image img = ImageLoader.load("images/test3x3.ppm");
    model1.storeImage("", img);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoreImageWhiteSpaceName() {
    Image img = ImageLoader.load("images/test3x3.ppm");
    model1.storeImage(" \n ", img);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoreImageNullImg() {
    model1.storeImage("square", null);
  }

  @Test
  public void testGetImage() {
    Image img = ImageLoader.load("images/test3x3.ppm");
    model1.storeImage("square", img);
    assertEquals(img, model1.getImage("square"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageInvalidName() {
    model1.getImage("circle");
  }
}
