package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import controller.loader.ImageLoader;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@code ImageProcessingModelImpl} class.
 */
public class ImageProcessingModelImplTest {
  private ImageProcessingModel model1;

  @Before
  public void init() {
    this.model1 = new ImageProcessingModelImpl();
  }

  @Test
  public void testConstructor() {
    ImageProcessingModel test = new ImageProcessingModelImpl();
    ImageInterface img = ImageLoader.load("res/test3x3.ppm");
    test.storeImage("square", img);
    assertEquals(img, test.getImage("square"));
  }

  @Test
  public void testStoreImage() {
    ImageInterface img = ImageLoader.load("res/test3x3.ppm");
    this.model1.storeImage("square", img);
    assertEquals(img, this.model1.getImage("square"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoreImageEmptyName() {
    ImageInterface img = ImageLoader.load("res/test3x3.ppm");
    this.model1.storeImage("", img);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoreImageWhiteSpaceName() {
    ImageInterface img = ImageLoader.load("test-images/test3x3.ppm");
    this.model1.storeImage(" \n ", img);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStoreImageNullImg() {
    this.model1.storeImage("square", null);
  }

  @Test
  public void testGetImage() {
    ImageInterface img = ImageLoader.load("res/test3x3.ppm");
    this.model1.storeImage("square", img);
    assertEquals(img, this.model1.getImage("square"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageInvalidName() {
    this.model1.getImage("circle");
  }

  @Test
  public void testGetImageNames() {
    ImageInterface img = ImageLoader.load("res/test3x3.ppm");
    assertEquals("[]", Arrays.toString(this.model1.getImageNames()));
    this.model1.storeImage("square", img);
    assertEquals("[square]", Arrays.toString(this.model1.getImageNames()));
  }
}
